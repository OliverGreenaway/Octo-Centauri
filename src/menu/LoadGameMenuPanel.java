package menu;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import UI.Window;
/**
 * Prompts user to choose between creating a new game from a random seed, or
 * loading a world from file
 * @author muruphenr , antunomate , richarhayd
 *
 */
public class LoadGameMenuPanel extends AbstractMenuPanel {

	public LoadGameMenuPanel(final MainFrame frame) {

		final JLabel error = new JLabel();

		final JTextArea file = new JTextArea(1, 30);
		this.addComponent(file);

		/*
		 * Brings up a file chooser and loads the game from file.
		 */
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//frame.addMenu(new Window(System.currentTimeMillis(), null, file.getText(), frame.musicThread));

				// TODO add try catch if file not found
				error.setText("Loading not Implemented");
			}
		};

		addButton(frame, listener, "LoadGameButton");

		/*
		 * back
		 */
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back();
			}
		};

		addButton(frame, listener, "BackButton");

		this.addComponent(error);
	}
}
