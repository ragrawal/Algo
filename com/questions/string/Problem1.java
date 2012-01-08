package com.questions.string;

import com.questions.Problem;
import com.questions.Solution;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem1 extends Problem{
	public Problem1(){
		super();
		this.solutions.add(new Solution1());
		this.solutions.add(new Solution2());
	}

	public String question(){
		return "Implement an algorithm to determine if a string has all unique characters.";
	}
	
	public Map readParameters() throws Exception{
		Map<String, String> options = new HashMap<String, String>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);

		System.out.print("Enter String: ");
		options.put("str", reader.readLine());
	
		return options;
		
	}
	
	public class Solution2 implements Solution{
		public String describe(){
			return "Assumes that we are only concerned with a to z characters. Uses a bit vector to keep track of characters that are seen.";
		}
		
		public String timeComplexity(){ return "O(N)"; }
		public String spaceComplexity(){ return "O(1)"; }
		
		public void execute(Map options){
			String str = (String) options.get("str");
			int checker = 0;
			for(int i=0; i < str.length(); i++){
				int val = str.charAt(i) - 'a';
				if((checker & (1 << val)) > 0){
					System.out.println("String characters are unique");
					return;
				}
				checker |= (1 << val);
			}
			System.out.println("String characters are unique");
			return;
		}
		
	}
	
	public class Solution1 implements Solution{
		public void execute(Map options){
			String str = (String) options.get("str");
			// intialize boolean vector
			boolean[] seen = new boolean[256];
			
			for(int i=0; i < str.length(); i++){
				int c = str.charAt(i);
				if(seen[c]){
					System.out.println("String characters are not unique");
					return;
				} 
				seen[c] = true;
			}
			
			System.out.println("String characters are unique.");
			return;
			
		}
		
		public String describe(){
			return "Initializes an empty boolean vector of length 256 (assuming ASCII) and set everything to false intially. For each character, check if corresponding index is true or not. If true then string contains duplicates. Otherwise set the corresponding index to true.";
		}
		
		public String timeComplexity(){
			return "O(N^2)";
		}
		
		public String spaceComplexity(){
			return "O(1)";
		}
		
	}

	
}