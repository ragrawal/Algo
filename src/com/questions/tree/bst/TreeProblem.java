package com.questions.tree.bst;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.data.BinarySearchTree;
import com.questions.Problem;
import com.util.Read;

public abstract class TreeProblem extends Problem{
	
	@Override
	public Map readParameters(PrintWriter writer, BufferedReader reader)
			throws Exception {
		Map<String, BinarySearchTree> options = new HashMap<String, BinarySearchTree>();
		Read read = new Read(writer, reader);
		BinarySearchTree tree = read.randomBinaryTree();
		writer.println("Tree: ");
		tree.print(writer);
		options.put("tree", tree);
		return options;
	}
	
}
