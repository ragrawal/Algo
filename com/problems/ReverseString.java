package com.problems;

import java.util.Stack;
import java.lang.StringBuilder;
import java.io.*;
/* 
Problem: Write code to reverse a C-style String (C-string means that "abcd" is represented as five characters including the null character). Do it in place
Solution: Use Stack => O(2n) time complexity, O(n) space complexity
          User Reverse Loop => O(n) time compelexity O(n) space complexity


*/

public class ReverseString{
	
	public static String usingJDK(String str){
		if(null==str | str.length() <= 1)
			return str;
		return new StringBuffer(str).reverse().toString();
	}
	
	public static String usingSwap(String str){
		byte[] array = str.getBytes();
		for(int i=0, j = array.length -1 ; i < j; i++, j--){
			array[i] ^= array[j];
			array[j] ^= array[i];
			array[i] ^= array[j];
		}
		return new String(array);
	}
	
	public static void main(String[] args){
		BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter String:");
		try{
			String line = is.readLine();
			System.out.println("using jdk:" + usingJDK(new String(line)));
			System.out.println("Using Swap: " + usingSwap(new String(line)));
			

			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
}