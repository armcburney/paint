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
import javax.swing.Timer;

class BottomBar extends JPanel implements Observer {
    private GridBagLayout gridbag;
    private GridBagConstraints gridBagConstraints;
    private JButton start, end;
    private ButtonGroup buttonGroup;
    private JPanel panel;
    private JRadioButton playForward, playBackward;
    private JSlider slider;
    private Model model;
    private Timer forwardTimer, backwardTimer;

    BottomBar(Model model_) {
        model = model_;
        forwardTimer  = new Timer(1, timerTaskForward);
        backwardTimer = new Timer(1, timerTaskBackward);

        // GridBagLayout
        gridbag = new GridBagLayout();
        gridBagConstraints = new GridBagConstraints();
        setLayout(gridbag);
        setBackground(Color.decode("#dddddd"));
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        gridBagConstraints.weightx = 1.0;
        slider = new JSlider(JSlider.HORIZONTAL, 0, 0, 0);
        slider.setMajorTickSpacing(1000);
        slider.setPaintTicks(true);
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
                    model.updateDrawing((g) -> g.partition(value), "partition");
                }
            });

        // Anonymous controller class
        playForward.addActionListener(e -> {
                System.out.println("Play forward");
                model.updateDrawing((g) -> g.play(true), "play");
            });

        // Anonymous controller class
        playBackward.addActionListener(e -> {
                model.updateDrawing((g) -> g.play(false), "play");
            });

        // Anonymous controller class
        start.addActionListener(e -> {
                // timer, iterate through current value to max value of bottom bar
                model.updateDrawing((g) -> g.sliderStart(), "sliderStart");
            });

        // Anonymous controller class
        end.addActionListener(e -> {
                model.updateDrawing((g) -> g.sliderEnd(), "sliderEnd");
            });
    }

    // Timer task listeners
    ActionListener timerTaskForward = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() + 10);

                if (slider.getValue() >= slider.getMaximum()) {
                    ((Timer)e.getSource()).stop();
                }
            }
        };
    ActionListener timerTaskBackward = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                slider.setValue(slider.getValue() - 10);

                if (slider.getValue() <= 0) {
                    ((Timer)e.getSource()).stop();
                }
            }
        };

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

        if (object == "head") {
            slider.setMaximum(numStrokes * 1000);
            slider.setValue(numStrokes * 1000);
        } else if (object == "sliderStart") {
            slider.setValue(0);
        } else if (object == "sliderEnd") {
            slider.setValue(numStrokes * 1000);
        } else if (object == "play") {
            boolean isForward = model.getDrawing().isPlayForward();

            // Play the animation depending on which direction is specified.
            if (isForward) {
                forwardTimer.start();
            } else {
                backwardTimer.start();
            }
        }
    }
}
