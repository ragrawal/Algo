package com.questions.tree.bst;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.data.BinarySearchTree;
import com.data.BinarySearchTree.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem3 extends Problem{

	public Problem3(){
		this.solutions.add(new Solution1());
	}
	
	@Override
	public String question() {
		return "Find maximum depth of the binary search tree";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Read read = new Read(writer, reader);
		BinarySearchTree tree = read.randomBinaryTree();
		writer.println("Tree: ");
		tree.print(writer);
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("tree", tree);
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = (BinarySearchTree) options.get("tree");
			out.println("Max depth is = " + depth(tree.getHead()));			
		}
		
		private int depth(Node node){
			if(node == null) return 0;
			int leftDepth = depth(node.left());
			int rightDepth = depth(node.right());
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
		
		private int depth(Node node){
			if(node == null) return 0;
			int leftDepth = depth(node.left());
			int rightDepth = depth(node.right());
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
