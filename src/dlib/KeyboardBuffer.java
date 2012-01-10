package dlib;

import  java.awt.*;
import  java.awt.event.*;
import  java.io.*;

/**
 * This class is Obligatory jdk 1.1 (uses new event model)
 * 
 * This is a subclass of TextArea which keeps track of the
 * location of the cursor, and distinguishes text typed
 * below the cursor from text printed or edited above the
 * cursor.  It is intended as a helper class for ConsoleWindow
 * 
 * @see ConsoleWindow
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.03, March 1997
 * 
 */

class windowOutputStream extends java.io.OutputStream
{
	KeyboardBuffer window;
	byte buffer [ ] = new byte[128];
	int idx = 0;
	public void flush () throws IOException  
	{
		if(idx>0) 
		  { int n = idx;
		    idx=0;
		    write(buffer,0,n); 
		  }
	}

	void safeflush () throws IOException  
	{
		flush();
	}

	windowOutputStream (KeyboardBuffer window) 
	{
		this.window = window;
	}

	public void write (int b) throws IOException  
	{
		buffer[idx++]=(byte)b; 
			  if(idx>=buffer.length) {flush();}
	}

	public void write (byte b[]) throws IOException  
	{
		flush(); window.write(b,0,b.length);
	}

	public void write (byte b[], int from, int len) throws IOException  
	{
		flush();
		window.write(b,from,len);
	}

}


class windowInputStream extends java.io.InputStream
{
	KeyboardBuffer window;
	windowInputStream (KeyboardBuffer window) 
	{
		this.window = window;
	}

	public int available () throws IOException  
	{
		window.outputstream.safeflush(); return(window.available());
	}

	public int read () throws IOException  
	{
		window.outputstream.safeflush(); 
		{int newchar = window.read();
		//System.out.println(" read returns "+newchar);
		return(newchar);
	}}
	/* we need this method in jdk 1.1 because input readers try to buffer */
	public int read(byte ar[],int from, int len) throws IOException
	{int nchars=0;
	 while(nchars<len) 
	 { ar[from+nchars]=(byte)read();
	   nchars++;
	   //System.out.println("got " + ar[from+nchars]);
	   if(window.available()<=0) { break; }
	 }
	 //System.out.println("Read b returns " + nchars);
	 return(nchars);
	}
	// public int read(byte ar[]) {return(window.read(ar));}
	// public int read(byte ar[],int from,int to) { return(window.read(ar,from,to));}
}


public class KeyboardBuffer extends java.awt.TextArea implements KeyListener
{
	private boolean dead = false;
	// set to true when the visible portal is closed
	private Thread suspendedthread;
	// the guy we zapped
	private dlib.windowInputStream inputstream = new windowInputStream(this);
	dlib.windowOutputStream outputstream = new windowOutputStream(this);
	private char temp [ ] = new char[1];
	private int marked_position=0;
	private boolean charisready = false;
	private boolean keyisdown = false;
	// a key is down and has not come up yet
	private int keyselend;
	// end of the selection when the key wend down
	private int keyisdownat;
	private boolean linemode = true;
	// true if we don't admit input is there
	// until there is an eol character
	private int bufferchars = 10000;

	/**
	 * set the maximum number of characters to keep in the buffer.  Adding this is motivated
	 * 	by the observation that none of the implementations of TextArea handle unliminited length
	 * 	strings gracefully..
	 * 	
	 */

	void setBufferchars (int n) 
	{
		bufferchars = n;
	}

	// constructors 
	public KeyboardBuffer (String name) 
	{
		super(name);
		this.addKeyListener(this);
	}

	public KeyboardBuffer (int rows, int columns) 
	{
		super(rows,columns);
		this.addKeyListener(this);
	}

	public KeyboardBuffer (String name, int rows, int cols) 
	{
		super(name,rows,cols);
		this.addKeyListener(this);
	}

	public KeyboardBuffer (String name, int rows, int cols, int bufferchars) 
	{
		super(name,rows,cols);
		this.bufferchars = bufferchars;
		this.addKeyListener(this);

	}

	// get the raw input and output streams 
	OutputStream OutputStream () 
	{
		return(outputstream);
	}

	InputStream InputStream () 
	{
		return(inputstream);
	}

	// get the formatted input and output streams 
	public PrintWriter PrintWriter () 
	{
		 return (new PrintWriter(outputstream,true));
	}
    DataInputStream DataInputStream() { return(new DataInputStream(inputstream)); }
  
	public InputStreamReader InputStreamReader () 
	{
		return (new InputStreamReader(inputstream));
	}

	BufferedReader BufferedReader () 
	{
		return new BufferedReader(InputStreamReader());
	}

	public void set_linemode (boolean t_f) 
	{
		linemode = t_f;
		 set_charisready();
	}

	public boolean linemode_p () 
	{
		return(linemode);
	}

	//+ after any change in state, decide if we think a character is ready 
	//.    for the consumer 
	private synchronized void set_charisready () 
	{
		 String text = getText();
		    int len = text.length();
		    charisready = false;
		    if (!keyisdown && (len>marked_position))
		     { if(!linemode) {charisready = true; }
		       else
		       {for(int i=marked_position;
		     			 i<len;
		     			 i++) 
		     			 {char ch = text.charAt(i);
		            if((ch == '\r') || (ch =='\n') || (ch==Character.LINE_SEPARATOR)) 
		             { charisready = true; break; 
		             }
		           }
		     }}
		  	//System.out.println("Mark " + marked_position + " last " + (int)text.charAt(len-1));
		    if(charisready) 
		    {Thread s=suspendedthread;
		     suspendedthread=null;
		     if(s!=null) 
		     {s.resume();
		      //System.out.println("Thread " + s + " resumed"); 
		     }
		    }
			//System.out.println("CharIsReady " + charisready);
	}

	private void AddToMarkedPosition (int dif) 
	{	//System.out.println("add " + dif);
		marked_position += dif;
		set_charisready();
	}

	private synchronized void NoticeNewText (int oldlen, int selend) 
	{
	int newlen = getText().length();
			 if((newlen!=oldlen)  && (selend<marked_position))
				 {AddToMarkedPosition(newlen-oldlen);
				 }
	}
	public void keyTyped (KeyEvent e) 
	{/* dummy method to satisfy the interface */
	}

	public void keyPressed (KeyEvent e) 
	{
 	//System.out.println("pressed");
	  keyisdown=true; charisready=false;
	  keyisdownat = getText().length();
		keyselend = getSelectionEnd();		//remember these until the key comes back up
	}

	public void keyReleased (KeyEvent e) 
	{
	//System.out.println("released");
 	 int len = keyisdown ? keyisdownat : getText().length();
	 int sel = keyisdown ? keyselend : getSelectionEnd();
	 NoticeNewText(len,sel);
	 keyisdown=false;
	 set_charisready(); 
	}

	public synchronized void insert (String str, int pos) 
	{//System.out.println("inserting "+ str);
			if((str!=null) && (str.length()>0))
				{charisready=false;
				super.insert(str,pos);
				if(pos<marked_position) 
				 {
				   AddToMarkedPosition(str.length());
				 }
					else {set_charisready();}; 
		    }
	}


	public void insertTextBeforeMark (String istr) 
	{	int len=0,ilen=ilen=istr.length(); 
		char sb[] = new char[ilen];
	    for(int i=0;i<ilen; i++)
		{ char ch = istr.charAt(i);
		  if(ch!='\r') { sb[len++]=ch; }
		}
		if(len>0)
		{String str = new String(sb,0,len);
		 int current_len = getText().length();
		 if((current_len+len) > bufferchars)
				  {/* get rid of some */
				   int remchars = Math.min(200,current_len/3);
				   replaceRange("",0,remchars-1);
				  }
				 {charisready=false;
				 boolean oldkd = keyisdown;
				 keyisdown=true; charisready=false;
				 insert(str,marked_position);
				 marked_position+=str.length();
				 keyisdown=oldkd;
				 set_charisready();
		    }}
	}

	public synchronized void append (String str) 
	{	//System.out.println("Appending " + str);
		charisready=false;
		super.append(str);
		set_charisready();
		//System.out.println(getSelectionStart()+" "+getSelectionEnd()+" "+getText().length());
}


	public synchronized void replaceRange (String str, int start, int end) 
	{
		charisready=false;
		//System.out.println("Replacerange " + str + " " + start + " " + end);
		super.replaceRange(str,start,end);
		if(marked_position>=end)
		 {
		 AddToMarkedPosition(str.length()-(end-start));
		 }
		else {set_charisready();};
	}
	
	public void setText (String str) 
	{
		charisready=false;
		super.setText(str);
		marked_position = getText().length();
		set_charisready();
	}

	private char readChar () 
	{
		 char value = (char)0;
			 if(charisready)
			 { String str = getText();
		    int newlen = str.length();
		    if((newlen>marked_position)) 
		     {value = str.charAt(marked_position++);
		     } 
		    }
		  return(value);
	}

	private void Do_Obituary () throws IOException  
	{
		if(dead) throw 
			new IOException(this.toString() + ": I'm dead" );
	}

	private char waitChar () throws IOException  
	{
		char ch=(char)0;
		while(!dead && (ch=readChar())==(char)0) 
		 	{ suspendedthread=Thread.currentThread();
		 	  //System.out.println("Going to suspend thread" + suspendedthread);
		 	  suspendedthread.suspend(); 
		 	  //System.out.println("thread" + suspendedthread + " running again");
		 	}
	  Do_Obituary();			
	  //System.out.println("WaitChar returns " + (int)ch );
		 	return(ch);
	}

	// stream methods 
	public void die () 
	{
			dead=true;
		  	if(suspendedthread!=null) { suspendedthread.resume(); }
	}

	public int available () 
	{
		/* here's a tricky bit: there are at least three threads involved here,
		   the system thread actually maintaining the buffer, the console thread 
		   running the window, and the client thread reading/writing the streams.
		   Rather than let the client know what's really going on, we only tell him
		   what we want him to know, so he doesn't accidentally notice the buffer
		   in some transient state */
		   return(charisready ? getText().length()-marked_position : 0);
	}

	public int read () throws IOException  
	{
		char ch=waitChar(); return((int)ch);
	}

	public void write (int v) throws IOException  
	{
		char ch =(char)v;
		    /* although this looks circuitous, this should actually be cons free */
		    Character chr = new Character(ch);
		 	 Do_Obituary();
		    insertTextBeforeMark(chr.toString());
	}

	public void write (byte data[], int from, int len) throws IOException  
	{
		Do_Obituary();
		insertTextBeforeMark(new String(data,from,len));
	}

}
