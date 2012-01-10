package dlib;

import  java.io.*;
/* 
$Id: BaseObject.java 1.3 1996/09/11 03:46:02 ddyer Exp $
$Log: BaseObject.java $
Revision 1.3  1996/09/11 03:46:02  ddyer
added preloader

Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */

/**
 * 
 * Provides a PrintWriter object, which will create and expose a console
 * window if it is used.  The intention of this class is to provide
 * an "emergency console" for use in situations where there normally 
 * should be no output to the console, and/or where output to the console
 * would be hard to see.  A good example of this is applets running
 * under Microsoft Internet Explorer: normally, output just disappears.
 * <P>
 * Suggested usage:
 * <BR>
 * 	System.out = new Deferred_PrintWriter("window name");
 * 
 * <P>
 * If the console window has been opened (probably by output) and closed
 * (probably by the user); then subsequent output will create a new window.
 * <p>
 * To gracefully synchronize closing the console window with exiting the program,
 * use the Always_Wait_For_Finish method.
 * <P>
 * <h4>Implementation Note</h4>
 * This class works by shadowing every one of PrintWriter's public methods,
 * which is pretty ugly, but the only way to do this in Java.  In more elegant
 * languages, it is possible to create a class that implements all methods through
 * a default handler, and passes them all on to the desired client.
 * <P>
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, November 1996
 * 
 */

public class Deferred_PrintWriter extends java.io.PrintWriter
{
	dlib.ConsoleWindow win = null;
	java.io.PrintWriter out = null;
	String name = "Java Output";

	/**
	 * returns true if the console window has been activated and is visible 
	 */

	public boolean Active () 
	{
		return((win!=null) && win.Active());
	}


	/**
	 * wait until the console window has been closed (or was never opened) 
	 * 
	 */

	public void Wait_For_Finish () throws InterruptedException  
	{
		while(Active()) { Thread.sleep(1000); }
	}


	/**
	 * unconsitionally wait for the console window to be closed 
	 * 
	 */

	public void Always_Wait_For_Finish () 
	{
		 try {Wait_For_Finish(); }
				 catch (InterruptedException err) {}
	}


	/**
	 * create a new PrintWriter, which will create a ConsoleWindow if any
	 * 	output is performed. 
	 */

	public Deferred_PrintWriter () 
	{
		super(System.out);
	}


	/**
	 * create a named new PrintStream, which will create a ConsoleWindow if any
	 * 	output is performed. 
	 */

	public Deferred_PrintWriter (String name) 
	{
		this(); this.name = name;
	}


	/**
	 * create the output stream we will use, which is a ConsoleWindow 
	 */

	private void CreateStream () 
	{
		if (out==null || (win==null) || !(win.Active()))
				{ win = new ConsoleWindow(name);
				  out = win.PrintWriter();
			  }
	}

	// Methods
	public boolean checkError () 
	{
		if(out!=null) {return(out.checkError());} else { return(false);}
	}

	public void close () 
	{
		if(out!=null) { out.close(); }
	}

	public void flush () 
	{
		CreateStream(); out.flush();
	}

	public void print (boolean b) 
	{
		CreateStream(); out.print(b);
	}

	public void print (char c) 
	{
		CreateStream(); out.print(c);
	}

	public void print (char s[]) 
	{
		CreateStream(); out.print(s);
	}

	public void print (double d) 
	{
		CreateStream(); out.print(d);
	}

	public void print (float f) 
	{
		CreateStream(); out.print(f);
	}

	public void print (int i) 
	{
		CreateStream(); out.print(i);
	}

	public void print (long l) 
	{
		CreateStream(); out.print(l);
	}

	public void print (Object obj) 
	{
		CreateStream(); out.print(obj);
	}

	public void print (String s) 
	{
		CreateStream(); out.print(s);
	}

	public void println (boolean b) 
	{
		CreateStream(); out.println(b);
	}

	public void println (char c) 
	{
		CreateStream(); out.println(c);
	}

	public void println (char s[]) 
	{
		CreateStream(); out.println(s);
	}

	public void println (double d) 
	{
		CreateStream(); out.println(d);
	}

	public void println (float f) 
	{
		CreateStream(); out.println(f);
	}

	public void println (int i) 
	{
		CreateStream(); out.println(i);
	}

	public void println (long l) 
	{
		CreateStream(); out.println(l);
	}

	public void println (Object obj) 
	{
		CreateStream(); out.println(obj);
	}

	public void println (String s) 
	{
		CreateStream(); out.println(s);
	}

	public void write (String b, int off, int len) 
	{
		CreateStream(); out.write(b,off,len);
	}

	public void write (String b) 
	{
		CreateStream(); out.write(b);
	}

	public void write (char b[], int off, int len) 
	{
		CreateStream(); out.write(b,off,len);
	}

	public void write (int i) 
	{
		CreateStream(); out.write(i);
	}

	public void write (char b[]) 
	{
		CreateStream(); out.write(b);
	}

}

