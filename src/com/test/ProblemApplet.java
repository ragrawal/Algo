package com.test;

import java.applet.Applet;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import com.questions.Problem;
import com.questions.ProblemService;
import com.questions.Solution;
import com.questions.string.Problem1;
import com.util.ConsolePanel;

import dlib.ConsoleWindow;

public class ProblemApplet extends JFrame implements Runnable, TreeSelectionListener {
	ConsolePanel console = new ConsolePanel("Console");
	ProblemService ps = ProblemService.getInstance();
	Thread thread ;
	JTree tree;
	
	
	public ProblemApplet(){
		JPanel container = new JPanel(new GridLayout(1,2));
		container.add(getProblemsTree());
		container.add(console);
		add(container);
	}
	
	@Override
	public void run(){
		pack();
		setVisible(true);

	}
	
	
	private JScrollPane getProblemsTree(){
		Iterator<Problem> iter = ps.getIterator();
		Map<String, ArrayList<Problem>> map = new HashMap<String, ArrayList<Problem>>();
		while(iter.hasNext()){
			Problem p = iter.next();
			String key = p.getClass().getPackage().toString();
			if(map.containsKey(key) == false)
				map.put(key,  new ArrayList<Problem>());
			map.get(key).add(p);
		}
		
		Set<String> keys = map.keySet();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		for(String key: keys){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(key);
			ArrayList<Problem>  problems = map.get(key);
			for(Problem problem : problems) node.add(new DefaultMutableTreeNode(problem));
			root.add(node);
		}
		tree = new JTree(root);
	    tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
	    tree.addTreeSelectionListener(this);
	    return new JScrollPane(tree);
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (node == null) return;
		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) {
		   final Problem problem = (Problem) nodeInfo;
		   if(thread != null && thread.isAlive()) return;
		      
		   Runnable r = new Runnable(){
			   public void run(){changeProblem(problem);}
		   };
		   thread = new Thread(r);
		   thread.start();
		  
		   
		}
	}
	
	private void changeProblem(Problem problem){
		if(problem == null) return;
		if(console.Active() == false) return;

		try{
			PrintWriter out = console.PrintWriter();
			BufferedReader in = console.BufferedReader();
			out.println("\n\n=================== STARTING NEW PROBLEM ============");
			out.println("Question: " + problem.question()); 
			int scount = problem.solutionCount();
			out.println("Number of solutions: " + scount); 
			out.flush();
			Map options = problem.readParameters(out, in);
			for(int i=0; i<scount; i++){
				out.println("===== solution " + i);
				Solution s = problem.getSolution(i);
				out.println(s.describe());out.flush();
				s.execute(options);
				out.flush();
			}
			
		}catch(Exception ex){
			
		}
	}
	
	
	public static void main(String[] args){
		new ProblemApplet().run();

	}




	

}
