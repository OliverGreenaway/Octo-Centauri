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
				frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
			}
		};
		addButton(frame, listener, "SinglePlayerButton");

		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new MultiplayerMenuPanel(frame)) ;
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
/*
		GridBagConstraints c = new GridBagConstraints();



		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
			}
		};

		MainFrame.addButton(frame, this, c, listener, "SinglePlayerButton", 0);

		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new MultiplayerMenuPanel(frame)) ;
			}
		};

		MainFrame.addButton(frame, this, c, listener, "MultiPlayerButton", 1);

		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};

		MainFrame.addButton(frame, this, c, listener, "QuitButton", 2);
	}

	protected void paintComponent(Graphics g) {
        g.drawImage(MainFrame.background, 0, 0, null);
        g.drawImage(MainFrame.planet, 0, 0, null);
    }*/
}
