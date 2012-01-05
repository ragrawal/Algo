package com.data;

public class BinaryTree{
   private Node head;
   
   
   public void add(Comparable element)
   {
   	// if head is null, add to head
   	Node nn = new Node(element);
   	if(head == null){
   	    head = nn;
   	    return;
        }
        
        //find the position where to add the node
        Node current = head;
        Node parent = head;
        while(current != null){
            parent = current;                         // update parent
            
            //if element is greater than current
            // then go right else go left
            current =  (element.compareTo(current.value) > 0) ? 
            			current.right : 
            			current.left;
        }
        
        // if element greater than parent
        // then add to right else add to left;
        if(element.compareTo(parent.value) > 0 )            
           parent.right = nn;
        else
           parent.left = nn;
        
        return;

   }
   
   
   public void print(){
   	print(head, 0);
   	
   }
   
   private void print(Node node, int level)
   {
   	if(node == null) return;
	for(int i=0; i < 2*(level-1); i++) System.out.print(" ");
	if(level > 0) System.out.print("|-");
	System.out.println(node.value); 
	print(node.left, level+1);
	print(node.right, level+1);
   }
   
   
   
   /* Inner Data Structure */
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