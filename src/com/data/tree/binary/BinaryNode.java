package com.data.tree.binary;

public class BinaryNode {
	protected BinaryNode left;
	protected BinaryNode right;
	protected BinaryNode parent;
	protected Comparable key;
	protected Object value;
	
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
	
	
	//**********************************
	// GETTER AND SETTERS
	//**********************************
	public Comparable getKey(){ return this.key; }
	public Object getValue(){ return this.value;}
	public BinaryNode getLeft(){ return this.left; }
	public BinaryNode getRight() { return this.right; }
	
	public void setValue(Object value){
		this.value = value;
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
	
	public int children(){
		int count = 0;
		if(left != null) count++;
		if(right != null) count++;
		return count;
	}
	
	
	public void delete(){
		this.parent = null;
		this.left = null;
		this.right =  null;
	}
	
	//**********************************
	// UTILITY METHODS
	//**********************************
	public String toString(){
		return key.toString() + ":" + value.toString();
	}
	
}
