package com.data;

public class BinaryTree{
   private Node head;
   
   
   public void add(Comparable element)
   {
   	
   	if(head == null){
   	    head = new Node(element);
   	    return
        }
        Node current = head;
        Node parent = head;
        while(current != null){
            parent = current;
            current = (element.compareTo(current.value) > 0 ) ? current.right : current.left;   
        }
        
        Node nn = new Node(element);
        if(element.compareTo(parent))
           parent.right = nn;
        else
           parent.left = nn;
        
        return;

   }
   
   public void print()
   {
   
   }
   
   
   public Comparable removeFirst(Comparable element)
   {
   }
   
   public Comparable removeLast(Comparable element)
   {
   }
   
   public Comparable removeAll(Comparable element)
   {
   }
   
   private boolean remove(Node node)
   {
   
   }
   
   
   
   /*
   * Function:get
   * Finds a node corresponding to a given element. 
   *@params elements:Comparable -- Element 
   *@params 
   */
   private Map<String, Node> get(Comparable element, String option)
   {
   
   }
   
   
   
   
   protected class Node{
   	protected Node left;
   	protected Node right;
   	protected Comparable value;
   	
   	public Node(){}
   	public Node(Comparable element){ 
   	   value = element;
   	}
   	public Node(Comparable element, Node left, Node right){
   	    value = element;
   	    left = left;
   	    right = right;
   	}
   }

}