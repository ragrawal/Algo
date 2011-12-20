package com.algo.sort;

public class InsertionSort extends AbstractSort {
	
	public void sort(int[] elements){
		if(elements == null || elements.length < 2)
			return;
		
		int length = elements.length;
		
		for(int i = 1; i < length; i++){        // forward loop
			int pivot = elements[i];              // Store value
			int j = i-1;
		  while(j >= 0 && elements[j] > pivot ){ // backward loop 
			  move(elements, j--);                 // move
		 	  counter++;
		  }
			elements[++j] = pivot;                // insert
		}
	}
	
	private void move(int[] elements, int j){
		elements[j+1] = elements[j];
		swaps++;
	}

	
	public String toString(){
		return "Insertion Sort";
	}
	
	
} // BubbleSort

