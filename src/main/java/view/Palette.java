/**
 * Palette.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.colorchooser.*;

class Palette extends JPanel implements Observer {
    private JFrame colorChooserFrame;
    private JColorChooser colorChooser;
    private Model model;
    private boolean toggled = false;
    private JLabel colorLabel, strokeWidthLabel;
    private JButton button, currentColour;
    private ArrayList<JButton> buttons;
    private JColorChooser chooser;
    private GridBagConstraints gridBagConstraints;
    private boolean isSmall = false;

    // Hex colours that form the default palette
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
        buttons = new ArrayList<JButton>();

        // GridBagLayout
        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        initColorChooserFrame();
        initColorPalette();

        setViewBig();
    }

    /**
     * Init color chooser frame
     */
    private void initColorChooserFrame() {
        colorChooserFrame = new JFrame("Color Chooser");
        colorChooserFrame.setMinimumSize(new Dimension(500, 300));
        colorChooserFrame.pack();
        colorChooserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        colorChooserFrame.setVisible(false);

        colorChooser = new JColorChooser(Color.BLACK);
        colorChooser.setBorder(null);
        colorChooserFrame.getContentPane().add(colorChooser);

        colorChooser.getSelectionModel().addChangeListener(e -> {
                System.out.println("Color chooser clicked.");
                model.updateDrawing((g) -> g.setCurrentColor((colorChooser.getColor())), "color");
            });
    }

    /**
     * Init color palette
     */
    private void initColorPalette() {
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;

        // Action Listener for colour selection
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.updateDrawing((g) -> g.setCurrentColor(((JButton) e.getSource()).getBackground()), "init");
            }
        };

        // Instantiate and style buttons
        int row = 0;
        for (int i = 0; i < colours.length; i++) {
            if (i % 5 == 0) {
                gridBagConstraints.gridy = ++row;
            }

            button = new JButton("");
            button.setOpaque(true);
            button.setContentAreaFilled(true);
            button.setMinimumSize(new Dimension(32, 32));
            button.setMaximumSize(new Dimension(32, 32));
            button.setBorderPainted(false);
            button.setBackground(Color.decode(colours[i]));
            buttons.add(button);
            add(button, gridBagConstraints);

            // Add action listener for color change
            button.addActionListener(actionListener);
        }

        // Current colour button
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.gridy = ++row;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);

        currentColour = new JButton("");
        currentColour.setOpaque(true);
        currentColour.setContentAreaFilled(true);
        currentColour.setBorderPainted(false);
        currentColour.setBackground(Color.BLACK);
        add(currentColour, gridBagConstraints);

        // Anonymous controller class
        currentColour.addActionListener(e -> {
                if (!colorChooserFrame.isVisible()) {
                    colorChooserFrame.setVisible(true);
                } else {
                    colorChooserFrame.setVisible(false);
                }
            });
    }

    /*--------------------------------------------------------------------*
     * Viewport Options
     *--------------------------------------------------------------------*/

    // Set view small when the model updates its viewport flag
    private void setViewSmall() {
        setMinimumSize(new Dimension(160, 100));
        setMaximumSize(new Dimension(160, 100));

        for (JButton b : buttons) {
            b.setVisible(false);
        }
    }

    // Set view big when the model updates its viewport flag
    private void setViewBig() {
        setMinimumSize(new Dimension(160, 270));
        setMaximumSize(new Dimension(160, 270));

        for (JButton b : buttons) {
            b.setVisible(true);
        }
    }

    @Override
    public void update(Observable observable, Object object) {
        Color color = model.getDrawing().getCurrentColor();
        currentColour.setBackground(color);
        colorChooser.setColor(color);

        if (model.isViewSmall() && !isSmall) {
            isSmall = true;
            setViewSmall();
        } else if (!model.isViewSmall() && isSmall) {
            isSmall = false;
            setViewBig();
        }
    }
}
