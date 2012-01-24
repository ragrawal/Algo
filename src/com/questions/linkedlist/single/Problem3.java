package com.questions.linkedlist.single;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.data.SingleLinkedList;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem3 extends Problem{
	
	public Problem3(){
		this.solutions.add(new Solution1());
	}

	@Override
	public String question() {
		return "Write code to determine if a given linkedList is pallidrome.";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
		Read read = new Read(writer, reader);
		char[] elements = read.string("Enter String").toCharArray();
		SingleLinkedList ssl = new SingleLinkedList();
		for(char element: elements)
			ssl.add((Comparable) element);
		options.put("data", ssl);
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			SingleLinkedList ssl = (SingleLinkedList) options.get("data");
			Stack<Comparable> stack = new Stack<Comparable>();
			
			SingleLinkedList.Node ptr1 = ssl.getHead();
			SingleLinkedList.Node ptr2 = ssl.getHead();
			stack.add(ptr2.getValue());
			
			boolean isOdd = false;
			while(ptr1 != null){
				ptr1 = ptr1.getNext();
				
				if(ptr1 != null) ptr1 = ptr1.getNext();
				else isOdd = true;
				
				if(ptr1 != null){
					ptr2 = ptr2.getNext();
					stack.add(ptr2.getValue());
				}
			}
			
			out.println("Ptr2 is at the center of the linked list:");
			out.println("Current Stack: " + stack.toString());
			
			if(isOdd) stack.pop();
			
			while((ptr2 = ptr2.getNext()) != null){
				Comparable prev = stack.pop();
				out.println("Comparing " + prev + " and " + ptr2.getValue());
				if(prev.compareTo(ptr2.getValue()) != 0){
					out.println("The string is not a pallidrome.");
					return;
				}
			}
			
			out.println("The string is a pallidrome.");
			return;
			
		}

		@Override
		public String describe() {
			return "Use two pointers. Increase one pointer by 2 and the second by 1 until the first" +
					"pointer reaches to the end of the linkedlist. Also use a stack to keep track of" +
					"the elements through which the first pointer passed. When the second pointer reaches" +
					"at the end of the linked list, the first pointer reachers to the middle of the linked" +
					"list. Now contiue incrementing the first pointer by 1 position and remove elements from" +
					"the stack and check if they matches with the current element of the pointer.";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(N/2 + 2)";
		}
		
	}
	
	

}
