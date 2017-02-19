/**
 *  Two views with integrated controllers.  Uses java.util.Observ{er, able} instead
 *  of custom IView.
 */

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

public class Main{

	public static void main(String[] args) {
		JFrame frame = new JFrame("HelloMVC4");
		Model model = new Model();
		View view = new View(model);
		model.addObserver(view);
		View2 view2 = new View2(model);
		model.addObserver(view2);
		// let all the views know that they're connected to the model
		model.notifyObservers();
		JPanel p = new JPanel(new GridLayout(2,1));
		frame.getContentPane().add(p);
		p.add(view);
		p.add(view2);
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
