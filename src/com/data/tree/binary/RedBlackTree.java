package com.data.tree.binary;

import java.util.ArrayList;

/*
 * RedBlackTree is a BinarySearchTree with the following additional conditions
 * 1. Root and Leaf nil nodes are always black
 * 2. Black Height of all path is same
 * 3. Red Node can only have black children
 */
public class RedBlackTree extends BinarySearchTree {
	
	public RedBlackTree(){
		super();
	}
	
	/*
	 * While adding a node, do a normal insert and then take care of any possible 
	 * violations. Since the new node is always black, the only violation that 
	 * can occur is having two red nodes adjacent to each other. To overcome 
	 * this violation, we will switch colors and/or perform rotations. There are three
	 * cases
	 * 1. Parent is left child of grand parent
	 * 		1. Uncle is Red
	 * 		2. X is Right Child of Parent
	 * 		3. X is Left Child of Parent
	 * 1. Parent is Red and Uncle is Red
	 * 2. Parent is Red and Uncle is Left Child
	 * 3. Parent is Red and Uncle is Right Child
	 * 
	 */
	public void add(BinaryNode node){
		RBNode rb = new RBNode(node);
		System.out.println(rb);
		super.add(rb);
		while(this.root != rb && ((RBNode) rb.parent).isBlack == false){
			RBNode parent = ((RBNode) rb.parent);
			RBNode gp = ((RBNode) parent.parent);
			
			if (gp.left == parent){
				RBNode uncle = (RBNode) gp.right;
				//Case I -- Parent and Uncle Both are Red
				if(uncle != null && !uncle.isBlack){
					parent.isBlack = true;
					uncle.isBlack = true;
					gp.isBlack = false;
					rb = gp;
				}
				else{
					//Case II -- Node Parent is Right Child 
					if(parent.right == rb) leftRotate(rb);
					//Case III -- Left Child
					parent.isBlack = true;
					gp.isBlack = false;
					rightRotatate(gp);
				}
			}
			else{
				RBNode uncle = (RBNode) gp.left;
				if(uncle != null && !uncle.isBlack){
					parent.isBlack = true;
					uncle.isBlack = true;
					gp.isBlack = false;
					rb = gp;
				}else{
					if(parent.left == rb) rightRotatate(rb);
					parent.isBlack = true;
					gp.isBlack = false;
					leftRotate(gp);
				}
				
			}
		}
		
		//always make sure root node is black
		((RBNode) this.root).isBlack = true;
		
	}
	
	   public static void main(String[] args){
		   RedBlackTree tree = new RedBlackTree();

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
		   

		  

	   }
	

	
	


}
