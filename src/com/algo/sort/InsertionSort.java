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
			for(; pointer >= 0 && elements[pointer].compareTo(value) > 0; pointer--){
				System.out.println(invariant + ", " + pointer + ", yes");
				swap(pointer, pointer+1);
			}
			elements[pointer+1] = value;
		}
		
		return ;
	}
	
	
	public static void main(String[] args){
		Integer[] elements = {50, 40, 30, 20, 10};
		Sort sort = new InsertionSort();
		sort.sort(elements);
		
	}
}