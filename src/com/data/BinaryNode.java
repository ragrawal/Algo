package com.data;

public class BinaryNode {
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;
	Comparable key;
	Object value;
	
	//**********************************
	// CONSTRUCTOR 
	//**********************************
	public BinaryNode(){}
	public BinaryNode(Comparable key){
		this.key = key;
	}
	public BinaryNode(Comparable key, Object value){
		this.key = key;
		this.value = value;
	}
	public BinaryNode(Comparable key, Object value, BinaryNode left, BinaryNode right){
		this.key = key;
		this.value = value;
		setLeft(left);
		setRight(right);
	}
	
	
	public void setLeft(BinaryNode left){
		//remove parent pointer of any existing child from point
		//to the current node
		if(this.left != null){ this.left.parent = null; }
		//attach new child
		this.left = left;
		//change parent of the child to point to current node
		if(this.left != null) this.left.parent = this;
	}
	
	public void setRight(BinaryNode right){
		//remove parent pointer of any existing child from point
		//to the current node
		if(this.right != null) this.right.parent = null;
		//attach new child
		this.right = right;
		//change parent of the child to point to current node
		if(this.right != null) this.right.parent = this;
	}
	
	public String toString(){
		return key.toString() + ":" + value.toString();
	}
	
}
