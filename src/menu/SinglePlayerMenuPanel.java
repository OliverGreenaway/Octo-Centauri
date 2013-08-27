package menu;

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
public class SinglePlayerMenuPanel extends JPanel {

	public SinglePlayerMenuPanel(final MainFrame frame) {
		/*
		 * Launches a new game, randomly generated from a seed
		 */
		JButton newGame = new JButton("New Game");
		this.add(newGame);
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new Window());
			}
		});
		/*
		 * Brings up a file chooser and loads the game from file.
		 */
		JButton loadGame = new JButton("Load Game");
		this.add(loadGame);
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Load Game
			}
		});

		JButton back = new JButton("Back");

		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back();

			}
		});
	}
}
