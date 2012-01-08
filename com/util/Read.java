package com.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import com.data.BinaryTree;

public class Read{
    
    BufferedReader reader;
    public static final String STOP = "q";
    
    public Read(){
        InputStreamReader isr = new InputStreamReader(System.in);
        reader = new BufferedReader(isr);
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
       System.out.print(message);
       return new Integer(reader.readLine());
    }
    
    
    public String string() throws Exception{
       return string("Enter string: ");
    }
    public String string(String message) throws Exception{
       System.out.println(message);
       return reader.readLine();
    }
    
    public BinaryTree randomBinaryTree() throws Exception{
        
        System.out.print("Enter total number of nodes: ");
        
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
           System.out.print("Enter node value (enter q if done): ");
           String value = reader.readLine();
           if(STOP.equalsIgnoreCase(value)) break;
           tree.add(new Integer(value));
        }
        return tree;
    
    }
    
    
}