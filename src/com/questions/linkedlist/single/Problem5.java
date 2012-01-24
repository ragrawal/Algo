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

public class Problem5 extends Problem{
	
	public Problem5(){
		this.solutions.add(new Solution1());
	}

	@Override
	public String question() {
		return "Write a code to swap two elements of a linked list";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Read read = new Read(writer, reader);
		Map<String, Object> options = new HashMap<String, Object>();
		
		SingleLinkedList list = new SingleLinkedList();
		for(Integer element: read.intArray())
			list.add(element);
		list.print(writer);
		options.put("data", list);
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options, PrintWriter out) {
			//Get data
			SingleLinkedList list = (SingleLinkedList) options.get("data");
			
			//Sanity Check
			if(list.length() < 2) {
				out.println("Nothing to swap");
				return;
			}
			
			//Set head to the next pointer as this will be the first element after 
			// the whole process
			Node last, current, next;
			last = next = null;
			current = list.getFirst();
			list.setHead(current.getNext());
			
			out.println("Resetting Head:");
			list.print(out);
			
			boolean setHead = true;
			int counter = 0;
			while((next = current.getNext()) != null){
				//swap
				current.setNext(next.getNext());
				next.setNext(current);
				
				//increment pointers
				if(last != null) last.setNext(next);
				last = current;
				current = current.getNext();
				
				//debug statements
				out.print("Counter " + counter++ + ":");
				list.print(out);
				
				//boundary condition check
				if(current == null) break;				
			}
			
			
			//debug statemenet
			out.println("Swapped Linked List: ");
			list.print(out);
			return;


			
		}

		@Override
		public String describe() {
			return "Use three pointers. Use one to keep track of the last swapped " +
					"element and two to swap current positions. At the end make sure to to set head";
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
