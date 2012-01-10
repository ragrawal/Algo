package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadArray{
	public static Comparable[][] read() throws Exception {
	   InputStreamReader isr = new InputStreamReader(System.in);
	   BufferedReader reader = new BufferedReader(isr);

	   System.out.print("Enter Matrix Size (Row, Col): ");
	   String[] numbers = reader.readLine().split(",");
	   if(numbers.length < 2) return null;
	   
	   int row = new Integer(numbers[0]).intValue();
	   int col = new Integer(numbers[1]).intValue();
	
	   Integer[][] matrix = new Integer[row][col];
	   
	   for(int i=0; i<row; i++){
	   	System.out.print("Enter Row " + i + " (separate elements by comma): ");
	   	numbers = reader.readLine().split(",");
	   	if(numbers.length < col) return null;
	   	
	   	for(int j=0; j<numbers.length; j++){
	   		matrix[i][j] = new Integer(numbers[j]);
	   	}
	   }	
	   return matrix;
	}
	
	public static void main(String[] args){
	   try{
		Comparable[][] matrix = ReadArray.read();
		int rows = matrix.length;
		for(int i=0; i<rows; i++)
		   System.out.println(Arrays.toString(matrix[i]));
	   }catch(Exception ex){
	   	System.out.println(ex);
	        System.exit(-1);
	   }
	}
}