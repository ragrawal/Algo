package com.test;

import com.algo.sort.*;
import java.util.Arrays;
import java.util.Random;

public class SortTest{
	
	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Usage: java SortTest <bubble|selection|insertion> <size>");
		}
		
		Sort sort = null;
		if("bubble".equalsIgnoreCase(args[0]))
			sort = new BubbleSort();
		else if ("selection".equalsIgnoreCase(args[0]))
			sort = new SelectionSort();
		else if ("insertion".equalsIgnoreCase(args[0]))
			sort = new InsertionSort();
		else if ("shell".equalsIgnoreCase(args[0]))
			sort = new ShellSort();
		else
			sort = new QuickSort();
		
		int size = new Integer(args[1]).intValue();

		int[] elements = new int[size];
		
		Random rand = new Random();
		for(int i=0; i<size; i++)
			elements[i] = rand.nextInt(size*100);
		
		elements = new int[]{1,8,2,9,5};
		
		System.out.println("=================");
		System.out.println(sort);
		System.out.println("=================");
		
		System.out.println("Original: " + Arrays.toString(elements));
		sort.sort(elements);
		System.out.println("Sorted: " + Arrays.toString(elements));
		System.out.println("Counter: " + sort.getIterationCount());
		System.out.println("Swaps: " + sort.getSwapCount());
	}
}
	