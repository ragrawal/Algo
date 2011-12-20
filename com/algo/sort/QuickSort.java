package com.algo.sort;

import java.util.Arrays;

public class QuickSort extends AbstractSort{
	
	public String toString(){
		return "Quick Sort";
	}
	
	public void sort(int[] elements) {
			// Check for empty or null array
			if (elements ==null || elements.length < 2) return;
			reset();
			quicksort(elements, 0, elements.length - 1);
	}

	private void quicksort(int[] elements, int left, int right){
		System.out.println("left = " + left + "; right = " + right + ": " + Arrays.toString(elements));
		if(right-left <= 0) return;  // if size <= 1 already sorted
		int pivot = elements[right]; // rightmost item 
		int partition = partition(elements, left, right, pivot); // partition range 
		System.out.println("partition = " + partition);
		quicksort(elements, left, partition-1);	  // sort left side
		quicksort(elements, partition+1, right);  // sort right side
	}

	private int partition(int[] elements, int left, int right, int pivot){
		int leftPtr = left -1;		// left (after ++)
		int rightPtr = right; // right-1 (after --)
		while(true){
			while(elements[++leftPtr] < pivot); // find larger item
			while(rightPtr > 0 && elements[--rightPtr] > pivot);  //find smaller item
			if(leftPtr >= rightPtr) break; // if pointers cross partition done
			System.out.print("...inside ");
			exchange(elements, leftPtr, rightPtr); //swap elements
		}
		System.out.print("...outside ");
		exchange(elements, leftPtr, right); // restore pivot
		return leftPtr; //return pivot location
	}


		private void exchange(int[] numbers, int i, int j) {
			int temp = numbers[i];
			numbers[i] = numbers[j];
			numbers[j] = temp;
			System.out.println("swap:" + i + ", " + j + " => " + Arrays.toString(numbers));
		}	
	
}