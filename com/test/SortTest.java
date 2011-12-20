package com.test;
import com.algo.sort.*;
import java.util.Random;
import java.util.Arrays;


public class SortTest{
	
	public static void main(String[] args){
		if(args.length < 2){
			System.out.println("Usage: SortTest <Algorithm> <Number of Elements>");
			System.out.println("Sort Algorithms: " + Arrays.toString(SortFactory.methods));
			System.exit(0);
		}

		//Generate Random elements
		int length = new Integer(args[1]).intValue();
	  Random random = new Random();
	  Comparable[] elements = new Integer[length];
		for(int i =0; i< length; i++)
			elements[i]=(Comparable) random.nextInt(length * 10);

		
		//Create Sort using SortFactory
		Sort sort = SortFactory.get(args[0]);
		
		//Debug Statemenets
		System.out.println("==========================");
		System.out.println(sort.name());
		System.out.println(sort.description());
		System.out.println("==========================");
		System.out.println("Original Series: " + Arrays.toString(elements));
		sort.sort(elements);
		System.out.println("Sorted Series: " + Arrays.toString(elements));
	
	}
	
	
}