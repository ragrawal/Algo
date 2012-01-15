package com.questions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	
	public String toString(){
		return question();
	}
	
	public Map readParameters() throws Exception{
		PrintWriter writer = new PrintWriter(System.out, true);
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		return readParameters(writer, reader);
	}
	
	public abstract Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception;
	
	
	
}