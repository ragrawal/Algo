package com.data;

import java.util.Stack;

public class BinaryTree{
	private Node root = null;
	private int order = 0;

	public String describe(){	return "Binary Tree"; }
	
	public BinaryTree(){}
	
	public void add(int value){ insert(value); }
	public void insert(int value){
		Node node = new Node(value);
		if(root == null){
			root = node;
		}else{
			Node parent = null;
			Node current = root;
			while(current != null){
				parent = current;
				if(value < current.data) current = current.right;
				else current = current.left;
			}
			if(parent.data >= value) parent.right = node;
			else parent.left = node;
		}
	}
	
	/* Search and Retrieve */
	public boolean contains(int value){ return find(value) != null; }
	public boolean contains(int value, String method){
		if(method.equalsIgnoreCase("recursion"))
			return findUsingRecursion(root, value) != null;
		return find(value) != null;
	}
	public Node find(int value){
		Node current = root;
		while(current != null && current.data != value){
			if(value < current.data) current = current.right;
			else current = current.left;
		}
		return current;
	}
	public Node findUsingRecursion(Node node, int value){
		if(node == null) return null;
		if(node.data == value) return node;
		if(value < node.data) return findUsingRecursion(node.right, value);
		return findUsingRecursion(node.left, value);
	}
	
	public Node delete(int value){
		Node current = root;
		Node parent;
		boolean isRightChild = false;
		
		while(current != null && current.data != value){
			parent = current;
			if( value < current.data) {
				current = current.right;
				isRightChild = true;
			}
			else{
				current = current.left;
				isRightChild =false;
			}
		}
		if(current == null) return null;
		
		//No children
		if(current.left == null && current.right == null){
			if(current == root) root = null;
			else if(isRightChild) parent.right = null;
			else parent.left = null;
		}
		// both children exists
		else if(current.right != null && current.left != null){
			
			// find successor 
			Node successor = getSuccessor(current);
			
			// delete node
			if(current = root) root = successor;
			else if(isRightChild) parent.right = successor
			else parent.left = successor;
			successor.right = current.right;
			
		}
		//only left exists;
		else if(current.right == null){
			if(current == root) root = current.left;
			else if(isRightChild) parent.right = current.left
			else parent.left = current.left
		}
		//only right exists;
		else if(current.left == null){
			if(current == root) root = current.right;
			else if(isRightChild) parent.right = current.right;
			else parent.left = current.right;
		}
	}
	
	private Node getSuccessor(Node node){
		Node successorParent = node;
		Node successor = node;
		Node current = node.left;
		while(current != null){
			successorParent = successor
			successor = current;
			current = current.right;
		}
		
		if(successor != node.left){
			successorParent.right = successor.left;
			successor.left = node.left;
		}
		
		return successor;
	}
	
	public void remove(Node node){
		
	}
	
	private int noOfChildren(Node){
		if(node == null) return -1;
		int count = 2;
		if(node.left == null) count--;
		if(node.right == null) count--;
		return count;
	}
	
	
	/* Three ways to traver tree */
	public void traverse(String method){
		if(method.equalsIgnoreCase("inorder")) 
				inOrder(root);
		return;
	}
	public void inOrder(Node node){
		if(node == null) return;
		inOrder(node.right);
		System.out.println(node.data);
		inOrder(node.left); 
	}
	public void preOrder(Node node){
		if(node == null) return;
		System.out.println(node.data);
		preOrder(node.right);
		preOrder(node.left);
	}
	public void postOrder(Node node){
		if(node == null) return;
		preOrder(node.right);
		preOrder(node.left);
		System.out.println(node.data);

	}
	
	
	
		
	
	public void print(){
		if(root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		/* Start depth first traverse */
		Stack<PNode> nodes = new Stack<PNode>();
		nodes.push(new PNode(root, 0));
		while(!nodes.empty()){
			PNode pnode = nodes.pop();
			Node node = pnode.node;
			int level = pnode.level;
			// print space
			for(int i = 0; i < (2*level)-1; i++) System.out.print(" ");
			if(level > 0) System.out.print("|-");
			System.out.println(node.data);
			if(node.left != null) nodes.push(new PNode(node.left, level+1));
			if(node.right != null) nodes.push(new PNode(node.right, level+1));
		}
	}
	
	private class PNode{
		int level;
		Node node;
		public PNode(Node node, int level){
			this.level = level;
			this.node = node;
		}
	}

	private class Node{
		int data;
		Node left;
		Node right;
		
		/* Constructor */
		public Node(int value){ data = value; }
		public Node(int value, Node left, Node right){
			data = value;
			left = left;
			right = right;
		}
		
		
	}
}