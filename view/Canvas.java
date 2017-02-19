/**
 * Canvas.java
 * @author: Andrew McBurney
 */
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

class Canvas extends JPanel implements Observer {
    // the view's main user interface
    private JButton button;
    private Model model;
    private int xPos;
    private int yPos;

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
                model.incrementCounter();
            }
        });
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        System.out.println("View: update");
        button.setText(Integer.toString(model.getCounterValue()));
    }
}
