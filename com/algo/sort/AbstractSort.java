package com.algo.sort;

abstract class AbstractSort implements Sort{
	protected int counter = 0;
	protected int swaps = 0;
	
	abstract public void sort(int[] elements);
	abstract public String toString();
	
	public int getIterationCount(){ return counter; }
	public int getSwapCount(){ return swaps; }
	
	protected void swap(int[] elements, int i, int j){
		int tmp = elements[i];
		elements[i] = elements[j];
		elements[j] = tmp;
		swaps++;
	}
	
	protected void reset(){
		counter = 0;
		swaps = 0;
	}
	
	
	
	
	
	
}

