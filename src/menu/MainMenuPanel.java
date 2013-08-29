package menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sound.AudioPlayer;

public class MainMenuPanel extends AbstractMenuPanel {

	AudioPlayer audioPlayer;


	public MainMenuPanel(final MainFrame frame) {

		this.audioPlayer = frame.musicPlayer;


		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.playButtonSound();
				frame.addMenu(new SinglePlayerMenuPanel(frame));
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
				if(audioPlayer!=null){
					audioPlayer.stopPlayer();
				}
				frame.dispose();
			}
		};

		addButton(frame, listener, "QuitButton");


	}
}
