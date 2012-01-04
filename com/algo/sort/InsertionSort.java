package com.algo.sort;

/*
Insertion Sort
*/
public class InsertionSort extends AbstractSort{
	public String name(){
		return "Insertion Sort";
	}
	
	public String description(){
		return "Time Complexity: O(N^2), Space Compexlity: constant";
	}
	
	public void sort(Comparable[] array){
		this.elements = array;
		if(!isValid()) return;
		
		int length = elements.length;
		for(int invariant=1; invariant <  length; invariant++){
			Comparable value = elements[invariant];
			int pointer = invariant-1;
			for(; pointer >= 0 && elements[pointer].compareTo(value) > 0; pointer--)
					swap(pointer, pointer+1);
			elements[pointer+1] = value;
		}
		
		return ;
	}
}