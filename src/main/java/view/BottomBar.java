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
    private Model model;

    BottomBar(Model model_) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        model = model_;
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Bottom Bar: update");
    }
}
