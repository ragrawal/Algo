package com.algo.sort;

/*
BubbleSort
While the invariant moves from length-1 to 1, the pointer (i) moves from 0 to invariant-1 and constantly checking if i > i+1. If i > i+1 then it swaps. 
*/
public class BubbleSort extends AbstractSort{
	public String name(){
		return "BubbleSort 1.0";
	}
	
	public String description(){
		return "BubbleSort (1.0) algorithm has O(n^2) time complexity and constant space complexity";
	}
	
	/*
	*Sort Function
	*@params array - Input array of comparable elements
	*@return 
	*/
	public void sort(Comparable[] array){
		this.elements = array;
		if(!isValid()) return;
		int length = this.elements.length;
		
		for(int invariant=length-1; invariant > 0; invariant--){
			for(int i=0; i < invariant; i++){
				if(elements[i].compareTo(elements[i+1]) > 0) 
					swap(i, i+1);
			}
		}
		
		return;
		
	}
	
}