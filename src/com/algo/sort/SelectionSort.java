package com.algo.sort;

/*
SelectionSort
Selection sort improves on the bubble sort by reducing the number of swaps necessary 
from O(N^2) to O(N). However, the number of comparisons remains O(N^2).
*/
public class SelectionSort extends AbstractSort{
	public String name(){
		return "SelectionSort 1.0";
	}
	
	public String description(){
		return "Time Complexit: O(N^2), Space Complexity: O(N)";
	}
	
	public void sort(Comparable[] array){
		this.elements = array;
		if(!isValid()) return;
		
		int length = this.elements.length;
		for(int invariant = 0; invariant < length-1; invariant++){
			int min = invariant;
			for(int j=invariant+1; j < length; j++ )
				if(elements[min].compareTo(elements[j]) > 0)
					min = j;
			swap(invariant, min);
		}
		
		return;
	}
}
