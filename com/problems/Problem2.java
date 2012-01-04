package com.problems;

import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem2 extends Problem{
	public Problem2(){
		super();
	}

	public String question(){
		return "Write code to reverse a C-Style String. (C-String means that 'abcd' is represented as five characters, including the null character).";
	}
	
	public Map readParameters() throws Exception{
		Map<String, String> options = new HashMap<String, String>();
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(isr);

		System.out.print("Enter String: ");
		options.put("str", reader.readLine());
	
		return options;
		
	}
	
}