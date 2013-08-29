package menu;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import sound.AudioPlayer;
import sound.MixingDesk;

public class MainMenuPanel extends AbstractMenuPanel {

	MixingDesk mixingDesk;


	public MainMenuPanel(final MainFrame frame) {

		this.mixingDesk = frame.mixingDesk;


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
				if(mixingDesk!=null){
					mixingDesk.stopAudio();
				}
				frame.dispose();
			}
		};

		addButton(frame, listener, "QuitButton");


	}
}
