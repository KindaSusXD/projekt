package com.leon.twentyquestions;

public class TreeNode {

	enum Type {
		ANSWER, QUESTION //nodes are answer or question
	}
	
	public static final String QUESTION_FLAG = "Q:";
	public static final String ANSWER_FLAG = "A:";
	public String data;
	public Type type;
	public TreeNode yes;
	public TreeNode no;
	
	public TreeNode() {
		
	}
	
	public TreeNode(String data, Type type) {
		this.data = data;
		this.type = type;
	}
	
	public boolean isQuestion() {
		return Type.QUESTION.equals(type);
	}
	
	public void setData(String data) {
		type = Type.QUESTION;
		
		if(data.startsWith(ANSWER_FLAG)) {
			type = Type.ANSWER;
		}
		
		this.data = data.substring(2); //entfernt question oder answer flag
	}
	
	public void addYes(TreeNode yes) {
		this.yes = yes;
	}
	
	public void addNo(TreeNode no) {
		this.no = no;
	}
	
}
