package dlib;

import  java.io.*;
/* 
$Id: Print_Readable.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: Print_Readable.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */

/**
 * 
 * 
 * classes which implement this protocol are able to print themselves
 * such that the printed text, when encountered by the java compiler, 
 * will generate an equivalent object.  This is intended as part of a
 * framework for tools which produce java source files as their output.
 * 
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public interface Print_Readable
{
	void Print_Readably (PrintStream out);
}

