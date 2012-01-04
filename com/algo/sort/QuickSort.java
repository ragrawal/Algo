package com.algo.sort;

import java.util.Arrays;
import java.util.Random;

/*
QuickSort
<ul>
 <li>Most popular sorting algorithm as it has O(N*logN) time complexity </li>
	<li>Operators by partitioning an array into two subarrays and then calling itself recursively two sort each of these subarrays.</li>
</ul>
*/
public class QuickSort extends AbstractSort{
	public String name(){
		return "QuickSort 1.0";
	}
	
	public String description(){
		return "Time Complexit: O(n Log(n)), Space Complexity: O(1)";
	}
	
	public void sort(Comparable[] array){
		this.elements = array;
		if(!isValid()) return;
		resort(0, elements.length-1);
		return;
	}
	
	protected void resort(int left, int right){
		//Sanity check
		//if length is less than 1 then its already sorted
		if(right-left < 1) return;
		
		int partition = partition(left, right);
		resort(left, partition-1);
		resort(partition+1, right);
			
	}
	
	/**
	* This partition algorithms always assume to use right most value as the pivot value
	*/
	protected int partition(int left, int right){
		Comparable pivot = this.elements[right];
		int leftPtr = left-1;
		int rightPtr = right;
		while(leftPtr < rightPtr){
			while(elements[++leftPtr].compareTo(pivot) < 0);
			while(rightPtr > left && elements[--rightPtr].compareTo(pivot) > 0);
			if(leftPtr < rightPtr) swap(leftPtr, rightPtr);
		}
		swap(leftPtr, right);
		return leftPtr;
	}
	
	public static void main(String[] args){
		Random rand = new Random();
		Comparable[] elements = new Integer[10];
		for(int i=0; i<10; i++) elements[i] = rand.nextInt(100);
		elements = new Integer[] {36, 51, 94, 49, 23, 1, 32, 12, 12, 77};
		Comparable pivot = elements[7];
		System.out.println("Initial:" + Arrays.toString(elements));
		System.out.println("Pivot = " + pivot);
		QuickSort sort = new QuickSort();
		sort.sort(elements);
		System.out.println("Partitioned: " + Arrays.toString(elements));
		
	}
	
}
