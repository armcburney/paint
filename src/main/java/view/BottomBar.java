/**
 * BottomBar.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
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
        //slider.setMajorTickSpacing(1);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
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

        // Event change listener
        slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent event) {
                    double value = slider.getValue() / (double) slider.getMaximum();
                    //System.out.println(value);
                    model.updateDrawing((g) -> g.partition(value), "partition");
                }
            });
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

        //System.out.println(numStrokes);

        if (object != "partition") {
            slider.setMaximum(numStrokes * 1000);
            slider.setValue(numStrokes * 1000);
        }
    }
}
