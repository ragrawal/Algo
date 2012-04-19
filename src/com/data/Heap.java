package com.data;

import java.util.ArrayList;
import java.util.Arrays;

import com.data.tree.binary.BinaryNode;
import com.data.tree.binary.BinarySearchTree;

public class Heap {
	public Node[] store;
	int size = -1;
	
	public Heap(int capacity){
		store = new Node[capacity];
	}
	
	public Heap(Node[] store){
		this.store = store;
		this.size = store.length-1;
		for(int i=parent(this.size); i>=0; i--)
			heapify(i);	
	}
	
	public void add(Node n){
		//Sanity checks
		if(n == null || n.key==null) return;
		if(size >= store.length-1) return;
		
		this.size++;
		int i = this.size;
		while(i > 0 && n.key.compareTo(key(parent(i))) > 0 ){
			store[i] = store[parent(i)];
			i = parent(i);
		}
		store[i] = n;	
	}
	
	public void heapify(int i){
		if (i > size) return;
		
		//Find largest
		int left = left(i);
		int right = right(i);
		int largest = i;
		if(left <= size && key(left).compareTo(key(largest)) > 0) 
			largest = left;
		if(right <= size && key(right).compareTo(key(largest)) > 0) 
			largest = right;
		
		//swap and re-heapify subtree
		if(largest != i){
			Node tmp = store[i];
			store[i] = store[largest];
			store[largest] = tmp;
			heapify(largest);
		}
		
		return;
	}
	
	public void sort(){
		int originalSize = this.size;
		
		System.out.print("After Build-Heap: "); print();
		System.out.println("Do Loop Begins: ");
		System.out.println("   HeapSize, ArrayElements");
		do{
			//Swap root with the last element
			//we know for sure that invariant is the 
			swap(0, this.size);
			this.size--; 
			heapify(0);
			System.out.print("    " + this.size + ", ");
			print();
		}while(this.size > 0);
		this.size = originalSize;
		System.out.print("Final Array: "); print();
	}
	
	public void print(){
		System.out.print("[ ");
		for(int i=0; i<=this.size; i++)
			System.out.print(store[i] + ", ");
		
		if(this.size < this.store.length-1){
			System.out.print("|");
			for(int i = this.size + 1; i < this.store.length; i++){
				System.out.print(store[i] + ", ");
			}
		}
		System.out.println(" ]");
		
	}
	
	private int parent(int i){ return (i+1)/2 - 1; }
	private int left(int i){ return 2*(i+1) - 1; }
	private int right(int i){ return 2*(i+1); }
	
	private Comparable key(int i){
		if(i < store.length && store[i] != null) return store[i].key;
		return null;
	}
	
	private void swap(int i, int j){
		Node tmp = store[j];
		store[j] = store[i];
		store[i] = tmp;
	}
	
	
	
	public class Node{
		protected Comparable key;
		protected Object value;
		
		public Node(Comparable key, Object value){
			this.key = key;
			this.value = value;
		}
		
		public String toString(){
			return this.key.toString();
		}
		
	}
	
	public static void main(String[] args){
		int[] nodes = {10, 5, 18, 2, 34, 0};
		Heap heap = new Heap(nodes.length);
		for(int i=0; i<nodes.length; i++){
			Integer key = nodes[i];
			heap.add(heap.new Node(key, key));	
		}
		heap.print();
		
		System.out.println("\n======= HEAP SORT =============");
		heap.sort();
		heap.print();

	}

}
