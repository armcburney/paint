/**
 * StrokeWidthPanel.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.List;
import java.util.Arrays;
import javax.swing.*;
import java.awt.event.ComponentEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.colorchooser.*;

class StrokeWidthPanel extends JPanel implements Observer {
    private Model model;
    private JLabel strokeWidthLabel;
    private JButton button;
    private BufferedImage circle;
    private Graphics2D graphics2D;
    private GridBagConstraints gridBagConstraints;
    private static final int OFFSET = 3;
    private int row = 0;

    StrokeWidthPanel(Model model_) {
        model = model_;

        // GridBagLayout
        setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;

        // Create components
        add(createButton(30), gridBagConstraints);
        add(createButton(25), gridBagConstraints);
        add(createButton(20), gridBagConstraints);
        add(createButton(15), gridBagConstraints);

        // Set view dimensions
        setMinimumSize(new Dimension(160, 500));
        setMaximumSize(new Dimension(160, 500));

        // Component listener for resize event
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                System.out.println(e);
            }
        });
    }

    /**
     * Anonymous controller class actionListener for button click event
     */
    private ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked - updating the stroke width.");
            model.updateDrawing((g) -> g.setStrokeWidth( Integer.parseInt( ((JButton) e.getSource()).getName() )) );
        }
    };

    /**
     * Draw a circle icon for the button and set it
     */
    private void drawCircle(JButton button, int d) {
        circle = new BufferedImage(d, d, BufferedImage.TYPE_INT_ARGB);
        graphics2D = circle.createGraphics();

        // Set rendering hint for antialiasing
        graphics2D.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON
        );

        graphics2D.setColor(Color.BLACK);
        graphics2D.fillOval(OFFSET, OFFSET, d - (2 * OFFSET), d - (2 * OFFSET));
        graphics2D.dispose();

        button.setIcon(new ImageIcon(circle));
    }

    /**
     * Creates a button
     */
    private JButton createButton(int diameter) {
        button = new JButton("");
        button.setName(String.valueOf(diameter));
        button.setMinimumSize(new Dimension(160, 35));
        button.setPreferredSize(new Dimension(160, 35));
        button.setMinimumSize(new Dimension(160, 35));
        button.addActionListener(actionListener);
        drawCircle(button, diameter);
        gridBagConstraints.gridy = ++row;

        return button;
    }

    @Override
    public void update(Observable observable, Object object) {
        System.out.println("StrokeWidthPanel: update");
    }
}
