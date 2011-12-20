package com.test;

import com.data.BinaryTree;
import java.io.*;

public class TreeTest{
	BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
	BinaryTree tree = new BinaryTree();
	
	public void add() throws Exception{
		System.out.print("Enter Number:");
		int val = Integer.parseInt(is.readLine());
		tree.add(val);
		tree.print();
	}
	
	public void find() throws Exception{
		System.out.println("Enter Method(r for recursion, w for while): ");
		String method = is.readLine();
		if(method.equalsIgnoreCase("r"))
			method = "recursion";
			
		System.out.print("Enter Number:");
		int val = Integer.parseInt(is.readLine());
		
		if(tree.contains(val, method))
			System.out.println("Is Present");
		else
			System.out.println("Is Not Present");
	}
	
	public void traverse() throws Exception{
		System.out.println("Enter Method(inorder, preorder): ");
		String method = is.readLine();
		tree.traverse(method);
	}
	
	public void operations() throws Exception{
		System.out.print("Enter Operation (a for add, s for search, t for traverse):");
		String op = is.readLine();
		if(op.equalsIgnoreCase("a")) add();
		else if(op.equalsIgnoreCase("s")) find();
		else if(op.equalsIgnoreCase("t")) traverse();
		
		else System.exit(0);
	}
	
	public static void main(String[] args){
		TreeTest test = new TreeTest();
		while(true){
			try{
				test.operations();
			}catch(NumberFormatException ex){
				System.err.println("Not a valid integer");
			}catch(IOException e){
				System.err.println("Unexpected IO");
			}catch(Exception e){
				System.err.println("Exception ocurred");
			}
		}
	}

	
}