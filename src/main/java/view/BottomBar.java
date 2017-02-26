/**
 * BottomBar.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.File;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;

class BottomBar extends JPanel implements Observer {
    private JButton button;
    private JSlider slider;
    private Model model;

    BottomBar(Model model_) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setEnabled(false);

        slider.setLabelTable(slider.createStandardLabels(1));
        add(slider, BorderLayout.WEST);

        model = model_;
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Bottom Bar: update");
        int numStrokes = model.getDrawing().numStrokes();
        if (numStrokes != 0) {
            slider.setEnabled(false);
            slider.setMaximum(numStrokes);
            slider.setValue(numStrokes);
        }
    }
}
