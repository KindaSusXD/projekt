package com.leon.twentyquestions;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

//main class für das Spiel
public class TwentyQuestions {
	
	private Tree tree;
	private TreeNode currentNode;
	private JButton yesButton, noButton, restartButton;
	private JTextPane textPane; //zum Frage darstellen
	private boolean started = false;
	//btns listener
	private ActionListener btnsListener = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			
			if(source == yesButton)
				yes();
			else if(source == noButton)
				no();
			else if(source == restartButton)
				restart();
		}
	};
	
	public static void main(String[] args) {
		TwentyQuestions twentyQuestions = new TwentyQuestions();
		twentyQuestions.tree = new Tree();
		twentyQuestions.tree.loadTree("E:\\data 2\\TwentyQuestionGame\\animals_questions");
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			twentyQuestions.buildUI();
			}
		});
	}

    private void buildUI() {
    	//wir bauen die UI
    	JFrame frame = new JFrame("Zwanzig Frage Spiel TEST");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	Container contentPane = frame.getContentPane();
    	
    	//Knöpfe hinzufügen
    	JPanel buttonsPanel = new JPanel();
    	
    	yesButton = new JButton("Ja");
    	yesButton.addActionListener(btnsListener);
    	buttonsPanel.add(yesButton, BorderLayout.LINE_START);
    	
    	restartButton = new JButton("Start");
    	restartButton.addActionListener(btnsListener);
    	buttonsPanel.add(restartButton, BorderLayout.LINE_START);
    	
    	noButton = new JButton("Nein");
    	noButton.addActionListener(btnsListener);
    	buttonsPanel.add(noButton, BorderLayout.LINE_START);
    	
    	contentPane.add(buttonsPanel, BorderLayout.PAGE_END);
    	
    	//Text hinzufügen
    	textPane = new JTextPane();
    	textPane.setEditable(false);
    	updateText("Denk an ein Tier, dann drücke Start!");
    	
    	//wir definieren einen Style für das Textfeld
    	SimpleAttributeSet bSet = new SimpleAttributeSet();
    	StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
    	StyleConstants.setFontSize(bSet,  22);
    	StyledDocument doc = textPane.getStyledDocument();
    	doc.setParagraphAttributes(0, doc.getLength(), bSet,false);
    	
    	contentPane.add(textPane, BorderLayout.CENTER);
    	
    	frame.setMinimumSize(new Dimension(400, 250));
    	
    	//wir zentrieren den JFrame
    	Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
    	int coordX = (objDimension.width - frame.getWidth()) / 2;
    	int coordY = (objDimension.height - frame.getHeight()) / 2;
    	frame.setLocation(coordX, coordY);
    	
    	//wir zeigen das Fenster
    	frame.pack();
    	frame.setVisible(true);
    	
     }
    
    //wir müssen Code für die callback methoden yes no und restart schreiben
    private void yes() {
    	//wir navigieren im Baum indem wir die jetzige Node des yes Branches bewegen
    	if(started &&currentNode != null) {
    		currentNode = currentNode.yes;
    		
    		if (currentNode != null) {
    			if(currentNode.isQuestion()) {
    				updateText(currentNode.data);
    			} else {
    				updateText("Denkst du an " + currentNode.data + "?");
    			}
    		} else {
    			updateText("Habs Erraten :)");
    		}
    	}
    }
    
    private void no() {
    	//wir navigieren im Baum indem wir die jetzige Node des no Branches bewegen
    	if (started && currentNode != null) {
    		currentNode = currentNode.no;
    		
    		if(currentNode != null) {
    			if (currentNode.isQuestion()) {
    				updateText(currentNode.data);
    			} else {
    				updateText("Denkst du an " + currentNode.data + "?");
    			}
    		} else {
    			updateText("Ich hab verkackt :(");
    		}
    	}
    }
    
    private void restart() {
    	if(started) {
    		started = false;
    		updateText("Denk an ein Tier, dann drücke Start!");
    	} else {
    		started = true;
    		updateText("Denk an ein Tier, dann drücke Start!");
    		currentNode = tree.root;
    		updateText(currentNode.data);
    	}
    }
    
    private void updateText(String txt) {
    	textPane.setText("\n" + txt);
    }
    
}
    
