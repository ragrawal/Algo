package com.data;

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
		if(isEmpty()){
			System.out.println("List is empty");
			return;
		}
		Node current = head;
		while(current != null){
			System.out.print(current.value + " --> ");
			current = current.next;
		}
		System.out.println();
		return;
	}
	
	public static void print(Node head){
		Node current = head;
		while(current != null){
			System.out.print(current.value + " --> ");
			current = current.next;
		}
		System.out.println();
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
