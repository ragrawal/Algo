package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ReadBinaryTree{

    public static BinaryTree random(){
        InputStreamReader isr = new InputStreamReader(System.in);
	BufferedReader reader = new BufferedReader(isr);
        
        System.out.print("Enter total number of nodes: ");
        
        int length = new Integer(reader.readLine()).intValue(); 
        Random random = new Random();
	BinaryTree tree = new BinaryTree();
	for(int i =0; i< length; i++)
	    tree.add(new Integer(random.nextInt(length * 10)));
	
	return tree;
    }
    
    public static BinaryTree read(){
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        
        BinaryTree tree = new BinaryTree();
        while(true){
           System.out.print("Enter node value (enter q if done): ");
           tree.add(new Integer(reader.readLine()));
        }
        return tree;
    
    }
    
    


}