/**
 * Main.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Main{
    public static void main(String[] args) {
        Model model = new Model();

        // Views
        View view = new View(model);
        Canvas canvas = new Canvas(model);
        TopBar topBar = new TopBar(model);
        BottomBar bottomBar = new BottomBar(model);

        // Add observers to model and notify them
        model.addObserver(view);
        model.addObserver(canvas);
        model.notifyObservers();

        // Create JFrame
        JFrame frame = new JFrame("Paint Clone");

        // Create JPanels
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel(new GridLayout(1,2));
        JPanel bottomPanel = new JPanel();

        // Add panels to JFrame
        frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
        frame.getContentPane().add(midPanel);
        frame.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);

        // Add views to panels
        midPanel.add(view);
        midPanel.add(canvas);
        topPanel.add(topBar);
        bottomPanel.add(bottomBar);

        // Set frame details
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
