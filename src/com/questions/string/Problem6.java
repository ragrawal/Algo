package com.questions.string;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Soundbank;

import com.questions.Problem;
import com.questions.Solution;

public class Problem6 extends Problem{

	public Problem6(){
		this.solutions.add(new Solution1());
	}
	@Override
	public String question() {
		return "Return all permutations of a string. For instance" +
				"if HAT is a string, the output is hat, ath, tha, hta, tah, aht";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, String> options = new HashMap<String, String>();
		writer.print("Enter String: "); 
		writer.flush();
		options.put("str", reader.readLine());
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			String input = (String) options.get("str");
			ArrayList<String> store = new ArrayList<String>();
			permutation("", input, store);
			for(String s : store)
				out.println(s);
			
		}
		
		private void permutation(String prefix, String suffix, ArrayList store){
			if(suffix.length() == 0){
				store.add(prefix);
				return;
			}
			
			int n = suffix.length();
			for(int i=0; i< n; i++){
				String np = prefix + suffix.charAt(i);
				String suf = suffix.substring(0,  i) + suffix.substring(i+1, n);
				permutation(np, suf, store);
			}
			return;
		}

		@Override
		public String describe() {
			return "Recursively call permutation method that three inputs: prefix string, suffix string and storage" +
					". In each iteration, increment prefix by adding one character from suffix string and " +
					"when suffix is empty add the prefix to the storage";
		}

		@Override
		public String timeComplexity() {
			return "O(N!)";
		}

		@Override
		public String spaceComplexity() {
			return "N!";
		}
		
	}
	
	public static void main(String[] args){
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("str","hat");
		Problem s = new Problem6();
		for(int i=0; i<s.solutionCount(); i++){
			Solution solution = s.getSolution(0);
			solution.execute(options, new PrintWriter(System.out));
		}
		
		
	}

}
