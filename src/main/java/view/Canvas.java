/**
 * Canvas.java
 *
 * @author: Andrew McBurney
 */

package ca.andrewmcburney.cs349.a2;

import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
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
                timer.start();
            }

            public void mouseDragged(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            public void mouseReleased(MouseEvent e) {
                model.updateDrawing((g) -> g.addCoordToStroke(new Coord(x, y)));
                timer.stop();
            }
        };

    ActionListener timerTask = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.updateDrawing((g) -> g.addCoordToStroke(new Coord(x, y)));
            }
        };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all strokes
        for (Stroke s : model.getDrawing().getStrokes()) {
            ArrayList<Coord> coordinates = s.getCoordinates();

            graphics2D.setColor(s.getColor());
            graphics2D.setStroke(new BasicStroke(s.getWidth() - 5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));

            // Draw all coordinates
            for (int i = 0; i < coordinates.size() -1; i++) {
                graphics2D.draw(new Line2D.Double(
                    new Point2D.Double(coordinates.get(i).getX(), coordinates.get(i).getY()),
                    new Point2D.Double(coordinates.get(i + 1).getX(), coordinates.get(i + 1).getY())
                ));
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
