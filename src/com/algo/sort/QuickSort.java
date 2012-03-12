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
		System.out.println("left,  right, input");
		resort(0, elements.length-1);
		return;
	}
	
	protected void resort(int left, int right){
		//Sanity check
		//if length is less than 1 then its already sorted
		if(left >= right) return;
		
		System.out.println(left + ", " + right + ", " + Arrays.toString(Arrays.copyOfRange(elements, left, right+1)));
		int partition = partition(left, right);
		System.out.println("    Result: " + partition + ", " + Arrays.toString(elements));
		System.out.println();
		resort(left, partition-1);
		resort(partition+1, right);
			
	}
	
	/**
	* This partition algorithms always assume to 
	* use right most value as the pivot value
	*/
	protected int partition(int p, int r){
		Comparable x = elements[r];
		
		//Initialize i outside of array
		int i = p-1;
		
		//run j from p to one before pivot 
		for(int j = p; j <= r-1; j++){
			if(elements[j].compareTo(x) <= 0)
				swap(++i, j);
		}
		swap(i+1, r);
		return i+1;
	}
	
	public static void main(String[] args){
		Random rand = new Random();
		Comparable[] elements = new Integer[10];
		for(int i=0; i<10; i++) elements[i] = rand.nextInt(100);

		Comparable pivot = elements[7];
		System.out.println("Initial:" + Arrays.toString(elements));
		System.out.println("Pivot = " + pivot);
		QuickSort sort = new QuickSort();
		sort.sort(elements);
		System.out.println("Partitioned: " + Arrays.toString(elements));
		
	}
	
}
