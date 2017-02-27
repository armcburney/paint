/**
 * TopBar.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.io.File;
import javax.swing.filechooser.FileFilter;
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
    private JFileChooser jFileChooser;
    private JPanel buttonPanel;
    private Model model;
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem newFile, openFile, saveFile;

    TopBar(Model model_) {
        model = model_;

        // Create JFileChooser
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("~/Coding/paint/files"));
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        // Filter out files that are not .json or .paint
        jFileChooser.setFileFilter(new FileFilter() {
            public String getDescription() {
                return ".json and .paint files";
            }

            public boolean accept(File fileType) {
                String fileName = fileType.getName().toLowerCase();
                return (
                    fileName.endsWith(".json")  ||
                    fileName.endsWith(".paint") ||
                    fileType.isDirectory()
                );
            }
        });

        newFile  = new JMenuItem("New File", KeyEvent.VK_T);
        openFile = new JMenuItem("Open File", KeyEvent.VK_T);
        saveFile = new JMenuItem("Save File", KeyEvent.VK_T);

        menu = new JMenu("File");
        menu.add(newFile);
        menu.add(openFile);
        menu.add(saveFile);

        menuBar = new JMenuBar();
        menuBar.add(menu);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(menuBar);

        // Anonymous controller classes
        newFile.addActionListener(new ActionListener() {
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
        openFile.addActionListener(new ActionListener() {
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
        saveFile.addActionListener(new ActionListener() {
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

        // Set view dimensions
        setMinimumSize(new Dimension(1000, 40));
        setMaximumSize(new Dimension(1000, 40));
        setBackground(Color.decode("#dddddd"));
    }

    @Override
    public void update(Observable observable, Object object) {}
}
