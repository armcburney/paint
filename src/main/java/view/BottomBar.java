/**
 * BottomBar.java
 *
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
    private GridBagLayout gridbag;
    private GridBagConstraints gridBagConstraints;
    private JButton start, end;
    private JRadioButton playForward, playBackward;
    private JSlider slider;
    private Model model;

    BottomBar(Model model_) {
        model = model_;

        // GridBagLayout
        gridbag = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        setLayout(gridbag);
        setBackground(Color.decode("#dddddd"));
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.0;

        playForward  = new JRadioButton("Forward");
        playBackward = new JRadioButton("Backward");
        add(playForward, gridBagConstraints);
        add(playBackward, gridBagConstraints);

        gridBagConstraints.weightx = 1.0;
        slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setEnabled(false);
        slider.setLabelTable(slider.createStandardLabels(1));
        add(slider, gridBagConstraints);

        gridBagConstraints.weightx = 0.0;
        start = new JButton("Start");
        end = new JButton("End");
        add(start, gridBagConstraints);
        add(end, gridBagConstraints);
    }

    @Override
    public void update(Observable observable, Object object) {
        int numStrokes = model.getDrawing().numStrokes();

        if (numStrokes == 0) {
            slider.setEnabled(false);
        } else {
            slider.setEnabled(true);
        }

        slider.setMaximum(numStrokes);
        slider.setValue(numStrokes);
    }
}
