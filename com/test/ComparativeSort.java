package com.test;

import com.algo.sort.*;
import java.util.Random;
import java.util.Arrays;

public class ComparativeSort{
	public static void main(String[] args){
		
		//Initialize Sort
		Sort[] sorts = {new BubbleSort(), new  SelectionSort(), new InsertionSort(), new ShellSort() };
		
		System.out.print("Size, ");
		for(Sort sort : sorts)
			System.out.print(sort + ", ");
		System.out.println();
		
		// initialize random value array
		for(int size =500; size <= 10000; size+= 500){
			System.out.print(size + ", ");
			int[] elements = new int[size];
			Random rand = new Random();
			for(int i=0; i<size; i++)
				elements[i] = rand.nextInt(size*100);
		
			for(Sort sort : sorts){
				int[] c =(int[]) elements.clone();
				sort.sort(c);
				System.out.print(sort.getIterationCount() + ", ");
			}
			
			System.out.println();
		}
	}
}