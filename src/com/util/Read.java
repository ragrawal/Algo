package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import com.data.BinaryTree;
import com.data.SingleLinkedList;

public class Read{
    PrintWriter writer; 
    BufferedReader reader;
    public static final String STOP = "q";
    
    public Read(){
    	writer = new PrintWriter(System.out,true);
        InputStreamReader isr = new InputStreamReader(System.in);
        reader = new BufferedReader(isr);
    }
    
    public Read(PrintWriter writer, BufferedReader reader){
    	this.writer = writer;
    	this.reader = reader;
    }
    
    public boolean stop(String word){
    	if(word.equalsIgnoreCase("q")) return true;
    	if(word.equalsIgnoreCase("quit")) return true;
    	return false;	
    }
    
    public Integer integer() throws Exception{
       return integer("Enter Integer:");
    }
    public Integer integer(String message) throws Exception{
       writer.print(message); writer.flush();
       return new Integer(reader.readLine());
    }
    
    public ArrayList<Integer> intArray() throws Exception{
    	return intArray("Enter number (press q if done): ");
    }
    public Comparable[][] intMatrix() throws Exception {
 	   writer.print("Enter Matrix Size (Row, Col): ");
 	   String[] numbers = reader.readLine().split(",");
 	   if(numbers.length < 2) return null;
 	   
 	   int row = new Integer(numbers[0]).intValue();
 	   int col = new Integer(numbers[1]).intValue();
 	
 	   Integer[][] matrix = new Integer[row][col];
 	   
 	   for(int i=0; i<row; i++){
 	   	writer.print("Enter Row " + i + " (separate elements by comma): ");
 	   	numbers = reader.readLine().split(",");
 	   	if(numbers.length < col) return null;
 	   	
 	   	for(int j=0; j<numbers.length; j++){
 	   		matrix[i][j] = new Integer(numbers[j]);
 	   	}
 	   }	
 	   return matrix;
 	}
 	

    
    
    

    public ArrayList<Integer> intArray(String message) throws Exception{
    	ArrayList<Integer> elements = new ArrayList<Integer>();
    	String elem = null;
    	while(true){
    		elem = string(message);
    		if(stop(elem)) break;
    		elements.add(new Integer(elem));
    	}
    	return elements;
    }
    
    public String string() throws Exception{
       return string("Enter string: ");
    }
    public String string(String message) throws Exception{
       writer.print(message); writer.flush();
       return reader.readLine();
    }
    public ArrayList<Comparable> strArray(String message) throws Exception{
    	ArrayList<Comparable> elements = new ArrayList<Comparable>();
    	String elem = null;
    	while(true){
    		elem = string(message);
    		if(stop(elem)) break;
    		elements.add((Comparable) elem);
    	}
    	return elements;
    }
    
    public BinaryTree randomBinaryTree() throws Exception{
        writer.print("Enter total number of nodes: "); writer.flush();
        
        int length = new Integer(reader.readLine()).intValue(); 
        Random random = new Random();
        BinaryTree tree = new BinaryTree();
        for(int i =0; i< length; i++)
        	tree.add(new Integer(random.nextInt(length * 10)));
        return tree;
    }
    
    public BinaryTree binaryTree() throws Exception{
        BinaryTree tree = new BinaryTree();
        while(true){
           writer.print("Enter node value (enter q if done): "); writer.flush();
           String value = reader.readLine();
           if(STOP.equalsIgnoreCase(value)) break;
           tree.add(new Integer(value));
        }
        return tree;
    }

    public SingleLinkedList singleLinkedList() throws Exception{
    	SingleLinkedList sll = new SingleLinkedList();
    	writer.println("Creating a single linked list"); writer.flush();
    	ArrayList<Integer> elements = intArray();
    	for(Integer elem: elements)
    		sll.add(elem);
    	return sll;
    }
}