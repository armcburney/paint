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

class BottomBar extends JPanel implements Observer {
    private JButton button;
    private JFileChooser jFileChooser;
    private Model model;

    BottomBar(Model model_) {
        this.model = model_;
        this.jFileChooser = new JFileChooser();
        this.jFileChooser.setCurrentDirectory(new File("~/Coding/paint/files"));
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Top Bar: update");
    }
}
