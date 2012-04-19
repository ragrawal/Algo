package com.data.tree.binary;

public class RBNode extends BinaryNode {
	protected boolean isBlack = false;

	public RBNode(){ super(); }
	public RBNode(Comparable key, Object value){
		super();
		this.key = key;
		this.value = value;
	}
	public RBNode(BinaryNode node){
		super();
		this.key = node.key;
		this.value = node.value;
		this.left = node.left;
		this.parent = node.parent;
		this.right = node.parent;

	}
}
