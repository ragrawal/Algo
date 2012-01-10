package dlib;

import  java.awt.*;

/**
 * 
 * Provides a convenient interface to preload classes needed to run a applet, and demonstrates
 * proper use of Class.forName and threads
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public class class_preloader implements java.lang.Runnable
{
	public int classes_to_go = 0;
	public Label label = null;
	private String classlist [ ] = null;
	private int nextclass = 0;

	/**
	 * preload with an animation coundown of classes to go 
	 */

	public class_preloader (String []names, Label l) 
	{
		int len = names.length;
			classlist = names;
			classes_to_go = len;
			label=l;
			changeLabel();
			/* launch two threads, so they can overlap the turnaround delays */
			new Thread(this,"Loader-1").start();
			new Thread(this,"Loader-2").start();
	}


	/**
	 * just preload with no animation 
	 */

	public class_preloader (String []names) 
	{
		this(names,null);
	}

	synchronized void changeLabel () 
	{
		if(label!=null) 
			{label.setText((classes_to_go>0) ? "Loading " + classes_to_go : "");
			}
	}

	synchronized int pickclass () 
	{
		return(nextclass++);
	}

	public void run () 
	{
		int target;
		while((target = pickclass())<classlist.length)
			{
			try 
				{Class.forName(classlist[target]);
				classes_to_go--;
				}
			catch (ClassNotFoundException err)
				{classes_to_go--;
				System.out.println("Class " + classlist[target] 
				+ " not found " + err.toString());
				}
			changeLabel();
			}
	}

}

