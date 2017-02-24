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
    private JFileChooser jFileChooser;
    private Model model;

    BottomBar(Model model_) {
        setBackground(Color.RED);
        model = model_;

        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("~/Coding/paint/files"));
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Bottom Bar: update");
    }
}
