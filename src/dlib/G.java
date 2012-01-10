package dlib;


/**
 * Class G contains "General utilities, small static functions
 * that any program may need.
 * @version 1.0 March 1997
 * @author Dave Dyer <ddyer@netcom.com>
 * 
 */

public class G
{

	/**
	 * G.Assert returns true, or throws an Error.  It provides a 
	 *   convenient place to place a breakpoint for any kind of internally
	 *   detected error.
	 *   
	 */

	public static boolean Assert (boolean condition, String message) 
	{
		if(!condition) 
		 { throw new Error(message); }
		else { return(true); }
	}

}

