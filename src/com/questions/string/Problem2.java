package com.questions.string;

import com.questions.Problem;
import com.questions.Solution;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Problem2 extends Problem{
	public Problem2(){
		super();
	}

	public String question(){
		return "Write code to reverse a C-Style String. (C-String means that 'abcd' is represented as five characters, including the null character).";
	}
	
	public Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception{
		Map<String, String> options = new HashMap<String, String>();
		writer.print("Enter String: "); writer.flush();
		options.put("str", reader.readLine());
		return options;
		
	}


	
}