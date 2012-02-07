package com.questions.tree.bst;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import com.data.BinarySearchTree;
import com.data.BinarySearchTree.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem5 extends TreeProblem{
	
	public Problem5(){
		this.solutions.add(new Solution1());
		this.solutions.add(new Solution2());
	}

	@Override
	public String question() {
		return "Return mirror of the tree (swap left to right).";
	}

	
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = ((BinarySearchTree) options.get("tree")).copy();
			LinkedList<Node> queue = new LinkedList<Node>();
			
			queue.add(tree.getHead());
			
			while(!queue.isEmpty()){
				Node current = queue.removeFirst();
				if(current == null) continue;
			
				
				//swap pointers
				Node tmp = current.left();
				current.setLeft(current.right());
				current.setRight(tmp);
				
				// add left and right to queue
				queue.add(current.left());
				queue.add(current.right());
			}
			
			out.println("============ TREE MIRROR ====================");
			tree.print(out);
			out.flush();
			
		}

		@Override
		public String describe() {
			return "In place swapping of left to right. Uses queue to traverse nodes.";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(log(N,2)^2)";
		}
		
	}
	
	public class Solution2 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = ((BinarySearchTree) options.get("tree")).copy();
			BinarySearchTree mirror = new BinarySearchTree();
			mirror.setHead(copyAndSwap(tree.getHead()));
			
			out.println("============ TREE MIRROR ====================");
			mirror.print(out);
			out.flush();
		}
		
		private Node copyAndSwap(Node current){
			if(current == null) return null;
			return (new BinarySearchTree()).new Node(current.value(), copyAndSwap(current.right()), copyAndSwap(current.left()));
		}

		@Override
		public String describe() {
			return "Create a deep copy of the tree and while creating the copy swap left and right nodes";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(N)";
		}
		
	}
	
	

}
