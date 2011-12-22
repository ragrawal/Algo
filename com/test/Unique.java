package com.test;

public class Unique{
	public static void main(String[] args){
		String str = args[0];
		int checker = 0;
		for(int i=0; i<str.length(); i++){
			System.out.println("====================");
			System.out.println("Character - " + str.charAt(i));
			int val = str.charAt(i) - 'a';
			System.out.println("val = " + val);
			System.out.println("1 << val = " + Integer.toBinaryString(1 << val));
			if((checker & (1 << val)) > 0 ) 
				return;
			checker |= (1 << val);
			System.out.println("Checker = " + Integer.toBinaryString(checker));
		}
	}
}