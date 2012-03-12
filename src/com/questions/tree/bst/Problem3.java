package com.questions.tree.bst;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.data.tree.binary.BinaryNode;
import com.data.tree.binary.BinarySearchTree;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem3 extends TreeProblem{

	public Problem3(){
		super();
		this.solutions.add(new Solution1());
	}
	
	@Override
	public String question() {
		return "Find maximum depth of the binary search tree";
	}

	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = (BinarySearchTree) options.get("tree");
			out.println("Max depth is = " + depth(tree.getRoot()));			
		}
		
		private int depth(BinaryNode node){
			if(node == null) return 0;
			int leftDepth = depth(node.getLeft());
			int rightDepth = depth(node.getRight());
			return 1 + ((leftDepth > rightDepth) ? leftDepth : rightDepth);	
		}

		@Override
		public String describe() {
			return "Use recursion to calcualte left and right " +
					"depth and return 1+max of the two";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(1)";
		}
		
	}
	
	public class Solution2 implements Solution{
		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = (BinarySearchTree) options.get("tree");
		
		}
		
		private int depth(BinaryNode node){
			if(node == null) return 0;
			int leftDepth = depth(node.getLeft());
			int rightDepth = depth(node.getRight());
			return 1 + ((leftDepth > rightDepth) ? leftDepth : rightDepth);	
		}

		@Override
		public String describe() {
			return "Use recursion to calcualte left and right " +
					"depth and return 1+max of the two";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(1)";
		}
	}
	

}
