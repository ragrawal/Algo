package com.algo.sort;

public class SortFactory{
	public static final String[] methods = {"BubbleSort", "InsertionSort", "QuickSort","HeapSort", "MergeSort", "SelectionSort"};
	
	public static Sort get(String algorithm){
		if("BubbleSort".equalsIgnoreCase(algorithm))
			return new BubbleSort();
		if("InsertionSort".equalsIgnoreCase(algorithm))
			return new InsertionSort();
		if("SelectionSort".equalsIgnoreCase(algorithm))
			return new SelectionSort();
		return new BubbleSort();
	}
	
}