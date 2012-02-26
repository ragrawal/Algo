package com.data;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class BinarySearchTree extends BinaryTree {
   
   
  public void add(BinaryNode node){
	  //Sanity Check
	  if(node == null) return;
	  //Check root
	  if(root == null){
		  this.root = node;
		  return;
	  }
	  //Traver and add to right place
	  Comparable insertKey = node.getKey();
	  BinaryNode current = this.root;
	  BinaryNode parent = null;
	  while(current != null){
		  parent = current;
		  current = (insertKey.compareTo(current.getKey()) >= 0) 
				  		? current.right
				  		: current.left;
	  }
	  
	  if (insertKey.compareTo(parent.key) >= 0) parent.setRight(node);
	  else parent.setLeft(node);	  
  }
	
	
  
   
   public BinarySearchTree copy(){
	   BinarySearchTree cp = new BinarySearchTree();
	   cp.root = copy(root);
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
	   
	   Node current = root;
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
   
   
   
   

      

}