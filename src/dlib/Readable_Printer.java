package dlib;

/* 
$Id: Readable_Printer.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: Readable_Printer.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */
import  java.io.*;

/**
 * Readable_Printer is a static class which supplies the two argument 
 *  version of "Print_Readably", which prints objects in a form compatible
 *  with Java source files.
 *  
 *  this class also illustrates the pain caused because Java isn't a fully
 *  extensible object environment.  In a better world, we would simply define
 *  Print_Readably methods directly on the object types (including base types such as "int")
 *  
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public class Readable_Printer extends BaseObject
{

	/**
	 * print an expression which casts a containerized primitive type
	 *     (such as Integer or Boolean) to the corresponding primitive type.
	 *     
	 */

	public static void Print_Cast_To_Type (PrintStream out, String objectname, String type) 
	{
		if(type.equals("boolean"))
		 	{ out.print("((Boolean)" + objectname + ").booleanValue()" ); }
		 	else if(type.equals("char"))
		 	{ out.print("((Character)" + objectname + ").charValue()" ); }
				else if(type.equals("byte"))
		 	{ out.print("((byte)((Integer)" + objectname + ").intValue())"); }
				else if(type.equals("short"))
		 	{ out.print("((short)((Integer)" + objectname + ").intValue())" ); }
				else if(type.equals("int"))
				{ out.print("((Integer)" + objectname + ").intValue()" ); }
				else if(type.equals("long"))
		 	{ out.print("((Integer)" + objectname + ").longValue()" ); }
				else if(type.equals("float"))
		 	{ out.print("((Float)" + objectname + ").floatValue()" ); }
				else if(type.equals("double"))
		 	{ out.print("((Float)" + objectname + ").doubleValue()" ); }
		 	else 
		 	{ out.print( "((" + type + ")" + objectname + ")"); }
	}


	/**
	 * print an object which implements the Print_Readable protocol 
	 */

	public static void Print_Readably (PrintStream out, Print_Readable object) 
	{
		object.Print_Readably(out);
	}


	/**
	 * print any object readably.  An error is generated if the object
	 * 	   doesn't implement the Print_Readable protocol or is not one of
	 * 	   the special cases Readable_Printer explicitly handles.  
	 * 	 
	 */

	public static void Print_Readably (PrintStream out, Object object) 
	{
		 if(object instanceof String) 
				{ /* note that we need this clause becauase sometimes strings are cast
				   as type "Object" at compile time. */
				 Print_Readably(out,(String)object); 
				}
			  else if(object instanceof Print_Readable) 
			  { Print_Readably(out,(Print_Readable)object); 
			  }
				else 
				{throw new Error("Object " + object + " can't print readably");}
	}


	/**
	 * print a string readably.  This prints the enclosing "" and handles
	 *    escaping embedded " and \ characters.  It doesn't handle newlines 
	 *    
	 */

	public static void Print_Readably (PrintStream out, String tf) 
	{
		out.print("\""); 
		   for(int i=0,len=tf.length();   i<len; i++)
		   { char c = tf.charAt(i);
		     if((c=='\"') || c=='\\') out.print("\\");	
		     out.print(c);
		   }
		   out.print("\"");
	}


	/**
	 * print an array or objects readably.  Reading will only work in contexts
	 *   where a static initializer is acceptable.
	 *   
	 */

	public static void Print_Readably (PrintStream out, Object[] arg) 
	{
		out.println();
		 	out.print("{");
		 	for(int i=0,lim=arg.length-1; i<=lim; i++)
		 	 { out.print("	");
		 	   Print_Readably(out,arg[i]);
		 	   if(i!=lim) out.println(",");
		   }
		   out.print("}");
	}

	public static void Print_Readably (PrintStream out, boolean tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, byte tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, short tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, int tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, float tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, double tf) 
	{
		out.print(tf);
	}

	public static void Print_Readably (PrintStream out, char tf) 
	{
		out.print("'" + tf + "'" );
	}

}

