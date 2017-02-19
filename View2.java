import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;

class View2 extends JPanel implements Observer {
	private Model model;
	private JLabel label = new JLabel();

	View2(Model model_) {
		// create UI
		setBackground(Color.WHITE);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		// set the model
		model = model_;

		// setup the event to go to the "controller"
		// (this anonymous class is essentially the controller)
		addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					model.incrementCounter();
				}
		});
		this.add(this.label);
	}

	// Observer interface
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("View2: updateView");
		// just displays an 'X' for each counter value
		String s = "";
		for (int i=0; i<this.model.getCounterValue(); i++) s = s + "X";
		this.label.setText(s);
	}
}
