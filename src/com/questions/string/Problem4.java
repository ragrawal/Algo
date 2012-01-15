package com.questions.string;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

import java.util.HashMap;

public class Problem4 extends Problem{

	public Problem4(){
		super();
		this.solutions.add(new Solution1());
	}
	@Override
	public String question() {
		return "Given a large document and a short pattern " +
				"consisting of a few words (eg. W1 W2 W3), find " +
				"the shortest string that has all the words in any " +
				"order (for eg. W2 foo bar dog W1 cat W3 -- is a " +
				"valid pattern)";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
		
		//Read dictionary
		Read read = new Read(writer, reader);
		ArrayList<String> dictionary = new ArrayList<String>();
		String word = null;
		while(true){
			word = read.string("Enter a new word in dictionary(enter q if done):");
			if(read.stop(word)) break;
			dictionary.add(word.toLowerCase());
		}
		options.put("dictionary", dictionary);
		
		//Read large textual document
		String document = read.string("Enter document text:");
		options.put("document", document.toLowerCase());
		
		return options;
	}
	
	public class Solution1 implements Solution{
		int length = 100000;
		int startPos = -1;
		int endPost = -1;
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		int docLength = -1;
		
		@Override
		public void execute(Map options) {
			
			System.out.println("Step 1: Copy dictionary to a hashmap and " +
					"set value to -1");
			ArrayList<String> lst = (ArrayList) options.get("dictionary");
			for(String word: lst) dictionary.put(word, -1);
			
			System.out.println("Step 2: Iterature over document word by word");
			String[] doc = ((String) options.get("document")).split("\\s+");
			docLength = doc.length;
			
			
			// set initial length greater than docLength
			length = docLength + 100;
			
			for(int i=0; i<docLength; i++){
				String word = doc[i];
				if(!dictionary.containsKey(word)) continue;
				System.out.println("...Update position of " + word + " to " + i + " position");
				dictionary.put(word, i);
				int curLength = getSubStringLength();
				length = (curLength != -1 && curLength < length) ? curLength : length;
			}
			
			System.out.println("Substring Length: " + length);
			if(length > docLength){
				System.out.println("Failed to find all words.");
			}else{
				Integer[] minMax = getMinMax();
				System.out.print("Substring: ");
				for(int i=minMax[0]; i <= minMax[1]; i++)
					System.out.print(doc[i]);
			}
		}
		
		private Integer[] getMinMax(){
			Set<String> keys = dictionary.keySet();
			int min = docLength + 10;
			int max = -1;
			for(String key: keys){
				int idx = dictionary.get(key).intValue();
				if(idx == -1) return null;
				min = (idx < min) ? idx : min;
				max = (idx > max) ? idx : max;
			}
			return new Integer[]{min, max};
		}
		
		private int getSubStringLength(){
			Integer[] minMax = getMinMax();
			if(minMax == null) return -1;
			return minMax[1]-minMax[0];
		}

		
		@Override
		public String describe() {
			return "Use a hash to store dictionary words as key " +
					"and initialize -1 as value. Now iterate word by word " +
					"and check if the word exist in the hash. If the word" +
					"exists then check its corresponding value in hash. If " +
					"its -1 then set it to the index of the word. If its " +
					"greater than -1 then check if all the other words in hash" +
					"has value more than -1. If yes then calculate the lenght" +
					"of the string by taking difference of min and max index value." +
					"Keep track of length and corresponding index numbers. Limitations: Can't handle multiple words dictionary tokens.";
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
