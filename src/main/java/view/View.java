package ca.andrewmcburney.cs349.a2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class View extends JPanel implements Observer {
    private JButton button;
    private Model model;

    View(Model model_) {
        button = new JButton("?");
        button.setMaximumSize(new Dimension(100, 50));
        button.setPreferredSize(new Dimension(100, 50));

        this.setLayout(new GridBagLayout());
        this.add(button, new GridBagConstraints());

        model = model_;

        // Anonymous controller class
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.saveImage();
                System.out.println("Button clicked");
            }
        });
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("View: update");
    }
}
