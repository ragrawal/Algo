package com.data;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree{
   private Node head;
   private int length = 0 ;
   
   public void add(Comparable element)
   {
   	// if head is null, add to head
   	Node nn = new Node(element);
   	if(head == null){
   	    head = nn;
   	    length++;
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
    
    length++;
    return;

   }
   
   public BinarySearchTree copy(){
	   BinarySearchTree cp = new BinarySearchTree();
	   cp.head = copy(head);
	   return cp;
   }
   
   private Node copy(Node current){
	   if(current == null) return null;
	   return new Node(current.value, copy(current.left), copy(current.right));
   }
   
   public void delete(Comparable element){
	   //elemement can be
	   // 1. leaf node 
	   // 2. node with a single child
	   // 3. node with two child
	   
	   Node current = head;
	   Node parent = null;
	   boolean isLeft = false;
	   
	   //Get element location
	   while(current != null && element.compareTo(current.value) != 0){
		   parent = current;
		   if(element.compareTo(current.value) < 0){
			   current = current.left;
			   isLeft = true;
		   }else{
			   current = current.right;
			   isLeft = false;
		   }
	   }
	   
	   if(current == null) return;
	   int children = current.childrenCount();
	   
	   //if element has no children then delete it
	   if(children == 0){
		   	if(isLeft) parent.left = null;
		   	else parent.right = null;
	   }
	   
	   // if element has one child then make the child
	   // direct descendant of the parent
	   else if(children == 1){
		   Node child = (current.left != null) ? current.left : current.right;
		   if(isLeft) parent.left = child;
		   else parent.right = child;
	   }
	   
	   // if element has two children then find predecessor or successor and replace the node
	   // with the successor. 
	   else {
		   //find successor - 
		   // predecssor -- go one left and then select right most leaf node
		   // successor -- or go one right and select left most leaf node
		   Node next = current.right;
		   while(next.left != null) next = next.left;
		   if(isLeft) parent.left = next;
		   else parent.right = next;
	   }
	   
	   //free node
	   current.left = null;
	   current.right = null;
	   
	   return;	 
   }
   
   
   
   
   
   public Node getRoot(){ return getHead(); }
   public Node getHead(){ return head; }
   public void setHead(Node head){this.head = head; }
   
   //pre-order traversal
   public Comparable[] preOrderUsingLoop(){
      Stack<Node> stack = new Stack<Node>();
      stack.push(head);
      Comparable[] elements = new Comparable[length];
      int counter = 0;
      while(!stack.isEmpty()){
          Node current = stack.pop();
          if(current == null) continue;
          elements[counter++] = current.value;
          stack.push(current.right);
          stack.push(current.left);
      }
      
      return elements;
   }
   
   public Comparable[] preOrder(){ return traverse("preOrder"); }
   public Comparable[] inOrder() { return traverse("inorder"); }
   public Comparable[] postOrder() { return traverse("postorder"); }
   public Comparable[] levelOrder() { return traverse("levelorder"); }
   
   protected Comparable[] traverse(final String type){
   	ArrayList<Comparable> store = new ArrayList<Comparable>();
   	if("preorder".equalsIgnoreCase(type))
   	    preOrder(head, store);
   	else if("inorder".equalsIgnoreCase(type))
   	    inOrder(head, store);
   	else if("postorder".equalsIgnoreCase(type))
   	    postOrder(head, store);
   	else if("levelOrder".equalsIgnoreCase(type))
   	    levelOrder(head, store);
   	else
   	    preOrder(head, store);
   	
   	int size = store.size();
   	Comparable[] elements = new Comparable[size];
   	for(int i=0; i< size; i++)
   		elements[i] = store.get(i);
   	return elements;
   }
   
   private void preOrder(Node node, ArrayList<Comparable> store){
   	if(node == null) return;
   	store.add(node.value);
   	preOrder(node.left, store);
   	preOrder(node.right, store);
   }
   
   // In order returns sorted tree
   private void inOrder(Node node, ArrayList<Comparable> store){
   	if(node == null) return;
   	inOrder(node.left, store);
   	store.add(node.value);
   	inOrder(node.right, store);
   	return;
   }
   
   //Post Order
   private void postOrder(Node node, ArrayList<Comparable> store){
   	if(node == null) return;
   	postOrder(node.left, store);
   	postOrder(node.right, store);
   	store.add(node.value);
   	return;
   }
   
   // breadth first traverse -- level order
   private void levelOrder(Node node, ArrayList<Comparable> store){
     Queue<Node> queue = new LinkedList<Node>();
     queue.offer(node);
     while(!queue.isEmpty()){
         Node current = queue.poll();
         if(current == null) continue;
         store.add(current.value);
         queue.offer(current.left);
         queue.offer(current.right);
     }
   }
   
   public void print(){
	   print(head, 0, new PrintWriter(System.out, true));
   }
   public void print(PrintWriter writer){
   	print(head, 0, writer);	
   }
   
   private void print(Node node, int level, PrintWriter writer)
   {
   	if(node == null) return;
	for(int i=0; i < 2*(level-1); i++) writer.print(" ");
	if(level > 0) writer.print("|-");
	writer.println(node.value); 
	print(node.left, level+1, writer);
	print(node.right, level+1, writer);
   }
   
   
   
   /* Inner Data Structure */
   public class Node{
   	protected Node left;
   	protected Node right;
   	protected Comparable value;
   	
   	public Node(){}
   	public Node(Comparable element){ 
   	   value = element;
   	}
   	
   	public Node(Comparable element, Node left, Node right){
   	    this.value = element;
   	    this.left = left;
   	    this.right = right;
   	}
   	
   	public Comparable value(){ return value; }
   	
   	public Node left(){ return left; }
   	public void setLeft(Node left){ this.left = left; }
   	
   	public Node right(){ return right; }
   	public void setRight(Node right){ this.right = right; }
   	
   	public int childrenCount(){
   		int counter = 0;
   		if(left != null) counter++;
   		if(right != null) counter++;
   		return counter;
   	}
   	
   	
   }
   

}