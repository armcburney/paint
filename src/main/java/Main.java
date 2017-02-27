/**
 * Main.java
 *
 * @author: Andrew McBurney
 * Instantiate views and add them as observers to the model. Creates main
 * gridBagLayout for app
 */

package ca.andrewmcburney.cs349.a2;

import java.util.logging.Logger;
import java.awt.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import java.awt.event.*;

public class Main {
    private final static Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("Initializing model, and views.");

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
        StrokeWidthPanel strokeWidth = new StrokeWidthPanel(model);
        Canvas canvas = new Canvas(model);

        // Set the middle section to take up the remaining vertical height
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;

        // Second row, first column
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;

        gridBagLayout.setConstraints(palette, gridBagConstraints);
        frame.getContentPane().add(palette);

        gridBagConstraints.weighty = 1.0;

        gridBagConstraints.gridy = 2;
        gridBagLayout.setConstraints(strokeWidth, gridBagConstraints);
        frame.getContentPane().add(strokeWidth);

        // Fill up the entire screen horizontally and vertically
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        // Second row, second column
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;

        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 2;

        gridBagLayout.setConstraints(canvas, gridBagConstraints);
        frame.getContentPane().add(canvas);

        // Add views to model as observers
        model.addObserver(palette);
        model.addObserver(strokeWidth);
        model.addObserver(canvas);

        /*--------------------------------------------------------------------*
         * Bottom Bar Layout
         *--------------------------------------------------------------------*/

        BottomBar bottomBar = new BottomBar(model);

        // Fill up the entire screen horizontally
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        // Take up the remaining horizontal section
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0;

        // Third row, first column
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;

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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Component listener for resize event
        frame.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent e) {
                    boolean viewSmall = model.isViewSmall();

                    if (frame.getBounds().getHeight() <= 475.0 && !viewSmall) {
                        model.setViewSmall(true);
                    } else if (frame.getBounds().getHeight() > 475.0 && viewSmall) {
                        model.setViewSmall(false);
                    }
                }
            });

        // Listener for close event
        frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    Object[] options = {"Cancel", "No", "Yes"};

                    int statusCode = JOptionPane.showOptionDialog(frame,
                        "Would you like save your changes before exiting?",
                        "Unsaved changes",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[2]
                    );

                    // If the statusCode is > 0, dispose of the window and exit
                    if (statusCode > 0) {
                        if (statusCode == 2) {
                            // Change to let the user specify the file name
                            model.saveImage("temp.json");
                        }

                        e.getWindow().dispose();
                        System.exit(0);
                    }
                }
            });

        frame.setVisible(true);
    }
}
