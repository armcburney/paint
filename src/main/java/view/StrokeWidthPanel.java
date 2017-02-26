/**
 * StrokeWidthPanel.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
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

class StrokeWidthPanel extends JPanel implements Observer {
    private Model model;
    private JLabel strokeWidthLabel;
    private JButton smallWidth, mediumWidth, largeWidth;
    private GridBagConstraints gridBagConstraints;

    StrokeWidthPanel(Model model_) {
        model = model_;
        setBackground(Color.RED);

        // GridBagLayout
        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;

        smallWidth = new JButton("test");
        smallWidth.setOpaque(true);
        smallWidth.setContentAreaFilled(true);
        smallWidth.setBorderPainted(false);
        smallWidth.setBackground(Color.decode("#db0000"));
        add(smallWidth, gridBagConstraints);
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("StrokeWidthPanel: update");
    }

    public void paint(Graphics g) {
        /*
        g.drawOval(0, 0, 20, 20);
        g.drawOval(40, 0, 20, 20);
        g.drawOval(80, 0, 20, 20);*/
    }
}
