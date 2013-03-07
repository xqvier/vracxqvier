package com.xqvier.test.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.xqvier.test.ihm.panel.TablePanel;

public class Fenetre extends JFrame {
	private static final long serialVersionUID = 1L;

	public Fenetre() {
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Test IHM");
		mainPanel.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		mainPanel.add(new TablePanel(), BorderLayout.CENTER);
		
		
		
	}
}
