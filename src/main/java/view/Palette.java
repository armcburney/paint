/**
 * Palette.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class Palette extends JPanel implements Observer {
    private Model model;
    private JButton button, currentColour;
    private GridBagConstraints gridBagConstraints;
    private static final String[] colours = {
        "#999999", "#777777", "#555555", "#333333", "#111111",
        "#db0000", "#c10000", "#a80000", "#8e0000", "#750000",
        "#ffb733", "#ffae1a", "#ffa500", "#e69500", "#b37400",
        "#ffff33", "#ffff1a", "#ffff00", "#e6e600", "#cccc00",
        "#60c550", "#40a347", "#166b2d", "#0d5a29", "#043e0b",
        "#83bcec", "#05508f", "#103a80", "#0a2553", "#01122e",
        "#efbbff", "#d896ff", "#be29ec", "#800080", "#660066"
    };

    Palette(Model model_) {
        model = model_;

        // GridBagLayout
        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;

        // Action Listener for colour selection
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.saveImage();
                System.out.println("Button clicked");
                System.out.println(e);
            }
        };

        // Instantiate buttons
        int row = 0;
        for (int i = 0; i < colours.length; i++) {
            if (i % 5 == 0) { gridBagConstraints.gridy = ++row; }
            button = new JButton("");
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setMinimumSize(new Dimension(32, 32));
            button.setMaximumSize(new Dimension(32, 32));
            button.setBorderPainted(false);
            button.setBackground(Color.decode(colours[i]));
            add(button, gridBagConstraints);
            button.addActionListener(actionListener);
        }

        // Current colour button
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridy = ++row;
        gridBagConstraints.gridx = 0;
        currentColour = new JButton("");
        currentColour.setOpaque(true);
        currentColour.setContentAreaFilled(true);
        currentColour.setBorderPainted(false);
        currentColour.setBackground(Color.decode("#db0000"));
        add(currentColour, gridBagConstraints);

        // View styles
        setMinimumSize(new Dimension(160, 10000));
        setMaximumSize(new Dimension(160, 10000));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Anonymous controller class
        currentColour.addActionListener(actionListener);
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Palette: update");
    }
}
