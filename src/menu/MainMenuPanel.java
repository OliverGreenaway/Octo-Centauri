package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

	public MainMenuPanel(final MainFrame frame) {
		JButton singlePlayer = new JButton("Single Player");
		this.add(singlePlayer);
		singlePlayer.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
					}
				});
		JButton multiPlayer = new JButton("Multi Player");
		this.add(multiPlayer);
		multiPlayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new MultiplayerMenuPanel(frame));
			}
		}) ;
		JButton quit = new JButton("Quit");
		this.add(quit);
		quit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0) ;
			}
		});

		JButton sound = new JButton("Sound");
		this.add(sound);
		sound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AudioPlayer();
			}
		});
	}
}
