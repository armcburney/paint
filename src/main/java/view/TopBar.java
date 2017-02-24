/**
 * TopBar.java
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.File;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;

class TopBar extends JPanel implements Observer {
    private JButton openButton, saveButton;
    private JFileChooser jFileChooser;
    private Model model;

    TopBar(Model model_) {
        setBackground(Color.GREEN);

        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("~/Coding/paint/files"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        openButton = new JButton("Open File");
        saveButton = new JButton("Save File");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        model = model_;

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(buttonPanel);

        // Anonymous controller classes
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = jFileChooser.showOpenDialog(TopBar.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    System.out.println("Opening: " + file.getName() + ".");
                } else {
                    System.out.println("Open command cancelled by user.");
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnVal = jFileChooser.showSaveDialog(TopBar.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = jFileChooser.getSelectedFile();
                    System.out.println("Saving: " + file.getName() + ".");
                } else {
                    System.out.println("Save command cancelled by user.");
                }
            }
        });
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("Top Bar: update");
    }
}
