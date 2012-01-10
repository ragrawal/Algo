package dlib;

/* 
$Id: SmallSet.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: SmallSet.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */

/**
 * 
 * 
 * Implements a "small set" which maps an array of strings into an integer of bits,
 * and prints itself in an intellegible way (instead of as an unintellegible integer).
 * 
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public abstract class SmallSet extends BaseObject
{
	public int value;
	public abstract String[ ] Elements ();
	// constructors 
	public SmallSet () 
	{
		value=0;
	}

	public SmallSet (int val) 
	{
		value = val;
	}

	public int Set_Indexof (String element) 
	{
		String Elements[] = Elements();
			 for(int i=0;  i<Elements.length;  i++) 
			  { if(element.equals(Elements[i])) { return(1<<i); };
			  }
			 throw new Error("Element " + element + "is not in " + getName());
	}

	public boolean equals (SmallSet other) 
	{
		return((value==other.value) && (getClass()==other.getClass()));
	}

	public void Add_Element (String element) 
	{
		value |= Set_Indexof(element);
	}

	public void Remove_Element (String element) 
	{
		value &= ~Set_Indexof(element);
	}

	public boolean Contains_Element (String element) 
	{
		return((value&Set_Indexof(element)) != 0);
	}

	public String getElements () 
	{
		String val = "";
		   String elements[]=Elements();
		   for(int i=0; i<elements.length; i++) 
		    { if( ((1<<i)&value)!=0) {val = val + " " + elements[i]; }
		    }
		   return(val);
	}

	public String toString () 
	{
		return("#{set of " + this.getClass().getName() + ": " + this.getElements() + "}");
	}

}

