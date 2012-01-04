package com.algo.sort;

import java.lang.Comparable;

abstract class AbstractSort implements Sort{
	Comparable[] elements;
	
	protected boolean isValid(){
		return (elements != null && elements.length > 0);
	}
	
	/*
	* Swap - swaps two elements
	* @param i:int -- index of the element to be swapped
	* @param j:init -- index of the element to be swapped with
	*/
	protected void swap(int i, int j){
		if(i==j) return;
		Comparable tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
	}
	
}