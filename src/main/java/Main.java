/**
 * Main.java
 *
 * @author: Andrew McBurney
 * Instantiate views and add them as observers to the model. Creates main
 * gridBagLayout for app
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        JFrame frame = new JFrame("Paint Clone");
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        frame.getContentPane().setLayout(gridBagLayout);

        /*--------------------------------------------------------------------*
         * Top Bar Layout
         *--------------------------------------------------------------------*/

        TopBar topBar = new TopBar(model);

        // Fill up the entire screen horizontally
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Take up the remaining horizontal section
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0;

        // First row, first column
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        gridBagConstraints.gridwidth = 2;

        gridBagLayout.setConstraints(topBar, gridBagConstraints);
        frame.getContentPane().add(topBar);

        // Add view to model as observers
        model.addObserver(topBar);

        /*--------------------------------------------------------------------*
         * Middle Section Layout (Palette and Canvas)
         *--------------------------------------------------------------------*/

        Palette palette = new Palette(model);
        Canvas canvas = new Canvas(model);

        // Fill up the entire screen horizontally and vertically
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        // Set the middle section to take up the remaining vertical height
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 1.0;

        // Second row, first column
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 1;

        gridBagLayout.setConstraints(palette, gridBagConstraints);
        frame.getContentPane().add(palette);

        // Second row, second column
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        gridBagConstraints.gridwidth = 1;

        gridBagLayout.setConstraints(canvas, gridBagConstraints);
        frame.getContentPane().add(canvas);

        // Add views to model as observers
        model.addObserver(palette);
        model.addObserver(canvas);

        /*--------------------------------------------------------------------*
         * Bottom Bar Layout
         *--------------------------------------------------------------------*/

        BottomBar bottomBar = new BottomBar(model);

        // Fill up the entire screen horizontally
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        //gridBagConstraints.anchor = GridBagConstraints.SOUTH;

        // Take up the remaining horizontal section
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0;

        // Third row, first column
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;

        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.gridwidth = 2;

        gridBagLayout.setConstraints(bottomBar, gridBagConstraints);
        frame.getContentPane().add(bottomBar);

        // Add view to model as observers
        model.addObserver(bottomBar);

        /*--------------------------------------------------------------------*
         * Frame details and other
         *--------------------------------------------------------------------*/

        // Notify observers
        model.notifyObservers();

        // Set frame details
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
