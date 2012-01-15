package com.questions;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import com.util.Read;

public class Problem4 extends Problem{
	public Problem4(){
		super();
		this.solutions.add(new Solution1());
	}

	public String question(){
		return "Write a function to get all possible subsets (Powerset problem). Hint: number of subsets will be equal to 2^n where n is the number of elements";
	}
	
	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception{
		Map<String, Object> options = new HashMap<String, Object>();
		Read read = new Read(writer, reader);
		ArrayList<Comparable> items = read.strArray("Enter element (press q if done):");
		options.put("array", items);
		return options;
	}
	
	public class Solution1 implements Solution{
		
		
		public void execute(Map options, PrintWriter writer){
			ArrayList<Comparable> items = (ArrayList<Comparable>) options.get("array");
			
			//Sanity Check
			int n = items.size();
			if(n >= 64){
			    System.out.println("Error: Array length greater than 64");
			    return;
			}
			
			
			for(long i=0; i < (1<<n); i++){ // run from 0 to 2^n 
				ArrayList<Comparable> subset = new ArrayList<Comparable>();
				for(int j=0; j < n; j++){        //search through n bits
				   if(((i>>j) & 1) == 1)          //left shift i by j and check first bit 
				       subset.add(items.get(j)); // if on then store it
				}
				writer.println(Arrays.toString(subset.toArray()));
			}
			
			/* Simpler Representation
			int powerElements = Math.pow(2, n);
			for(int i=0; i < powerElements; i++){
			
				//conver the binary number to a string containing n digits
				String binary = intToBinary(i, n);
				ArrayList<Comparable> subset = new ArrayList<Comparable>();
				
				for(int j=0; j < binary.length(); j++){
					if(binary.charAt(j) == '1') subset.add(items.get(j));
				}
				
			}
			*/
			
			return;
			
		}
		
		public String describe(){
			return "Uses BitMasking technique to generate all possible subsets. Incrment counter by 1 at each iterature and select elements for which bit value is 1";
		}

		public String timeComplexity(){
			return "polynomical time - O(2^n)";
		}
		public String spaceComplexity(){
			return "2^n";
		}
	}

}