/**
 * TopBar.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.File;
import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.Color;

class TopBar extends JPanel implements Observer {
    private JButton openButton, saveButton, newButton;
    private JFileChooser jFileChooser;
    private JPanel buttonPanel;
    private Model model;

    TopBar(Model model_) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("~/Coding/paint/files"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        newButton  = new JButton("New File");
        openButton = new JButton("Open File");
        saveButton = new JButton("Save File");

        buttonPanel = new JPanel();
        buttonPanel.add(newButton);
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        model = model_;

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(buttonPanel);

        // Anonymous controller classes
        newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Object[] options = {"Cancel", "No", "Yes"};

                    int statusCode = JOptionPane.showOptionDialog(
                        (JFrame) SwingUtilities.getRoot(TopBar.this),
                        "Would you like save your changes?",
                        "Unsaved changes",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, options, options[2]
                    );

                    // If the statusCode is > 0, create a new image
                    if (statusCode > 0) {
                        if (statusCode == 2) {
                            // Change to let the user specify the file name
                            model.saveImage("temp.json");
                        }
                        model.clearDrawing();
                    }
                }
            });
        openButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int returnVal = jFileChooser.showOpenDialog(TopBar.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = jFileChooser.getSelectedFile();
                        System.out.println("Opening: " + file.getName() + ".");
                        model.loadImage(file.getName());
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
                        model.saveImage(file.getName());
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
