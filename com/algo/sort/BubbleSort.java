package com.algo.sort;

/*
BUBBLE SORT - Sorting Algorithm
Complexity: O(n^2)
it takes n*(n-1)/2 iterations to complete the algorithm
*/

public class BubbleSort extends AbstractSort {
	
	public void sort(int[] elements){
		/* Sanity Checks */
		if(elements == null || elements.length < 2)
			return;
			
		reset();
		
		int length = elements.length;

		/* Bubble Sort Algorithm */
		for(int i = length - 1; i > 1; i--){       //Outer Loop (backward)  
			for(int j = 0; j < i; j++){              //Inner Loop (Forward)
				if(elements[j] > elements[j+1])				 //Out of Order ?
					swap(elements, j, j+1);		           // yes then swap
				counter++;			
			}                                        // end inner loop
		}                                          // end outer loop
		
	} // Sort
	
	public String toString(){
		return "Bubble Sort";
	}
	
	
} // BubbleSort

