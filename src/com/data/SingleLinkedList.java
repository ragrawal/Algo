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
	
	public void add(int index, Comparable element){
		Node nn = new Node(element);
		if(index == 0){
			nn.next = head;
			head = nn;
		}
		else{
			Node parent = head;
			int i = 0;
			for(; parent != null && i < index-1; i++) parent = parent.next;
			nn.next = parent.next;
			parent.next = nn;	
		}
		length++;
		return;
	}
	
	
	public void setHead(Node head){
		this.head = head;
	}
	
	public Node getFirst(){return head;}
	
	public void addFirst(Comparable element){
		add(0, element);
	}
	public void addLast(Comparable element){
		add(length, element);
	}
	
	public void reverse(){
		Node current = head;
		Node prev = null;
		Node next = null;
		
		while(current != null){
			//get next pointer
			next = current.getNext();
			//set link from current to previous
			current.setNext(prev);
			//update pointers
			prev = current;
			current = next;
		}
		
		head = prev;
	}
	
	
	
	public void delete(Comparable element){
		if(isEmpty()) return;
		Node current = head;
		Node parent = null;
		while(current != null && element.compareTo(current.value) == 0){
			parent = current; 
			current = current.next;
		}
		if(parent != null && current != null){
			parent.next = current.next;
			length--;
		}
		return;		
	}
	
	
	public Node getHead(){ return head; }
	public Node getRoot() { return getHead(); }
	public int length(){ return length; }
	
	public void print(){
		print(new PrintWriter(System.out, true));
	}
	public static void print(Node head){
		print(head, new PrintWriter(System.out, true));
	}
	public void print(PrintWriter writer){
		if(isEmpty()){
			writer.println("List is empty");
			return;
		}
		print(head, writer);
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
	
	public static void main(String args[]){
		
	}

}
