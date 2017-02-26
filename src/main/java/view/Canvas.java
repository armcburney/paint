package ca.andrewmcburney.cs349.a2;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.util.*;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class Canvas extends JPanel implements Observer {
    private Model model;
    private Timer timer;
    private int x = 0;
    private int y = 0;

    Canvas(Model model_) {
        timer = new Timer(10, timerTask);

        // Set canvas styles
        setBackground(Color.WHITE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setMinimumSize(new Dimension(400, 10000));
        setPreferredSize(new Dimension(600, 10000));

        model = model_;

        // Add mouse listener and mouse motion listener
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                model.updateDrawing((g) -> g.addStroke(new Coord(x, y)));

                // timer.start();
            }

            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                model.updateDrawing((g) -> g.addCoordToStroke(new Coord(x, y)));
            }

            public void mouseReleased(MouseEvent e) {
                // timer.stop();
            }
        };

    ActionListener timerTask = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(x + " | " + y);
                //model.updateDrawing((g) -> g.addCoordToStroke(new Coord(x, y)));
                // update model if within the constraints here
            }
        };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;

        for (Stroke s : model.getDrawing().getStrokes()) {
            graphics2D.setColor(s.getColor());
            int width = s.getWidth();

            for (Coord c : s.getCoordinates()) {
                Ellipse2D.Double circle = new Ellipse2D.Double(c.getX(), c.getY(), width, width);
                graphics2D.fill(circle);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Canvas: update");
        repaint();
    }
}
