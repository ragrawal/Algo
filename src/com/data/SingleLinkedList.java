package com.data;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleLinkedList {
	private Node head = null;
	private int length = 0;
	
	public void add(Comparable element){
		Node nn = new Node(element);
		if(isEmpty())
			head = nn;
		else{
			Node current = head;
			while(current.next != null) 
				current = current.next;
			current.next = nn;
		}
		length++;
	}
	
	public Node getHead(){ return head; }
	public Node getRoot() { return getHead(); }
	
	public int length(){ return length; }
	
	public void print(){
		print(new PrintWriter(System.out, true));
	}
	public void print(PrintWriter writer){
		if(isEmpty()){
			writer.println("List is empty");
			return;
		}
		Node current = head;
		while(current != null){
			writer.print(current.value + " --> ");
			current = current.next;
		}
		writer.println();
		writer.flush();
		return;
	}
	
	public static void print(Node head){
		print(head, new PrintWriter(System.out, true));
	}
	public static void print(Node head, PrintWriter writer){
		Node current = head;
		while(current != null){
			writer.print(current.value + " --> ");
			current = current.next;
		}
		writer.println();
		writer.flush();
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	/**
	 * Node Class
	 * @author ragrawal
	 */
	public class Node{
		protected Comparable value;
		protected Node next;
		
		public Node(){}
		public Node(Comparable value){
			this.value = value;
		}
		
		public Comparable getValue(){
			return this.value;
		}
		
		public Node getNext(){
			return this.next;
		}
		
		public void setNext(Node n){
			this.next = n;
		}
	}

}
