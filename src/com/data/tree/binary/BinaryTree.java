package com.data.tree.binary;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;




public class BinaryTree {
	protected BinaryNode root;
	
	//CONSTRUCTOR
	public BinaryTree(){}
	public BinaryTree(BinaryNode root){
		this.root = root;
	}
	
	public BinaryNode getRoot(){
		return this.root;
	}
	
	//Tree Attributes
	public int getNumberOfNodes(){ return 0;}
	public int getMaxHeight(){
		return 0;
	}
	
	//Traverse
	public ArrayList<BinaryNode> inOrder(){ return traverse("inOrder"); } 
	public ArrayList<BinaryNode> preOrder(){ return traverse("preOrder"); }
	public ArrayList<BinaryNode> postOrder() { return traverse("postOrder"); }
	public ArrayList<BinaryNode> levelOrder() { return traverse("levelOrder"); }
	
	
	//****************************************************
	//  IMPLEMENTATION OF ROTATION ALGORITHS
	//        |                                       |
	// 	      y                                       x
	//  	 / \                                     / \
	//      x   C  ----Right ROTATE ABOUT Y --->    A   y
	//     / \     <----left  ROTATE ABOUT x ---       / \
	//    A   B                                       B   C
	//****************************************************
	public void rightRotatate(BinaryNode y){
		BinaryNode x;
		if(y == null || (x = y.left) == null) return;
		
		//Change Parents
		//Concerns: 
		//    1: Parent can be null, 
		//    2. also take care of left or right child
		BinaryNode parent = y.parent;
		if (parent == null){
			this.root = x;   /* KEY CONDITION - ALWAYS FORGET */
		}
		else if (parent.left.key.compareTo(y.key) == 0)
			//Note: BinaryNode implementation takes care of setting x.parent = parent
			parent.setLeft(x);  
		else 
			//Note: BinaryNode implementation takes care of setting x.parent = parent
			parent.setRight(x); 
		
		//Take care of B
		y.setLeft(x.right);
		x.setRight(y); //BinaryNode takes care of setting y.parent = x

	}
	
	public void leftRotate(BinaryNode x){
		BinaryNode y;
		if(x == null || (y = x.right) == null) return;
		
		BinaryNode parent = x.parent;
		y.parent = parent;
		x.parent = y;
		
		if (parent == null)
			this.root = y;
		else if(parent.left.key.compareTo(x.key) == 0) 
			/* KEY CONDITION -- MAKE SURE TO MATCH WITH PIVOT NODE */ 
			parent.left = y;
		else {
			System.out.println("Right Brnach");
			parent.right = y;
		}
	
		if((x.right = y.left) != null) y.left.parent = x;
		y.left = x;
		x.parent = y;
				
	}

	//****************************************************
	//  UTILITY FUNCTIONS
	//****************************************************	

	public BinaryTree copy(){
		return new BinaryTree(copy(this.root));
	}
	
	protected BinaryNode copy(BinaryNode node){
		if (node == null) return null;
		BinaryNode duplicate = new BinaryNode(node.key, node.value);
		duplicate.left = copy(node.left);
		duplicate.right = copy(node.right);
		return duplicate;
	}
	
	public boolean empty(){
		return (this.root == null);
	}
	
	public int size(){
		int count = 0;
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		stack.add(this.root);
		BinaryNode current = null;
		while(!stack.empty()){
			current = stack.pop();
			count++;
			if(current.right != null) stack.push(current.right);
			if(current.left != null) stack.push(current.left);
		}
		return count;
	}
	
	public int maxDepth(){
		return maxDepth(this.root, 1, 0);
	}
	
	private int maxDepth(BinaryNode node, int level, int largest){
		if(node == null) return level-1;
		if(node.children() == 0) return ((level > largest) ? level : largest);
		int leftDepth = maxDepth(node.left, level+1, largest);
		int rightDepth = maxDepth(node.right, level+1, largest);
		return ((leftDepth > rightDepth) ? leftDepth : rightDepth);
	}
	
	public int maxDepthWithoutRecursion(){
		class NodeDepth{
			BinaryNode node;
			int depth;
			NodeDepth(BinaryNode node, int depth){
				this.node = node;
				this.depth = depth;
			}
		}
		int maxDepth = 0;
		Stack<NodeDepth> stack = new Stack<NodeDepth>();
		stack.add(new NodeDepth(this.root, 1));
		NodeDepth current = null;
		
		while(!stack.isEmpty()){
			current = stack.pop();
			if(current.node.children() == 0)
				maxDepth = (current.depth > maxDepth) ? current.depth : maxDepth;
			if(current.node.left != null) 
				stack.push(new NodeDepth(current.node.left, current.depth+1));
			if(current.node.right != null)
				stack.push(new NodeDepth(current.node.right, current.depth+1));	
		}
		return maxDepth;
	}
	
	
	
	//****************************************************
	//  TREE DISPLAY ALGORITHMS
	//****************************************************	
	public void print(){ 
		print(this.root, 0, new PrintWriter(System.out, true));
	}
	public void print(PrintWriter writer){
	    print(this.root, 0, writer);	
	}
	
	/*
	 * Uses recursive depth first traversal to print the tree
	 * Each call passes depth of the node to decide number of 
	 * spaces to be left. 
	 */
	protected void print(BinaryNode node, int level, PrintWriter writer)
	{
		if(node == null) return;
		for(int i=0; i < 2*(level-1); i++) writer.print(" ");
		if(level > 0) writer.print("|-");
		writer.println(node); 
		print(node.left, level+1, writer);
		print(node.right, level+1, writer);
	}
	   
	
	//****************************************************
	//  IMPLEMENTATION OF VARIOUS TRAVERSAL ALGORITHMS
	//****************************************************	
	protected ArrayList<BinaryNode> traverse(String method){
		ArrayList<BinaryNode> nodes = new ArrayList<BinaryNode>();
		if(method.equalsIgnoreCase("inorder")) inOrder(this.root, nodes);
		else if(method.equalsIgnoreCase("preorder")) preOrder(this.root, nodes);
		else if(method.equalsIgnoreCase("postorder")) postOrder(this.root, nodes);
		else if(method.equalsIgnoreCase("levelorder")) levelOrder(nodes);
		return nodes;
	}
	
	protected void levelOrder(ArrayList<BinaryNode> store){
		int counter = 0;
		store.add(this.root);
		BinaryNode current;
		while(counter < store.size()){
			current = store.get(counter++);
			if(current.left != null) store.add(current.left);
			if(current.right != null) store.add(current.right);
		}
		
	}
	

	protected void levelOrder(BinaryNode node, ArrayList<BinaryNode> store){
		if(node == null) return;
		store.add(node);
		levelOrder(node.left, store);
		levelOrder(node.right, store);		
	}
	
	protected void preOrder(ArrayList<BinaryNode> store){
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		stack.add(this.root);
		BinaryNode current;
		while((current = stack.pop()) != null){
			store.add(current);
			if(current.right != null) stack.push(current.right);
			if(current.left != null) stack.push(current.left);
		}
	}
	
	protected void preOrder(BinaryNode node, ArrayList<BinaryNode> store){
		if(node == null) return;
		store.add(node);           
		preOrder(node.left, store);
		preOrder(node.right, store);
	}
	
	
	
	// InOrder Traversal using Recursion
	protected void inOrder(BinaryNode node, ArrayList<BinaryNode> store){
		if(node == null) return;
		inOrder(node.left, store);
		store.add(node);
		inOrder(node.right, store);
	}
	
	//InOrder Traversal without recursion
	public void inOrder(ArrayList<BinaryNode> store){
		if(this.root == null) return;
		Stack<BinaryNode> stack = new Stack<BinaryNode>();
		stack.add(this.root);
		BinaryNode current = this.root.left;
		boolean done = false;
		while(!done){
			if(current != null){
				stack.add(current);
				current = current.left;
			}
			else{
				if(!stack.empty()){
					current = stack.pop();
					store.add(current);
					current = current.right;
				}else{
					done = true;
				}
			}
		}

	}
	
	//Morris Traversal InOrder Traversal without recursion and without stack
	protected void morrisTraversal(ArrayList<BinaryNode> store){
		BinaryNode current, pre;
		current = this.root;
		while(current != null){
			if(current.left == null){
				store.add(current);
				current = current.right;
			}
			else{
				// find inorder predecessor of current node 
				// For predecessor - find rightmost node of the left subtree 
				pre = current.left;
				while(pre.right != null && pre.right != current){
					pre = pre.right;
				}
				
				//Make current as right child of its inorder predecessor 
				if(pre.right == null){
					pre.right = current;
					current = current.left;
				}
				
				//Revert the changes made in if part to restore 
				//the original tree
				else{
					pre.right = null;
					store.add(current);
					current = current.right;
				}
			}
		}
	}
	
	/* postorder traversal without using loop -- space efficient */
	protected void postOrder(ArrayList<BinaryNode> store){
		store.add(this.root);
		BinaryNode current;
		int counter = 0;
		while(counter < store.size()){
			current = store.get(counter++);
			if(current.right != null) store.add(current.right);
			if(current.left != null)  store.add(current.left);
		}
		int size = store.size();
		int mid = size/2;
		for(int i=0; i<=mid; i++){
			BinaryNode node = store.remove(i);
			store.add(i, store.remove(size-i));
			store.add(size-i, node);
		}
		return;
	}
	
	protected void postOrder(BinaryNode node, ArrayList<BinaryNode> store){
		if(node == null) return;
		postOrder(node.left, store);
		postOrder(node.right, store);
		store.add(node);
	}
	
	public static void main(String[] args){
		BinaryNode[] nodes = new BinaryNode[10];
		for(int i=0; i<10; i++){
			Integer key = new Integer(i);
			nodes[i] = new BinaryNode(key, key);
		}
		nodes[0].setLeft(nodes[1]);
		nodes[0].setRight(nodes[2]);
		nodes[1].setLeft(nodes[3]);
		nodes[1].setRight(nodes[4]);
		nodes[2].setLeft(nodes[5]);
		nodes[2].setRight(nodes[6]);
		nodes[3].setLeft(nodes[7]);
		nodes[3].setRight(nodes[8]);
		nodes[4].setLeft(nodes[9]);
		
		BinaryTree tree = new BinaryTree(nodes[0]);
		System.out.println("Orignal Tree");
		tree.print();
		
		System.out.println("=========== TREE STATISTICS =============");
		System.out.println("Number of Nodes: " + tree.size());
		System.out.println("Max Depth: " + tree.maxDepth());
		System.out.println("Max Depth (w/o recursion): " + tree.maxDepthWithoutRecursion());
		
		
		
		System.out.println("=========== Left Rotate about " + nodes[1] + "=============");
		tree.leftRotate(nodes[1]);
		tree.print();
		
		System.out.println("===========RIGHT Rotate about " + nodes[1] + "=============");
		tree.rightRotatate(nodes[4]);
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
