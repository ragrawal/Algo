package com.questions;

import java.io.PrintWriter;
import java.util.Map;

public interface Solution{
	public void execute(Map options, PrintWriter out);
	public String describe();
	public String timeComplexity();
	public String spaceComplexity();
}