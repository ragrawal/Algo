package com.algo.sort;

import java.util.Arrays;


public class ShellSort extends AbstractSort{
	
	
	public void sort(int[] elements){
		/* Sanity Check */
		if(elements == null || elements.length < 2)
			return;
		
		reset();
		int length = elements.length;
		
		//initialize gap
		int h = 1;
		while(h <= length/3)
			h = 3*h + 1;
		
		while(h > 0){
			//insertion sort
			for(int outer = 0; outer < length; outer++){
				int tmp = elements[outer];
				int inner = outer;
				int innerLoop  = 1;
				while(inner >= h && elements[inner-h] > tmp){
					elements[inner] = elements[inner-h];
					inner -= h;
					swaps++;
					innerLoop++;
				}
				counter += innerLoop;
				elements[inner] = tmp;
			}
			
			//diminish gap
			h = (h-1)/3;
		}
	}
	

	
	public String toString(){
		return "Shell Sort";
	}
	
}