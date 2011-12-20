package com.problems;

import java.io.*;
/*
* Write a code to determine if string contains unique characters.
* Solution: 
* 1. Use a boolean array 256 character long to keep track of characters that has appeared: O(n) time complexity & O(n) space complexity
* 2. Use bit operator to save space. Assumes that only a to z characters are available
* 3. Sort and check neighboring characters: O(nlogn)
*/
public class IsUniqueChar{
	
	/*
	* Time Complexity: O(n)
	* Space Complexity: O(n)
	*/
	public static boolean method1(String line){
		boolean[] charset = new boolean[256];
		for(int i=0; i < line.length(); i++){
			int val = line.charAt(i);
			if(charset[val]) return false;
			charset[val] = true;
		}
		return true;
	}
	
	/*
	* Reduces space complexity by using bit operators
	* Assume that all strings are in lower case
	*/
	public static boolean method2(String line){
		int checker = 0;
		for(int i=0; i< line.length(); i++){
			int val = line.charAt(i) - 'a';
			if((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		}
		return true;
	}
	
	public static void main(String[] args){
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter String:");
		try{
			String line = is.readLine();
			if(method1(line)) System.out.println("Method1: No Duplicates");
			if(method2(line)) System.out.println("Method2: No Duplicates");
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}