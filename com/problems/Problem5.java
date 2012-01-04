package com.problems;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem5 extends Problem{
	public Problem5(){
		super();
		// TODO: add all possible solutions
		//this.solutions.add(new Solution1());
	}

	public String question(){
		//TODO: describe question
		return "Write a function to find the largest number in a 2 dimensional matrix where each row and column are sorted.";
	}
	
	public Map readParameters() throws Exception{
		return null;
		
	}
	
	
	
	public class Solution1 implements Solution{
		public void execute(Map options){
			//TODO: write your function over here
			return;
			
		}
		
		public String describe(){
			//TODO: descibe your solution and its limitation
			return "Initializes an empty boolean vector of length 256 (assuming ASCII) and set everything to false intially. For each character, check if corresponding index is true or not. If true then string contains duplicates. Otherwise set the corresponding index to true.";
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