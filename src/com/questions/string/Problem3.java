package com.questions.string;

import com.questions.Problem;
import com.questions.Solution;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.algo.sort.BubbleSort;
import java.util.Arrays;

public class Problem3 extends Problem{
	public Problem3(){
		super();
		this.solutions.add(new Solution1());
	}

	public String question(){
		return "Write a method to decide if two strings are anagrams or not";
	}
	
	public Map readParameters() throws Exception{
		Map<String, String> options = new HashMap<String, String>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);

		System.out.print("Enter String 1: ");
		options.put("str1", reader.readLine());

		System.out.print("Enter String 2: ");
		options.put("str2", reader.readLine());
		
	
		return options;
		
	}
	
	public class Solution1 implements Solution{
		
		private String process(String str){
			str = str.toLowerCase().trim();
			return str.replaceAll("[^a-zA-Z0-9]", "");
			
		}
		public void execute(Map options){
			
			String[] str1 = process((String) options.get("str1")).split("");
			String[] str2 = process((String) options.get("str2")).split("");
			
			if(str1.length != str2.length ){
				System.out.println("Two strings are not anagrams");
				return;
			}
			
			//Sort two strings
			BubbleSort sort = new BubbleSort();
			sort.sort(str1);
			sort.sort(str2);
			System.out.println("Sorted String1: " + Arrays.toString(str1));
			System.out.println("Sorted String2: " + Arrays.toString(str2));
			
			
			//check if elements are same
			int length = str1.length;
			for(int i=0; i< length; i++){
				if(str1[i].compareTo(str2[i]) != 0){
					System.out.println("Two strings are not anagrams");
					return;
				}
			}
			
			System.out.println("Two strings are anagrams");
			return;
			
		
			
		}
		public String describe(){
			return "First check if two strings are of same length or not. If they, sort the two strings and compare them";
			
		}
		public String timeComplexity(){
			return "O(N * Log N)";
		}
		public String spaceComplexity(){
			return "O(1)";
		}
	}
}