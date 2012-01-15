package com.util;

/* 
$Id: ConsolePanel.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: ConsolePanel.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */
import  java.io.*;
import  java.awt.*;
import  java.awt.event.*;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dlib.G;
import dlib.KeyboardBuffer;

/* version 1.01 adds an explicit "destroy" at the exits  */
/* version 1.02 adds "dead" notification to help handle closed windows  */
/* version 1.03 adds clipping to a finite number of lines in the buffer  */

/**
 * 
 * 
 * This class implements a simple console window, conceptually a replacement for
 * reading/writing to System.out and System.in;  The advantage of this over System.xx
 * is that it works with JDB, the contents of the window can be saved, and *major feature*
 * the typein and typeout can be edited.
 * 
 * The intended use is as a programmer's shell for messages and simple command
 * processing while developing programs.  See the "main" method in this file
 * for an example how to use it.
 * @see Deferred_PrintWriter
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.03, March 1997
 * 
 * 
 */

public class ConsolePanel extends JInternalFrame  implements ComponentListener,ActionListener,ItemListener
{
	private KeyboardBuffer area;
	private InputStreamReader input;
	private BufferedReader bufferedinput;
	private PrintWriter output;
	private boolean dead = false;
	String fonts [ ];
	String styles [ ] = {"Plain", "Bold", "Italic"};
	String sizes [ ] = {"8", "9", "10", "12", "14", "16", "18", "24"};
	String clipboard;
	String saveFile = null;
	String fname;
	int fstyle;
	int fsize;
	static int default_bufferchars = 10000;
	int bufferchars;
	JMenuItem saveItem = new JMenuItem("Save");
	JCheckBoxMenuItem fontItems [ ];
	JCheckBoxMenuItem styleItems [ ];
	JCheckBoxMenuItem sizeItems [ ];
	public ConsolePanel (String title) 
	{
		this(title, default_bufferchars);
	}
	
	public ConsolePanel (String title, int bufferchars)
	{
		 // Initialize
		this.bufferchars = bufferchars;
		area = new KeyboardBuffer("",30,80,bufferchars);
		fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		JMenuBar JMenuBar = new JMenuBar();
		
		fname = "Courier";
		fstyle = Font.PLAIN;
		fsize = 12;
		
		  // Create file menu
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.addActionListener(this);
		fileMenu.add(new JMenuItem("New"));
		fileMenu.add(new JMenuItem("Open..."));
		fileMenu.add(saveItem);
		fileMenu.add(new JMenuItem("Save as..."));
		fileMenu.add(new JMenuItem("-"));
		fileMenu.add(new JMenuItem("Exit"));
		//JMenuBar.add(fileMenu);
		
		  // Create edit menu
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.addActionListener(this);
		editMenu.add(new JMenuItem("Cut"));
		editMenu.add(new JMenuItem("Copy"));
		editMenu.add(new JMenuItem("Paste"));
		editMenu.add(new JMenuItem("Select all"));
		editMenu.add(new JMenuItem("-"));
		//    editMenu.add(new JMenuItem("Find..."));
		//JMenuBar.add(editMenu);
		
		  // Create font menu
		
		JMenu fontMenu = new JMenu("Font");
		fontMenu.addActionListener(this);
		fontItems = new JCheckBoxMenuItem[fonts.length];
		
		for (int i = 0; i < fonts.length; i++) {
			fontItems[i] = new JCheckBoxMenuItem(fonts[i]);
			fontMenu.add(fontItems[i]);
			fontItems[i].addItemListener(this);
			if (fonts[i].equals(fname))
				fontItems[i].setState(true);
		}
		
		JMenuBar.add(fontMenu);
		
		  // Create style menu
		
		JMenu styleMenu = new JMenu("Style");
		styleMenu.addActionListener(this);
		
		styleItems = new JCheckBoxMenuItem[styles.length];
		
		for (int i = 0; i < styles.length; i++) {
			styleItems[i] = new JCheckBoxMenuItem(styles[i]);
			styleItems[i].addItemListener(this);
			styleMenu.add(styleItems[i]);
			
			if (i == fstyle) 
				styleItems[i].setState(true);
		}
		
		JMenuBar.add(styleMenu);
		
		 // Create size menu
		
		JMenu sizeMenu = new JMenu("Size");
		sizeItems = new JCheckBoxMenuItem[sizes.length];
		for (int i = 0; i < sizes.length; i++) {
		      sizeItems[i] = new JCheckBoxMenuItem(sizes[i]);	
		      sizeMenu.add(sizeItems[i]);
			  sizeItems[i].addItemListener(this);
			
			if (sizes[i].equals(new Integer(fsize).toString()))
				sizeItems[i].setState(true);
		}
		 
		JMenuBar.add(sizeMenu);
		setJMenuBar(JMenuBar);
		saveItem.setEnabled(false);
		
		   // Show window
		
		area.setFont(new Font(fname, fstyle, fsize));
		setTitle(title);
		add("Center", area);
		
		pack();
		show();
		this.addComponentListener(this);
	}
	
	// Read file
	public String readFile (String fl) 
	{
		String text = new String();
		
		try {
		      FileReader fs = new FileReader(fl);
		      BufferedReader ds = new BufferedReader(fs);
		      String str = new String();
			
		      while (str != null) {
		  	  str = ds.readLine();
				
				if (str != null)
					text = text + str + "\n";
		      }
		} catch (Exception err) {
		      System.out.println("Cannot open file.");
		}
		
		return text;
	}
	
	// Write file
	public void writeFile (String fl, String txt) 
	{
		try {
		      FileOutputStream fs = new FileOutputStream(fl);
		      DataOutputStream ds = new DataOutputStream(fs);
			String ls = System.getProperty("line.separator");
			
			for (int i = 0; i < txt.length(); i++) {
		  	  char ch = txt.charAt(i);
				
				switch (ch) {
			    case '\n':
					ds.writeBytes(ls);
					break;
					
			    default:
					ds.write(ch);
				}
		      }
		} catch (Exception err) {
		      System.out.println("Cannot save file.");
		}
	}
	
	// Handle system event
	public void dispose () 
	{
		dead=true;//System.out.println("declared dead");
		area.die();
		super.dispose();
	}
	
	// Handle component events
	public void actionPerformed (ActionEvent evt) 
	{	boolean handled=false;
		
		Object obj = evt.getSource();
		String label = evt.getActionCommand();
		//System.out.println("event " + evt + " source is " + obj + "command is " + label );
		String file = null;
		  
		  // Handle file menu
		
		if (label.equals("New")) {
		      area.setText("");
		      saveItem.setEnabled(false);
			  handled=true;
		} else if (label.equals("Open...")) 
		{	handled=true;
		      FileDialog dialog = new FileDialog(new JFrame(), "Open...", 
				FileDialog.LOAD);
			
		      dialog.show();
		      file = dialog.getFile();
			
		      if (file != null) {
				setTitle(file + " - Text Edit");
				
				saveFile = file;
				area.setText(readFile(file));
				saveItem.setEnabled(true);
		      }
			
		}else if (label.equals("Save")) {
			 handled = true;
		      writeFile(saveFile, area.getText());
		} else if (label.equals("Save as...")) 
		{	handled=true;
		      FileDialog dialog = new FileDialog(new JFrame(), "Save as...", 
				FileDialog.SAVE);
		      
			  dialog.show();
		      file = dialog.getFile();
			
		      if (file != null) {
				setTitle(file + " - Text Edit");
				saveFile = file;
				writeFile(file, area.getText());
		      }
		} else if (label.equals("Exit")) {
		      {handled=true;
			   dispose();
		       }
		}else if (label.equals("Cut")) {
			handled = true;
			if(area.getSelectionStart()<area.getText().length())
			{ clipboard = area.getSelectedText();
			  area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd()  );
			}
		} else if (label.equals("Copy")) {
			handled=true;
			if(area.getSelectionStart()<area.getText().length())
				{ clipboard = area.getSelectedText();
				}
		}else if (label.equals("Paste")) {
			 handled = true;
		      int start = area.getSelectionStart();
		      int end = area.getSelectionEnd();
			 if(start<area.getText().length() && start<end)
				{area.replaceRange(clipboard, start, end-1);
				}else
				{ area.insert(clipboard,area.getCaretPosition());
				}
			
		} else	if (label.equals("Select all"))
		      { handled=true;
			    area.selectAll();
				}
		
		//    if (label.equals("Find...")) {
		//	FindDialog dialog = new FindDialog(this, label, true);
		//	dialog.resize(200, 70);
		//	dialog.show();
		//	
		//    }
	G.Assert(handled,"Got an undefined actionEvent"+label);		
	}
	
	public void itemStateChanged(ItemEvent evt)
	{	boolean handled=false;
		Object obj = evt.getSource();
		String label = null;
		if(evt.getItem() instanceof JCheckBoxMenuItem){
			label = ((JCheckBoxMenuItem) evt.getItem()).getLabel();
		}else{
			label = (String)evt.getItem();
		}
		
	
		// Handle font menu
	    for (int i = 0; i < fonts.length; i++) {
			if (label.equals(fonts[i])) {
				for (int j = 0; j < fonts.length; j++) 
					if (i != j && fontItems[j] != null)
						fontItems[j].setState(false);
				
				fname = label;
				area.setFont(new Font(fname, fstyle, fsize));
				handled=true;
			}
	    }
		// Handle size menu
		
	    for (int i = 0; i < sizes.length; i++) {
			if (label.equals(sizes[i])) {
				for (int j = 0; j < sizes.length; j++) 
					if (i != j && sizeItems[j] != null)
						sizeItems[j].setState(false);
				
				fsize = new Integer(label).intValue();
				area.setFont(new Font(fname, fstyle, fsize));
				handled=true;
			}
	    }
		
		// Handle style menu
		
	    for (int i = 0; i < styles.length; i++) {
			if (label.equals(styles[i])) {
				for (int j = 0; j < styles.length; j++) 
					if (i != j && styleItems[j] != null)
						styleItems[j].setState(false);
				
				fstyle = i;
				area.setFont(new Font(fname, fstyle, fsize));
				handled = true;
			}
	    }
		
		G.Assert(handled,"Got an undefined item event" + evt);
	}
	public KeyboardBuffer getTextArea () 
	{
		return area;
	}
	private void Do_Obituary () throws IOException  
	{
		if(dead) throw new IOException(this.toString() + ": I'm dead" );
	}
	public PrintWriter PrintWriter () 
	{
		if(output==null) { output = area.PrintWriter(); }
		   return(output);
	}
	
	/**
	 * get a InputStream corresponding to the typein area if this window.
	 *   Note that the input from the InputStream will be line buffered, so no
	 *   input is available from incomplete lines.
	 *   
	 */
	public InputStreamReader InputStreamReader () 
	{
		if(input==null) { input = area.InputStreamReader();}
		return(input);
	}
	
	/**
	 * get a BufferedReader corresponding to the typein area if this window.
	 *   Note that the input from the BufferedReader will be line buffered, so no
	 *   input is available from incomplete lines.
	 *   
	 */
	public BufferedReader BufferedReader () 
	{
		if(bufferedinput==null) { bufferedinput = new BufferedReader(InputStreamReader()); }
		  return(bufferedinput);
	}
	
	
	/**
	 * returns true if the console window has been activated and is visible 
	 */
	
	public boolean Active () 
	{
		return(!dead);
	}
	
	
	/**
	 * wait until the console window has been closed 
	 */
	
	public void Wait_For_Finish () throws InterruptedException  
	{
		while(Active()) { Thread.sleep(1000); }
	}
	
	
	/**
	 * unconditionally wait until the console window has been closed  
	 */
	
	public void Always_Wait_For_Finish () 
	{
		 try {Wait_For_Finish(); }
		catch (InterruptedException err) {}
	}
	
	/* handle window events */
	public void windowActivated(WindowEvent ev) {/* System.out.println("Act"); */ }  
	public void windowDeactivated(WindowEvent ev) {/* System.out.println("deact"); */}  
	public void windowClosed(WindowEvent ev)  { /* System.out.println("close");*/ }
	public void windowClosing(WindowEvent ev)  {/* System.out.println("closing"); */ dispose(); }
	public void windowDeiconified(WindowEvent ev) {/* System.out.println( "deicon"); */ } 
	public void windowIconified(WindowEvent ev) {/* System.out.println("icon"); */}  
	public void windowOpened(WindowEvent ev)   {/* System.out.println("open");*/}
	
	
	/**
	 * 
	 * <pre>
	 * public static void main (String args[])
	 * { String name = args.length >= 1 ? args[0] : "Console";
	 *   ConsolePanel console = new ConsolePanel(name);
	 *   PrintStream out = console.PrintStream();
	 *   DataInputStream in = console.DataInputStream();
	 *   out.println("ready");
	 *  while (console.Active())
	 *   {
	 *    out.print("> ");
	 *    try 
	 *    {String str = in.readLine();
	 * 		out.println("Typed line: " + str);
	 *   }
	 *   catch (IOException err) {};
	 *   };
	 *   System.exit(0);
	 *  }
	 * </pre>
	 * 
	 */
	
	public static void main (String args[]) 
	{
		 String name = args.length >= 1 ? args[0] : "Console";
		  ConsolePanel console = new ConsolePanel(name);
		  JFrame frame = new JFrame();
		  frame.add(console);
		  frame.pack();
		  frame.setVisible(true);
		  PrintWriter out = console.PrintWriter();
		  BufferedReader in = console.BufferedReader();
		  out.println("ready");
		  
		  if(console.Active()==false) return;
		 //while (console.Active())
		//  {
			out.print("> ");out.flush();
			try 
			{String str = in.readLine();
				if(str!=null) { out.println("Typed : " + str); }
			}
			catch (IOException err) {
				if(console.Active()) { System.out.println("Exception " + err);}; }
		 // } 
			console.Always_Wait_For_Finish();
		//System.exit(0);
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
