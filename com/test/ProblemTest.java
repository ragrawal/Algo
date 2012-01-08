package com.test;

import com.questions.*;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemTest{
	public static void main(String[] args){
		ProblemService ps = ProblemService.getInstance();
		ArrayList<Problem> problems = ps.getProblems();
		System.out.println("Total Number of Problems: " + problems.size());
		for(int i=0; i< problems.size(); i++)
		    System.out.println(i + ": " + problems.get(i).question());
		
		System.out.print("Enter Question Number:");
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);
		Integer number = 0;
		
		try{
		    number = new Integer(reader.readLine());
		    if(number < 0 || number >= problems.size()) 
		    	throw new Exception("Question Number out of bounds");
		    	
		}catch(Exception ex){
		    System.out.println(ex.getMessage());
		    System.exit(-1);
		}
		Problem problem = problems.get(number.intValue());
		
		System.out.println("=================================");
		System.out.println("Question: " + problem.question());
		
		
		
		int count = problem.solutionCount();
		System.out.println("Number of solutions: " + count);
		Map options = null;
		try{
			options = problem.readParameters();
		} catch(Exception ex){
			System.out.println(ex.getMessage());
			System.exit(-1);
		}
		System.out.println("\n\n\n");
		
		for(int i=0; i < count; i++){
			System.out.println("===== SOLUTION " + (i+1) + " =======");
			Solution sol = problem.getSolution(i);
			System.out.println(sol.describe());
			System.out.println("Time Complexity: " + sol.timeComplexity());
			System.out.println("Space Complexity: " + sol.spaceComplexity());
			System.out.println("Result: ");
			sol.execute(options);
			System.out.println("\n\n\n");
		}
		
	}
}