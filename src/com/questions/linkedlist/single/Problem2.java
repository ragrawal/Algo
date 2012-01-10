package com.questions.linkedlist.single;

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
	public Map readParameters() throws Exception {
		Map<String, Object> options = new HashMap<String, Object>();
		Read read = new Read();
		SingleLinkedList ssl = read.singleLinkedList();
		ssl.print();
		options.put("data", ssl);
		
		return options;
	}
	
	public class Solution1 implements Solution{

		@Override
		public void execute(Map options) {
			SingleLinkedList ssl = (SingleLinkedList) options.get("data");
			
			//Sanity checks
			if(ssl.length() < 2) return;
			
			SingleLinkedList.Node current = ssl.getHead();
			SingleLinkedList.Node prev = null;
			SingleLinkedList.Node next = null;
			
			while(current != null){
				//get next pointer
				next = current.getNext();
				//set link from current to previous
				current.setNext(prev);
				//update pointers
				prev = current;
				current = next;
			}
			
			System.out.println("Printing Reverse List:");
			SingleLinkedList.print(prev);
			
		}

		@Override
		public String describe() {
			return "Use three pointers to keep track of" +
					"prev, current adn next node" +
					"next. ";
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
