package com.questions.tree.bst;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.data.BinaryTree;
import com.data.BinaryTree.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;


public class Problem1 extends Problem{

	public Problem1(){
		super();
		this.solutions.add(new Solution1());
	}
	@Override
	public String question() {
		return "Print every level in a tree in its own " +
				"line.";
	}

	@Override
	public Map readParameters() throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
        
		Read read = new Read();

		BinaryTree tree = null;
		String treetype = read.string("Tree type (Manual/Random): ");
		tree = ("manual".equalsIgnoreCase(treetype)) ? 
			read.binaryTree() : 
			read.randomBinaryTree();
			
		System.out.println("========== GENERATE TREE ==============");
		tree.print();
		System.out.println("========== END OF TREE ==============");
		options.put("tree", tree);
		
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options) {
			BinaryTree bt = (BinaryTree) options.get("tree");
			Queue<Object> queue = new LinkedList<Object>();
			
			queue.add(bt.getHead());
			queue.add(null);
		
			while(!queue.isEmpty()){
				
				Object current = queue.poll();
				if(current == null){
					System.out.println();
					System.out.println("Size = " + queue.size());
					// KEY STEP: BOUNDARY CONDITION 
					if(queue.peek() != null) queue.add(null);
				}
				else{
					Node node = (Node) current;
					System.out.print(", " + node.value());
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
