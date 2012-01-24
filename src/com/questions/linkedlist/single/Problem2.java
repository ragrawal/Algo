package com.questions.linkedlist.single;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.data.SingleLinkedList;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem2 extends Problem{

	public Problem2(){
		super();
		this.solutions.add(new Solution1());
	}
	@Override
	public String question() {
		return "Reverse a single linked list";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
		Read read = new Read(writer, reader);
		SingleLinkedList ssl = read.singleLinkedList();
		ssl.print(writer);
		options.put("data", ssl);
		
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter writer) {
			SingleLinkedList ssl = (SingleLinkedList) options.get("data");
			
			//Sanity checks
			if(ssl.length() < 2) return;
			ssl.reverse();
			writer.print("Reverse Linked List: "); ssl.print(writer);
			
			
			
		}

		@Override
		public String describe() {
			return "Use three pointers to keep track of" +
					"prev, current adn next node" +
					"next. At the last set head to previous. ";
		}

		@Override
		public String timeComplexity() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String spaceComplexity() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	

}
