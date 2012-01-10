package com.test;

import java.applet.Applet;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;

import com.questions.Problem;
import com.questions.ProblemService;
import com.util.ConsolePanel;

import dlib.ConsoleWindow;

public class ProblemApplet extends JFrame implements Runnable {
	ConsolePanel console = new ConsolePanel("Console");
	ProblemService ps = ProblemService.getInstance();
	ArrayList<Problem> problems = null;
	
	
	
	public ProblemApplet(){
		
		JPanel container = new JPanel(new GridLayout(1,2));
		container.add(new JTree());
		container.add(console);
		
		add(container);
		
	}
	
	@Override
	public void run(){
		pack();
		setVisible(true);
		initConsole();

	}
	
	
	
	
	
	
	public void initConsole() {
		problems = ps.getProblems();
		try{
			PrintWriter out = console.PrintWriter();
			BufferedReader in = console.BufferedReader();
			try{
				out.println("Total Number of Problems: " + problems.size());
				for(int i=0; i< problems.size(); i++)
				    out.println(i + ": " + problems.get(i).question());
				while(console.Active()){
					out.print("Enter Question Number:");out.flush();
					{
						String str = in.readLine();
					}
				}
				 
			}catch(Exception err){
				 
			}
			
		}catch(Exception err){
			
		}
		
	}
	
	public static void main(String[] args){
		new ProblemApplet().run();

	}




	

}
