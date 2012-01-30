package com.test;

import com.data.*;
import java.util.Random;
import java.util.Arrays;


public class TreeTest{
	
	public static void main(String[] args){
	   Random random = new Random();
	   BinarySearchTree tree = new BinarySearchTree();
	   int length = 10;
	   for(int i =0; i< length; i++)
	       tree.add(new Integer(random.nextInt(length * 10)));;

           tree.print();
           System.out.println("Level-Order-Traversal");
           System.out.println(Arrays.toString(tree.levelOrder()));
           System.out.println("\n\n PRE ORDER TRAVERSAl USING LOOP ");
           System.out.println(Arrays.toString(tree.preOrderUsingLoop()));
           System.out.println("\n\nPRE ORDER TRAVERSAL USING RECURSION ");
           System.out.println(Arrays.toString(tree.preOrder()));
           System.out.println("\n\nIN ORDER TRAVERSAL USING RECURSION ");
           System.out.println(Arrays.toString(tree.inOrder()));
           System.out.println("\n\nPOST ORDER TRAVERSAL USING RECURSION ");
           System.out.println(Arrays.toString(tree.postOrder()));
           
	}
	
	
}