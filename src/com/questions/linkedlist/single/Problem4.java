package com.questions.linkedlist.single;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.data.SingleLinkedList;
import com.data.SingleLinkedList.Node;
import com.questions.Problem;
import com.questions.Solution;
import com.util.Read;

/**
 * Test Cases: 8424+23, 9999, 9999
 * @author ragrawal
 *
 */
public class Problem4 extends Problem {
	
	public Problem4(){
		this.solutions.add(new Solution1());
		this.solutions.add(new Solution2());
	}

	@Override
	public String question() {
		return "You are given two numbers in the form of linked list.Add them without \n" +
				"reversing the linked lists. linked lists can be of any length. \n" +
				"Ex:123 1->2->3 \n" +
				"   10234 1->0->2->3->4 \n" +
				"ans: 10357 1->0->3->5->7";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, SingleLinkedList> options = new HashMap<String, SingleLinkedList>();
		Read read = new Read(writer, reader);
		SingleLinkedList num1 = new SingleLinkedList();
		SingleLinkedList num2 = new SingleLinkedList();
		
		String[] number = read.string("Enter First Number: ").split("");
		for(int i=1; i<number.length; i++)
			num1.add((Comparable) new Integer(number[i]) );
		num1.print(writer);
		
		number = read.string("Enter Second Number: ").split("");
		for(int i=1; i<number.length; i++)
			num2.add((Comparable) new Integer(number[i]) );
		num2.print(writer);
		
		options.put("num1", num1);
		options.put("num2", num2);
		return options;
	}
	
	public class Solution1 implements Solution{
		int len1 = 0;
		int len2 = 0;
		PrintWriter out ;
		
		@Override
		public void execute(Map options, PrintWriter out) {
			SingleLinkedList num1 = (SingleLinkedList) options.get("num1");
			SingleLinkedList num2 = (SingleLinkedList) options.get("num2");
			out = out;
			
			len1 = num1.length();
			len2 = num2.length();
			
			//Sanity check
			if(len1 == 0 && len2 == 0){
				out.println("Sum is undefined");
				return;
			}
			else if(len1 == 0) out.println("Sum is same as num2: " );
			else if(len2 == 0) out.println("Sum is same as num1: ");
			
			
			if(len1 < len2){  /* KEY POINT */
				SingleLinkedList tmp = num1;
				int tmpLen = len1;
				num1 = num2;
				num2 = tmp;
				len1 = len2;
				len2 = tmpLen;
			}
			
			out.print("\nBigger Number is: "); num1.print(out);
			out.print("\nSmaller Number is: "); num2.print(out);
			int sum = call(num1.getFirst(), num2.getFirst(), 1);
			out.println("Sum is: " + sum);
			
		}
		
		private int call(Node node1, Node node2, int level){
			if(node1 == null || node2 == null) return 0;
			
			// if only one node is null then it indicates an error
			
			int num1 = ((Integer) node1.getValue()).intValue();
			Node next1 = node1.getNext();
			int num2 = 0; Node next2 = node2;
			
			if(level > (len1-len2)){ /* KEY POINT */
				num2 = ((Integer)node2.getValue()).intValue();
				next2 = node2.getNext();
			} 
			int down = call(next1, next2, level+1);
			return (num1 +  num2)* (int) Math.pow(10.0, len1-level) + down; /* KEY POINT */
		}

		@Override
		public String describe() {
			return "Use recursion to go the last element and then carry over the number\n";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(N) ??";
		}
		
	}
	
	public class Solution2 implements Solution{
		int len1 = 0;
		int len2 = 0;
		PrintWriter out ;
		SingleLinkedList result ;
		
		@Override
		public void execute(Map options, PrintWriter out) {
			SingleLinkedList num1 = (SingleLinkedList) options.get("num1");
			SingleLinkedList num2 = (SingleLinkedList) options.get("num2");
			result = new SingleLinkedList();
			out = out;
			
			len1 = num1.length();
			len2 = num2.length();
			
			//Sanity check
			if(len1 == 0 && len2 == 0){
				out.println("Sum is undefined");
				return;
			}
			else if(len1 == 0) out.println("Sum is same as num2: " );
			else if(len2 == 0) out.println("Sum is same as num1: ");
			
			
			if(len1 < len2){  /* KEY POINT */
				SingleLinkedList tmp = num1;
				int tmpLen = len1;
				num1 = num2;
				num2 = tmp;
				len1 = len2;
				len2 = tmpLen;
			}
			
			out.print("\nBigger Number is: "); num1.print(out);
			out.print("\nSmaller Number is: "); num2.print(out);
			int remainder = call(num1.getFirst(), num2.getFirst(), 1);
			if(remainder != 0)
				result.addFirst(new Integer(remainder));
			out.print("Sum is: "); result.print(out);
			
		}
		
		private int call(Node node1, Node node2, int level){
			if(node1 == null || node2 == null) return 0;
			
			// if only one node is null then it indicates an error
			
			int num1 = ((Integer) node1.getValue()).intValue();
			Node next1 = node1.getNext();
			int num2 = 0; Node next2 = node2;
			
			if(level > (len1-len2)){ /* KEY POINT */
				num2 = ((Integer)node2.getValue()).intValue();
				next2 = node2.getNext();
			} 
			int carry = call(next1, next2, level+1);
			
			int sum = num1 +  num2 +  carry; 
			System.out.println(level + ": " + num1 + " + " + num2 + " + " + carry + " = " + sum);
			result.addFirst(new Integer(sum%10)); /* KEY POINT */
			return sum/10; /* KEY POINT */
			
			
		}

		@Override
		public String describe() {
			return "Use recursion to go the last element and then carry over the number\n";
		}

		@Override
		public String timeComplexity() {
			return "O(N)";
		}

		@Override
		public String spaceComplexity() {
			return "O(N) ??";
		}
		
	}


}
