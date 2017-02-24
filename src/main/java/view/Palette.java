package ca.andrewmcburney.cs349.a2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class Palette extends JPanel implements Observer {
    private JButton button;
    private Model model;

    Palette(Model model_) {
        button = new JButton("?");
        //button.setMinimumSize(new Dimension(200, 360));
        //button.setPreferredSize(new Dimension(200, 540));

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
        System.out.println("Palette: update");
    }
}
