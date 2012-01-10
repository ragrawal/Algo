package dlib;

/* 
$Id: JavaNames.java 1.2 1996/09/10 02:03:30 ddyer Exp $
$Log: JavaNames.java $
Revision 1.2  1996/09/10 02:03:30  ddyer
added list

Revision 1.1  1996/09/07 13:06:00  ddyer
Initial revision

 */

/**
 * 
 * This class knows the names and numbers for all the AWT event types.  It's intended
 * use is as a debugging aid for AWT, so that debugging messages can print 
 * 
 * "Event type MOUSE_EVENT" instead of "EVENT_TYPE 500"
 * 
 * @see NameDB
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.0, August 1996
 * 
 */

public class JavaNames extends BaseObject
{

	/**
	 * Usage:  JavaNames.EventTypes.FindName( event_id )
	 *   
	 */

	public static NameDB EventTypes = new NameDB("Event Names");
	static  
	{
		EventTypes.AddName("SHIFT_MASK", 1);
		EventTypes.AddName("CTRL_MASK", 2);
		EventTypes.AddName("META_MASK", 4);
		EventTypes.AddName("ALT_MASK", 8);
		EventTypes.AddName("HOME", 1000);
		EventTypes.AddName("END", 1001);
		EventTypes.AddName("PGUP", 1002);
		EventTypes.AddName("PGDN", 1003);
		EventTypes.AddName("UP", 1004);
		EventTypes.AddName("DOWN", 1005);
		EventTypes.AddName("LEFT", 1006);
		EventTypes.AddName("RIGHT", 1007);
		EventTypes.AddName("F1", 1008);
		EventTypes.AddName("F2", 1009);
		EventTypes.AddName("F3", 1010);
		EventTypes.AddName("F4", 1011);
		EventTypes.AddName("F5", 1012);
		EventTypes.AddName("F6", 1013);
		EventTypes.AddName("F7", 1014);
		EventTypes.AddName("F8", 1015);
		EventTypes.AddName("F9", 1016);
		EventTypes.AddName("F10", 1017);
		EventTypes.AddName("F11", 1018);
		EventTypes.AddName("F12", 1019);
		EventTypes.AddName("WINDOW_EVENT", 200);
		EventTypes.AddName("WINDOW_DESTROY", 201);
		EventTypes.AddName("WINDOW_EXPOSE", 202);
		EventTypes.AddName("WINDOW_ICONIFY", 203);
		EventTypes.AddName("WINDOW_DEICONIFY", 204);
		EventTypes.AddName("WINDOW_MOVED", 205);
		EventTypes.AddName("KEY_EVENT", 400);
		EventTypes.AddName("KEY_PRESS", 401);
		EventTypes.AddName("KEY_RELEASE", 402);
		EventTypes.AddName("KEY_ACTION", 403);
		EventTypes.AddName("KEY_ACTION_RELEASE", 404);
		EventTypes.AddName("MOUSE_EVENT", 500);
		EventTypes.AddName("MOUSE_DOWN", 501);
		EventTypes.AddName("MOUSE_UP", 502);
		EventTypes.AddName("MOUSE_MOVE", 503);
		EventTypes.AddName("MOUSE_ENTER", 504);
		EventTypes.AddName("MOUSE_EXIT", 505);
		EventTypes.AddName("MOUSE_DRAG", 506);
		EventTypes.AddName("SCROLL_EVENT", 600);
		EventTypes.AddName("SCROLL_LINE_UP", 601);
		EventTypes.AddName("SCROLL_LINE_DOWN", 602);
		EventTypes.AddName("SCROLL_PAGE_UP", 603);
		EventTypes.AddName("SCROLL_PAGE_DOWN", 604);
		EventTypes.AddName("SCROLL_ABSOLUTE", 605);
		EventTypes.AddName("LIST_EVENT", 700);
		EventTypes.AddName("LIST_SELECT", 701);
		EventTypes.AddName("LIST_DESELECT", 702);
		EventTypes.AddName("MISC_EVENT", 1000);
		EventTypes.AddName("ACTION_EVENT", 1001);
		EventTypes.AddName("LOAD_FILE", 1002);
		EventTypes.AddName("SAVE_FILE", 1003);
		EventTypes.AddName("GOT_FOCUS", 1004);
		EventTypes.AddName("LOST_FOCUS", 1005);
	}

	// 	public static void main(String args[])
	// 	{ for(int i=0;i<args.length;i++)
	// 		{int ival = Integer.parseInt(args[i]);
	// 			System.out.println(args[i] + " = " + EventTypes.FindName(ival));
	// 		}
	// 	}
}

