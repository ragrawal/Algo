package com.questions.array;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem1 extends Problem{
	
	public Problem1(){
		this.solutions.add(new Solution1());
	}

	@Override
	public String question() {
		return "Generate all possible subsets for an array of characters";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, ArrayList<Comparable>> options = new HashMap<String, ArrayList<Comparable>>();
		Read read = new Read(writer, reader);
		ArrayList<Comparable> list = read.strArray("Enter Character (Press q if done)");
		writer.println("Entered Array: ");
		writer.println(Arrays.toString(list.toArray()));
		
		options.put("data", list);
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			ArrayList<Comparable> list = (ArrayList<Comparable>) options.get("data");
			int length = list.size();
		
			
			//For N size, there are 2^N possible subsets
			int max = (int) Math.pow(2.0, length);
			//alternatively int max = 1 << length
			
			for(int i=0; i<max; i++){
				int k = i;
				out.print("\nSubset " + i  + ": ");
				for(int j = 0; j < length; j++){
					if((k & 1)==1) out.print(list.get(j) + ", ");
					k = k >> 1;
				}
					
					
				
			}
			

			
		}

		@Override
		public String describe() {
			return "Uses bit operation ";
		}

		@Override
		public String timeComplexity() {
			return "O(2^N * N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(N)";
		}
		
	}

}
