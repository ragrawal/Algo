package com.problems;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1 extends Problem{
	public Problem1(){
		super();
		// TODO: add all possible solutions
		//this.solutions.add(new Solution1());
	}

	public String question(){
		return "Find the least common ancestore of two nodes in a binary tree. A least common ancestor is a node that of which both the give nodes are descendant.";
	}
	
	public Map readParameters() throws Exception{
		/* 
		//Read require parameters from command line. 
		
		Map<String, String> options = new HashMap<String, String>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);

		System.out.print("Enter String: ");
		options.put("str", reader.readLine());
	
		return options;
		*/
		return null;
	}
	
	
	
	public class Solution1 implements Solution{
		public void execute(Map options){
			//TODO: write your function over here
			return;
			
		}
		
		public String describe(){
			//TODO: descibe your solution and its limitation
			return "Explain the solution over here";
		}
		
		public String timeComplexity(){
			//TODO: return time complexity of the solution
			return "O(N^2)";
		}
		
		public String spaceComplexity(){
			//TODO: return space complexity of the solution
			return "O(1)";
		}
		
	}

	
}