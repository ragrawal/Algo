package com.questions.string;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Map;

import com.questions.Problem;

public class Problem5 extends Problem{

	@Override
	public String question() {
		return "Find and store all the valid numbers " +
				"in an array that are in the string including " +
				"negative, positive, hexadecimal, octal, binary? For " +
				"example string abcd 0xa 11.12 123 " + 
				"has values 10, 11.12 , 123.";
	}

	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
