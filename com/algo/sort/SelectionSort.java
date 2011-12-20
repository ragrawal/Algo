package com.algo.sort;

public class SelectionSort extends AbstractSort {
	
	public void sort(int[] elements){
		/* sanity checks */
		if(elements == null || elements.length < 2)
			return;
		
		reset();
		int length = elements.length;
		
		/* selection sort */
		for(int i = 0; i < length; i++){          // outer loop
			int idx = i;                            // set minimum
			for(int j = i+1; j < length; j++){        // inner loop
				if(elements[j] < elements[idx])       // if element@j > element@min  
					idx = j;                            // we have new min
				counter++;
			}
			swap(elements, i, idx);						    // swap
		} // i
	}
	
	public String toString(){
		return "Selection Sort";
	}
	
}