package com.test;

import com.problems.*;
import java.util.Map;

public class ProblemTest{
	public static void main(String[] args){
		Problem problem = new Problem1();
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
			Solution sol = problem.getSolution(0);
			System.out.println(sol.describe());
			System.out.println("Time Complexity: " + sol.timeComplexity());
			System.out.println("Space Complexity: " + sol.spaceComplexity());
			System.out.println("Result: ");
			sol.execute(options);
			System.out.println("\n\n\n");
		}
		
	}
}