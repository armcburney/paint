/**
 * Main.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class Main{
    public static void main(String[] args) {
        Model model = new Model();

        // Views
        View view = new View(model);
        Canvas canvas = new Canvas(model);

        // Add observers to model and notify them
        model.addObserver(view);
        model.addObserver(canvas);
        model.notifyObservers();

        // Create main JPanel
        JFrame frame = new JFrame("Paint Clone");
        JPanel panel = new JPanel(new GridLayout(1,2));
        frame.getContentPane().add(panel);
        panel.add(view);
        panel.add(canvas);

        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
