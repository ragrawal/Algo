package com.questions.linkedlist.single;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.data.SingleLinkedList;
import com.data.SingleLinkedList.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem6 extends Problem {
	
	public Problem6(){
		this.solutions.add(new Solution1());
	}

	@Override
	public String question() {
		return "M-to-last Problem\n" +
				"Given a single linked list devise a time and space efficient algorithm \n" +
				"to find the mth to last element of the list. Define mth to last \n" +
				"such that when m = 0, the last element of the list is returned.";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Read read = new Read(writer, reader);
		Map<String, Object> options = new HashMap<String, Object>();
		
		SingleLinkedList list = read.randomSingleLinkedList();
		list.print(writer);
		options.put("m", read.integer("Enter M: "));
		options.put("data", list);
		return options;
	}
	
	class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			SingleLinkedList list = (SingleLinkedList) options.get("data");
			Integer m = (Integer) options.get("m");
			
			Node pointer = list.getFirst();
			//Increment pointer to length-m position
			for(int i=0; i<m & pointer != null; i++)
				pointer = pointer.getNext();
				
			if(pointer == null){
				out.println("Error: Invalid M");
				return;
			}
			
			Node mPointer = list.getFirst();
			while((pointer = pointer.getNext()) != null)
				mPointer = mPointer.getNext();
			
			out.println("M-to-last = " + mPointer.toString());
		}

		@Override
		public String describe() {
			return "Uses two pointer. Increment first pointer to Mth index and then \n" +
					"start incrementing both the pointers";
			
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
