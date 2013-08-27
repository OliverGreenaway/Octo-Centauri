package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {

	public MainMenuPanel(final MainFrame frame) {
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
			}
		};
		
		MainFrame.addButton(frame, this, listener, "SinglePlayerButton", 0);
		
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new MultiplayerMenuPanel(frame)) ;
			}
		};
		
		MainFrame.addButton(frame, this, listener, "MultiPlayerButton", 1);
		
		listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		};
		
		MainFrame.addButton(frame, this, listener, "QuitButton", 1);
	}
}
