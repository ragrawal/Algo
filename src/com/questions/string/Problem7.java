package com.questions.string;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem7 extends Problem {

	public Problem7(){
		this.solutions.add(new Solution1());
		this.solutions.add(new Solution2());
	}
	@Override
	public String question() {
		return "Given two strings, find the longest common substring"; 
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Read read = new Read(writer, reader);
		Map<String, String> options = new HashMap<String, String>();
		options.put("str1", read.string());
		options.put("str2", read.string());
		return options;
	}
	
	class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			String str1 = (String) options.get("str1");
			String str2 = (String) options.get("str2");
			
			int substr_length = 0;
			int substr_end = -1;
			int str1_length = str1.length();
			int str2_length = str2.length();
			
			int[][] score = new int[str1_length][str2_length];
			for(int i=0; i<str1_length; i++){
				for(int j=0; j<str2_length; j++){
					if(str1.charAt(i) == str2.charAt(j))
						score[i][j] = (i==0 || j == 0) ? 1 : 1+score[i-1][j-1];
					
					if(score[i][j] > substr_length){
						substr_length = score[i][j];
						substr_end = i;
					}
				}
			}
			if(substr_length == 0)
				out.print("No common substring found");
			else
				out.print("Longest Common Substring: " + str1.substring(substr_end-substr_length+1, substr_end+1));
		}

		@Override
		public String describe() {
			return "Uses dynamic programming to compute longest substring. The idea \n" +
					"is similar to edit distance calculation but only tracks diagonal \n" +
					"elements when computing substring length";
					
		}

		@Override
		public String timeComplexity() {
			return "O(nm)";
		}

		@Override
		public String spaceComplexity() {
			return "O(nm)";
		}
		
	}
	
	class Solution2 implements Solution{
		@Override
		public void execute(Map options, PrintWriter out) {
			String str1 = (String) options.get("str1");
			String str2 = (String) options.get("str2");
			
			int substr_length = 0;
			int substr_end = -1;
			int str1_length = str1.length();
			int str2_length = str2.length();
			
			int[] score = new int[str2_length];
			for(int i=0; i<str1_length; i++){
				for(int j=str2_length-1; j>=0; j--){
					if(str1.charAt(i) == str2.charAt(j))
						score[j] = (i==0 || j == 0) ? 1 : 1+score[j-1];
					
					if(score[j] > substr_length){
						substr_length = score[j];
						substr_end = i;
					}
				}
			}
			if(substr_length == 0)
				out.println("No common substring found\n");
			else
				out.println("Longest Common Substring: " + str1.substring(substr_end-substr_length+1, substr_end+1) + "\n");
			out.flush();
		}

		@Override
		public String describe() {
			return "Similar to previous solution but improves space complexity \n" +
					"It only stores last one row rather than all the rows";
					
		}

		@Override
		public String timeComplexity() {
			return "O(n)";
		}

		@Override
		public String spaceComplexity() {
			return "O(nm) -- \n Note space complexity can be further reduced \n" +
					"to O(n) by storing only last row";
		}	
	}
	
	

}
