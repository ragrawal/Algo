package com.questions.linkedlist.single;

import java.util.HashMap;
import java.util.Map;

import com.data.SingleLinkedList;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

public class Problem1 extends Problem{

	public Problem1(){
		super();
		this.solutions.add(new Solution1());
		this.solutions.add(new Solution2());
	}
	@Override
	public String question() {
		return "Find the middle element of a single linked" +
				"list in a single pass";
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
			int length = ssl.length();
			SingleLinkedList.Node node = ssl.getHead();
			//if even then two central element display both
			if(length%2 == 0){
				for(int i=0; i<length/2-1; i++)
					node = node.getNext();
				System.out.print("Middle elements are:");
				System.out.print(node.getValue());
				System.out.print(" and ");
				System.out.println(node.getNext().getValue());
				
			}
			//if odd number of elements then display one
			else{
				for(int i=0; i<length/2; i++)
					node = node.getNext();
				System.out.println("Middle element is: " + node.getValue());
			}
		}

		@Override
		public String describe() {
			return "Get length of the linked list in one pass" +
					"and then get the middle element";
		}

		@Override
		public String timeComplexity() {
			return "O(n + n/2)";
		}

		@Override
		public String spaceComplexity() {
			return "O(1)";
		}
		
	}
	
	public class Solution2 implements Solution{

		@Override
		public void execute(Map options) {
			SingleLinkedList ssl = (SingleLinkedList) options.get("data");
			SingleLinkedList.Node ptr1 = ssl.getHead();
			SingleLinkedList.Node ptr2 = ssl.getHead();
			boolean isOdd = false;
			while(ptr2 != null){
				ptr2 = ptr2.getNext();
				
				if(ptr2!= null) ptr2= ptr2.getNext();
				else isOdd = true;
				
				if(ptr2 != null) ptr1 = ptr1.getNext();
			}
			
			if(isOdd){
				System.out.println("Middle element is: " + ptr1.getValue());
			}else{
				System.out.print("Middle elements are:");
				System.out.print(ptr1.getValue());
				System.out.print(" and ");
				System.out.println(ptr1.getNext().getValue());
			}
			
		}

		@Override
		public String describe() {
			return "Use two pointer. Increase one pointer by 2 and " +
					"another by 1. When the second reaches to the end " +
					"of the list the other pointer will at the center of" +
					"list. If we are able to move the second pointer by 1" +
					"then this indicates the the list has even number of " +
					"elements and thus there will be two central elements. " +
					"\n\n SIMILAR APPROACH IN OTHER PROBLEMS: similar approach can be used if someone asks" +
					"to find n'th element from last. In this case move first " +
					"pointer to the n'th position and then start incrementing " +
					"the other";
		}

		@Override
		public String timeComplexity() {
			return "O(N) -- single scan approach";
		}

		@Override
		public String spaceComplexity() {
			return "O(3)";
		}
		
	}
	

}
