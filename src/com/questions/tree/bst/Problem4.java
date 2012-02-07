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

public class Problem4 extends TreeProblem{
	
	public Problem4(){
		super();
		this.solutions.add(new Solution1());
	}

	@Override
	public String question() {
		return "Verify whether the given tree is a binary search tree";
	}

	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, BinarySearchTree> options = new HashMap<String, BinarySearchTree>();
		Read read = new Read(writer, reader);
		BinarySearchTree tree = read.randomBinaryTree();
		
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			BinarySearchTree tree = (BinarySearchTree) options.get("tree");
			//out.println("Max depth is = " + depth(tree.getHead()));			
		}
		
	
		private boolean verify(Node node, Comparable max, Comparable min){
			if(node == null) return true;
			Comparable value = node.value();
			boolean minStatus = (min == null || value.compareTo(min) >= 0);
			boolean maxStatus = (max == null || value.compareTo(max) <= 0);
			
			if(!minStatus) return false;
			if(!maxStatus) return false;
			
			if(!verify(node.left(), min, node.value())) return false;
			if(!verify(node.right(), node.value(), max)) return false;
			
			return true;
		}
		

		@Override
		public String describe() {
			return "Use iterative method to pass node and the maximum and minimum value of the branch" +
					"Note that you just don't need to check child elements but for every element there is " +
					"possible range that is decided based on all the ancestors upto root";
	
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
