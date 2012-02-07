package com.questions.tree.bst;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.data.BinarySearchTree;
import com.data.BinarySearchTree.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;


public class Problem1 extends TreeProblem{

	public Problem1(){
		super();
		this.solutions.add(new Solution1());
	}
	@Override
	public String question() {
		return "Print every level in a tree in its own " +
				"line.";
	}

	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter writer) {
			BinarySearchTree bt = (BinarySearchTree) options.get("tree");
			Queue<Object> queue = new LinkedList<Object>();
			
			queue.add(bt.getHead());
			queue.add(null);
		
			while(!queue.isEmpty()){
				
				Object current = queue.poll();
				if(current == null){
					writer.println();
					//writer.println("Size = " + queue.size());
					// KEY STEP: BOUNDARY CONDITION 
					if(queue.peek() != null) queue.add(null);
				}
				else{
					Node node = (Node) current;
					writer.print(", " + node.value());
					if(node.left() != null) queue.add(node.left());
					if(node.right()!= null) queue.add(node.right());
				}
			}
			
		}
		

		@Override
		public String describe() {
			return "Uses queue and reads tree in breadth first order. " +
					"Uses a dummy variable to mark end of level." +
					"Extract top node from queue and add its children" +
					"to queue after the dummy variable";
		}

		@Override
		public String timeComplexity() {
			return "O(n)";
		}

		@Override
		public String spaceComplexity() {
			return "O(1)";
		}
		
	}

}
