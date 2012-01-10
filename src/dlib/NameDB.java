package dlib;

/* 
$Id: NameDB.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: NameDB.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */
import  java.util.*;

/**
 * 
 * This class is a simple repository of correspondances between Strings and numbers. It's
 * intended usage is to ameliorate the brain damage caused by Java's lack of "enum" types.
 * In truth, it should be used only for objects whose representation you do not control.
 * For objects you control, it's much better to use static variables derived from
 * type NamedObject
 * 
 * @see JavaNames
 * @see NamedObject
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 * 
 */

public class NameDB extends NamedObject
{
	// constructors 
	NameDB (String name) 
	{
		super(name);
	}

	private Hashtable Names = new Hashtable();

	/**
	 * find the name associated with a number, or return null 
	 */

	public Object FindName (int number) 
	{
		return( Names.get(new Integer(number)));
	}


	/**
	 * find the name associated with an Integer (boxed number), or return null 
	 */

	public Object FindName (Integer number) 
	{
		return( Names.get(number));
	}


	/**
	 * find the Integer associated with name 
	 */

	public Object FindNumber (String name) 
	{
		return( Names.get(name));
	}


	/**
	 * use this method to build a table of names 
	 */

	public void AddName (String s, int n) 
	{
		Integer i = new Integer(n);
		   Names.put(s,i);
		   Names.put(i,s);
	}


	/**
	 * use this method to build a table of names 
	 */

	public void AddName (String s, Object n) 
	{
		Names.put(s,n);
		   Names.put(n,s);
	}

}

