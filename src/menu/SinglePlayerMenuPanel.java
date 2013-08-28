package menu;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import UI.Window;
/**
 * Prompts user to choose between creating a new game from a random seed, or
 * loading a world from file
 * @author muruphenr , antunomate , richarhayd
 *
 */
public class SinglePlayerMenuPanel extends AbstractMenuPanel {

	public SinglePlayerMenuPanel(final MainFrame frame) {

		/*
		 * Launches a new game, randomly generated from a seed
		 */
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new Window(System.currentTimeMillis(), null, null, null));
			}
		};

		addButton(frame, listener, "NewGameButton");

		/*
		 * Brings up a file chooser and loads the game from file.
		 */
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new LoadGameMenuPanel(frame));
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
	}
}
