package com.leon.twentyquestions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Tree {

	public TreeNode root; //Wurzel unseres Baumes
	
	public Tree() {
		root = new TreeNode();
	}
	
	//Wir laden die data von unserer Datei 
	public void loadTree(String filename) {
		File file = new File(filename);
		FileReader fileReader = null;
		BufferedReader buf = null;
		
		try {
			fileReader = new FileReader(file);
			buf = new BufferedReader(fileReader);
			buildTree(root,buf);
		} catch (Exception e) {
			System.out.println("Error during Tree Building : " + e.getMessage());
		} finally {
			try {
			if (fileReader != null) {
				fileReader.close();
			}
			
			if(buf != null) {
				buf.close();
			}
		}
		    catch(Exception e) {
				
			}
		}
	}
	
	//wir bauen den Baum von einem Buffered reader mit einer Breitensuche
	private void buildTree(TreeNode currentNode, BufferedReader buf) throws Exception {
		String line = 
				buf.readLine();
		
		if(line != null) {
			currentNode.setData(line);
			
			if(currentNode.isQuestion()) {
				TreeNode yesNode = new TreeNode();
				TreeNode noNode = new TreeNode();
				currentNode.yes = yesNode;
				currentNode.no = noNode;
				buildTree(yesNode, buf);
				buildTree(noNode, buf);
			}
		}
	}
}
