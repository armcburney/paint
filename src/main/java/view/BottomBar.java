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
import java.awt.GridLayout;
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
    private ButtonGroup buttonGroup;
    private JPanel panel;
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

        gridBagConstraints.weightx = 1.0;
        slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setEnabled(false);
        slider.setLabelTable(slider.createStandardLabels(1));
        add(slider, gridBagConstraints);

        gridBagConstraints.weightx = 0.0;

        // Add two radio buttons to a button group
        playForward  = new JRadioButton("Forward");
        playBackward = new JRadioButton("Backward");
        playForward.setEnabled(false);
        playBackward.setEnabled(false);
        playForward.setSelected(true);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(playForward);
        buttonGroup.add(playBackward);

        panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(Color.decode("#dddddd"));
        panel.add(playForward);
        panel.add(playBackward);
        add(panel, gridBagConstraints);

        // Buttons for start, end as per specs
        start = new JButton("Start");
        end = new JButton("End");
        start.setEnabled(false);
        end.setEnabled(false);

        panel = new JPanel(new GridLayout(0, 1));
        panel.setBackground(Color.decode("#dddddd"));
        panel.add(start);
        panel.add(end);
        add(panel, gridBagConstraints);
    }

    @Override
    public void update(Observable observable, Object object) {
        int numStrokes = model.getDrawing().numStrokes();

        if (numStrokes == 0) {
            slider.setEnabled(false);
            playForward.setEnabled(false);
            playBackward.setEnabled(false);
            start.setEnabled(false);
            end.setEnabled(false);
        } else {
            slider.setEnabled(true);
            playForward.setEnabled(true);
            playBackward.setEnabled(true);
            start.setEnabled(true);
            end.setEnabled(true);
        }

        slider.setMaximum(numStrokes);
        slider.setValue(numStrokes);
    }
}
