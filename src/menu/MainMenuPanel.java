package menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MainMenuPanel extends AbstractMenuPanel {

	public MainMenuPanel(final MainFrame frame) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new SinglePlayerMenuPanel(frame));
			}
		};
		addButton(frame, listener, "SinglePlayerButton");

		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
			}
		};
		addButton(frame, listener, "MultiPlayerButton");

		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};

		addButton(frame, listener, "QuitButton");
	}
}
