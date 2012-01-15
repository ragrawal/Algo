package com.questions;

import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.ArrayList;

/**
* A singleton class to access Problems 
* Uses ServiceLoader functionality of java. It requires a file inside the META-INF/services folder. 
* The name of the file
* file is the full qualified name of the class interface or abstract class
* and the lines inside contains the full qualified name of the files
* implementing the interface/abstract class
* 
*/
public class ProblemService {   
   
   private static ProblemService service;
   private ServiceLoader<Problem> loader;
   private ArrayList<Problem> problems = new ArrayList<Problem>();
   
   
   private ProblemService(){
   	loader = ServiceLoader.load(Problem.class);
   }
   
   /**
   * Retrive the singleton static instance
   */
   public static synchronized ProblemService getInstance(){
   	if(service == null)
   		service = new ProblemService();
   	return service;
   }
   
   public Iterator<Problem> getIterator(){
	   return loader.iterator();
   }
   
   /**
   * Get List of problmes
   */
   public ArrayList<Problem> getProblems(){
   	if(problems == null || problems.size() == 0){
   	    problems = new ArrayList<Problem>();
   	    try{
   	        Iterator<Problem> iter = loader.iterator();
   	        while(iter.hasNext())
   	            problems.add(iter.next());
    	    }catch(Exception ex){
   	        System.out.println(ex.getMessage());
   	    }
   	}
   	return problems;
   }

}