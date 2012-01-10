package dlib;

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
 * Provides a base object, just above "Object" which all the client's other
 * classes can be based on.  Why not just use "Object"?  You can't supercede
 * or augment type "Object" directly.
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public class BaseObject
{

	/**
	 * implement the getName method for all objects based on BaseObject,
	 * 	so other methods can use getName without worrying if the target implements
	 * 	the method.  It's good to give objects real names, though.
	 * 	@see NamedObject
	 * 	
	 */

	public String getName () 
	{
		return(null);
	}


	/**
	 * A dummy implementation of Copy_Slots as a base for our Coneable 
	 * 	
	 */

	public BaseObject Copy_Slots (BaseObject to) 
	{
		return(to);
	}


	/**
	 * make all objects print with their classname, in a distinctive form,
	 * 	similar to #<typename @x> 
	 * 	
	 */

	public String toString () 
	{
		String myname = this.getName();
		return("#<" 
		+ super.toString()
		+ (myname!=null ? (" " + myname) : "" )
		+ ">");
	}

	// 	public static void main(String args[]) { System.out.println(new BaseObject()); }
}

