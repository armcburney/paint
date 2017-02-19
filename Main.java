/**
 * Main.java
 * @author: Andrew McBurney
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class Main{
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        View2 view2 = new View2(model);

        // Add observers to model and notify them
        model.addObserver(view);
        model.addObserver(view2);
        model.notifyObservers();

        // Create main JPanel
        JFrame frame = new JFrame("Paint Clone");
        JPanel panel = new JPanel(new GridLayout(1,2));
        frame.getContentPane().add(panel);
        panel.add(view);
        panel.add(view2);

        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(400, 300));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
