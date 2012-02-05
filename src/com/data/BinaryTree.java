package com.data;

public class BinaryTree {
	
	Node head;
	
	public void add(Comparable key){
		
	}
	
	public class Node{
		Node left, right;
		Comparable value;
		
		public Node(){}
		public Node(Comparable value){
			this.value = value;
		}
		public Node(Comparable value, Node left, Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
		public void setLeft(Node left){ this.left = left; }
		public void setRight(Node right){ this.right = right; }
		public void setValue(Comparable value){ this.value = value;}
		
		public Node left(){ return left; }
		public Node right() { return right; }
		public Comparable value(){ return value; } 
	}

}
