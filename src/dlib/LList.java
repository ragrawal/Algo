package dlib;

import  java.util.Enumeration;

/**
 * implements a simple list (as in "lisp") and some useful methods on it 
 * 
 * @author Dave Dyer <ddyer@netcom.com>
 * @version 1.01, March 1997
 * 
 */

public class LList extends BaseObject implements java.util.Enumeration
{
	private Object contents;
	private dlib.LList next;

	/**
	 * a list acts as it's own Enumeration 
	 */

	public Enumeration elements () 
	{
		return(this);
	}


	/**
	 * for the Enumeration protocol 
	 */

	public Object nextElement () 
	{
		return(this.next);
	}


	/**
	 * for the Enumeration protocol 
	 */

	public boolean hasMoreElements () 
	{
		return(this.next==null);
	}


	/**
	 * get the contents of this element of the list 
	 */

	public Object Contents () 
	{
		return(this.contents);
	}


	/**
	 * get the next element of the list 
	 */

	public LList Next () 
	{
		return(this.next);
	}


	/**
	 * set the contents of the list to an arbitrary object 
	 */

	public void Set_Contents (Object to) 
	{
		this.contents = to;
	}


	/**
	 * set the next element of the list (to some other list) 
	 */

	public void Set_Next (LList next) 
	{
		this.next = next;
	}

	// constructors 
	public LList () 
	{
		
	}

	public LList (Object contents, LList next) 
	{
		this.contents = contents;
		this.next  = next;
	}


	/**
	 * delete the list containing "item" from the list.  Returns a new head
	 * for the list, so correct usage is  theList = theList.Delete_Item(object);
	 * 
	 */

	public LList Delete_Item (Object item) 
	{
		 LList prev=null;
		  LList cur = this;
		  while(cur!=null) {
		    if(cur.contents == item) { 
		      if(prev!=null) 
			{ prev.next = cur.next;
			  cur.next=null;
		          return(this);
		        }
		      else
		       {prev = cur.next;
		        cur.next = null;
		        return(prev);
		       }
		     }
		    prev = cur;
		    cur=cur.next;
		  }
		  return(this);
	}


	/**
	 * return the length of the list, or 0 if it is null 
	 */

	public static int LList_Length (LList l) 
	{
			int len=0;
		  while(l!=null) { len++; l=l.next; }
		  return(len);
	}


	/**
	 * sort the list, using "fn" to compare pairs of elements.  
	 *  This is implemented by a bubble sort, so is only appropriate
	 *  for short lists, hence the name.  Returns a new head for the
	 *  list, so correct usage is theList=theList.Sort_Short_List(comparefn);
	 *  
	 */

	public LList Sort_Short_LList (CompareFunction fn) 
	{
		   LList out_list = this;
			 LList l=this;
		 	 LList in_list = l.next;
			l.next = null;
			while(in_list!=null) 
			{ /* scan through the in list, performing an insertion
				sort into the out list */
			LList current_list = in_list;
			Object current_item = current_list.contents;
			LList scan_list = out_list;
			LList prev_scan_list = null;
			in_list = in_list.next;
			while(scan_list!=null 
			  && !fn.InOrder(current_item,scan_list.contents)) 
				{
				prev_scan_list = scan_list;
				scan_list = scan_list.next;
				}
			current_list.next = scan_list;
			if(prev_scan_list!=null) 
			   { prev_scan_list.next = current_list; 
			   }
			   else
			   {out_list = current_list;
			   }
		    }
		  return(out_list);
	}

	// sort_short_list
}

/* class List   */
