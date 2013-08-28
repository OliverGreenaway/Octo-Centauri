package menu;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import UI.Window;

/**
 * Prompts user to either start a server on the local machine, and wait for a connection -
 * or to connect to an existing server
 * @author muruphenr
 *
 */
public class MultiplayerMenuPanel extends JPanel {
	public MultiplayerMenuPanel(final MainFrame frame) {

		GridBagConstraints c = new GridBagConstraints();
		
		/*
		 * Launches a new game, randomly generated from a seed
		 */
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new HostMenuPanel(frame)) ;
			}
		};

		MainFrame.addButton(frame, this, c, listener, "HostButton", 0);

		/*
		 * join a game
		 */
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new JoinMenuPanel(frame));
			}
		};

		MainFrame.addButton(frame, this, c, listener, "JoinButton", 1);

		/*
		 * back
		 */
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back();
			}
		};

		MainFrame.addButton(frame, this, c, listener, "BackButton", 2);
	}
}
