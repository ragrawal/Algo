package com.data.tree.binary;

import java.util.ArrayList;
import java.util.Random;


public class BinarySearchTree extends BinaryTree {

	
  public BinarySearchTree(){
	  super();
  }
  
  public void add(BinaryNode node){
	   //Sanity Check
	   if(node == null || node.key == null) return;
	   if(empty()) 
		  this.root = node;
	   else{
		   //Find location where to add
		   BinaryNode current = this.root;
		   BinaryNode parent = null;
		   while(current != null){
			   parent = current;
			   current = (node.key.compareTo(current.key) > 0 ) ? current.right : current.left;
		   }

		   if (node.key.compareTo(parent.key) > 0) parent.setRight(node);
		   else parent.setLeft(node);
	   }
	   return;
  }
  
  public BinaryNode get(Comparable key){
	  if(key == null) return null;
	  BinaryNode current = this.root;
	  while(current != null && key.compareTo(current.key) != 0)
		  current = (key.compareTo(current.key) > 0) ? current.right : current.left;
	  return current;
  }
  
  
   
   /*
    * Function: delete
    * Three Cases:
    * 1. Leaf Node: In this case simply delete the node
    * 2. Hash only one child: in this case, remove the node and add the child to the parent
    * 3. Has two children: In this case replace the node with the 
    * successor and link successor's right to successor's parent
    * 
    */
   public BinaryNode delete(Comparable key){
	   BinaryNode z = get(key);
	   if(z == null) return null;
	   
	   //Determine node to splice out
	   BinaryNode y = (z.left == null || z.right == null) ? z : successor(z);
	   System.out.println("Y = " + y);
	   
	   //There can be only at most one child of splice out node
	   BinaryNode x = (y.left != null) ? y.left : y.right;
	
	   //Bind child of splice out node to y's parent
	   if(x != null) x.parent = y.parent;
	   if(y.parent != null){
	   		if(y.parent.left == y) y.parent.setLeft(x);
	   		else y.parent.setRight(x);
	   }
	   
	   //Replace z with y
	   if(y != z){
		   z.key  = y.key;
		   z.value = y.value;
		   y.delete();
	   }
	   
	   return y;
	   
   }
   
   
   
   public BinaryNode successor(BinaryNode node){
		if(node == null) return null;
		
		//If node has right child then find minimum of right subtree
		if(node.right != null)
			return getMinimum(node.right);
		
		/* KEY CONDITION */
		//else find the first left parent
		BinaryNode parent = node.parent; 
		while(parent != null && node == parent.right){
			node = parent;
			parent = node.parent;
		}
		return parent;
   }
	
   //Predecessor is the element that comes before the given node
   //when you traversal the tree inOrder
   public BinaryNode predecessor(BinaryNode node){
	   if(node == null) return null;
	   
	   //if node has left child then find max of left subtree
	   if(node.left != null)
		   return getMaximum(node.left);
	   
	   //Else predecessor is the first right parent
	   BinaryNode parent = node.parent;
	   while(parent != null && node == parent.left){
		   node = parent;
		   parent = node.parent;
	   }
	   		
	   return parent;
   }
   
   public BinaryNode getMinimum(){
	   return getMinimum(this.root);
   }
   
   public static BinaryNode getMinimum(BinaryNode current){
	   if(current == null) return null;
	   while(current.left != null) current = current.left;
	   return current;
   }
   
   public BinaryNode getMaximum(){
	   return getMaximum(this.root);
   }
   public BinaryNode getMaximum(BinaryNode current){
	   if(current == null) return null;
	   while(current.right != null) current = current.right;
	   return current;
   }
   
   public static void main(String[] args){
	   BinarySearchTree tree = new BinarySearchTree();

	   int[] nodes = {15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1};
	   for(int i=0; i<nodes.length; i++){
		   Integer key = nodes[i];
		   tree.add(new BinaryNode(key, key));	
	   }


	   System.out.println("Orignal Tree");
	   tree.print();

	   System.out.println("=========== TREE STATISTICS =============");
	   System.out.println("Number of Nodes: " + tree.size());
	   System.out.println("Max Depth: " + tree.maxDepth());
	   System.out.println("Max Depth (w/o recursion): " + tree.maxDepthWithoutRecursion());
	   System.out.println("Min: " + tree.getMinimum());
	   System.out.println("Max: " + tree.getMaximum());
	   

	   System.out.println("=========== Delete 9 =============");
	   tree.delete(9);
	   tree.print();

	   System.out.println("=========== Left Rotate about " + nodes[1] + "=============");
	   tree.leftRotate(tree.get(nodes[1]));
	   tree.print();

	   System.out.println("===========RIGHT Rotate about " + nodes[1] + "=============");
	   tree.rightRotatate(tree.get(nodes[1]));
	   tree.print();


	   System.out.println("=========== PreOrder =============");
	   for(BinaryNode node: tree.traverse("preOrder"))
		   System.out.print(node  + " --> ");
	   System.out.println("\n");

	   System.out.println("=========== InOrder ============="); 
	   System.out.println("...using recursion");
	   for(BinaryNode node: tree.traverse("inorder"))
		   System.out.print(node  + " --> ");
	   System.out.println("\n");

	   System.out.println("...using stack");
	   ArrayList<BinaryNode> store = new ArrayList<BinaryNode>();
	   tree.inOrder(store);
	   for(BinaryNode node: store)
		   System.out.print(node  + " --> ");
	   System.out.println("\n");

	   System.out.println("...using morris traversal");
	   store = new ArrayList<BinaryNode>();
	   tree.morrisTraversal(store);
	   for(BinaryNode node: store)
		   System.out.print(node  + " --> ");
	   System.out.println("\n");

	   System.out.println("=========== PostOrder =============");
	   for(BinaryNode node: tree.traverse("postOrder"))
		   System.out.print(node  + " --> ");

   }
   
   

      

}