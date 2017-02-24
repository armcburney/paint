package ca.andrewmcburney.cs349.a2;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class Canvas extends JPanel implements Observer {
	private Model model;
	private JLabel label = new JLabel();

	Canvas(Model model_) {
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

    setMinimumSize(new Dimension(400, 10000));
    setPreferredSize(new Dimension(600, 10000));

		model = model_;

		// Anonymous controller class
		addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				}
		});
		this.add(this.label);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Canvas: update");
	}
}
