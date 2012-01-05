package com.test;

import com.data.*;
import java.util.Random;
import java.util.Arrays;


public class TreeTest{
	
	public static void main(String[] args){
	   Random random = new Random();
	   BinaryTree tree = new BinaryTree();
	   int length = 10;
	   for(int i =0; i< length; i++)
	       tree.add(new Integer(random.nextInt(length * 10)));;

           tree.print();
           	
	}
	
	
}