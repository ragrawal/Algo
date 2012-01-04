package com.problems;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Problem4 extends Problem{
	public Problem4(){
		super();
		this.solutions.add(new Solution1());
	}

	public String question(){
		return "Write a function to get all possible subsets (Powerset problem). Hint: number of subsets will be equal to 2^n where n is the number of elements";
	}
	
	public Map readParameters() throws Exception{
		Map<String, Object> options = new HashMap<String, Object>();
		ArrayList<Comparable> items = new ArrayList<Comparable>();
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		final String quit = "q";
		while(true){
			System.out.print("Enter element (press q if done): ");
			String item = reader.readLine();
			if(quit.equalsIgnoreCase(item)) break;
			items.add(item);
		}
		
		options.put("array", items);
		return options;
		
	}
	
	public class Solution1 implements Solution{
		
		public void execute(Map options){
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
				System.out.println(Arrays.toString(subset.toArray()));
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