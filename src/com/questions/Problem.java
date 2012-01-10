package com.questions;

import java.util.Map;
import java.util.ArrayList;

public abstract class Problem{
	protected ArrayList<Solution> solutions = new ArrayList<Solution>();
	
	abstract public String question();
	
	public int solutionCount(){
		return solutions.size();
	}

	public Solution getSolution(int number){
		if(number < 0 || number >= solutions.size()) return null;
		return solutions.get(number);
	}
	
	
	public abstract Map readParameters() throws Exception;
	
	
}