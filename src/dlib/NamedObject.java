package dlib;

/* 
$Id: NamedObject.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: NamedObject.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */

/**
 * NamedObject is a simple extension to BaseObject,  it
 * has a user-supplied name.  That name will appear whenever the
 * object is printed, so you'll see #<object@nnn Good Stuff> instead
 * of #<object@xyx>
 * 
 * You will thank yourself if you use static variables derived from NamedObject 
 * instead of integers as "Enum" replacements
 * <p>
 * for example:
 * <pre>
 * 	class Cases extends NamedObject
 * 	{ public static final NormalCase = new Cases("normal");
 * 	  public static final Othercase = new Cases("unusual");
 * 	  public Cases(String name) { super(name); }
 * 	}
 * </pre>
 * <br>
 * then, in your code
 * <pre>
 * 	class Foo implements Cases
 * 	{
 * 	 public test(Cases thiscase)
 * 	 { if(thiscase == NormalCase) return(true) else return(false);
 * 	 }
 * 	}
 * </pre>
 * <p>
 * the only downside of this is that you can't use "switch" statements on Cases
 * 
 * 
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 * 
 */

public class NamedObject extends BaseObject
{
	public String name;
	private static int Unnamed_Sequence = 0;
	// constructor 
	public NamedObject () 
	{
		name="unnamed_" + Unnamed_Sequence++;
	}

	public NamedObject (String name) 
	{
		this.name = name;
	}

	public String getName () 
	{
		return(name);
	}

	// public static void main(String args[]) { System.out.println(new NamedObject("Fred")); }
}

