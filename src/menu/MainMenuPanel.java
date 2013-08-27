package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenuPanel extends JPanel {
	private JButton singlePlayer;
	private JButton multiPlayer;
	private JButton quit;
	//private final MainFrame frame;

	public MainMenuPanel(final MainFrame frame) {
		//this.frame = frame;
		singlePlayer = new JButton("Single Player");
		this.add(singlePlayer);
		singlePlayer.addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.addMenu(new SinglePlayerMenuPanel(frame)) ;
					}
				});
		multiPlayer = new JButton("Multi Player");
		this.add(multiPlayer);
		quit = new JButton("Quit");
		this.add(quit);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0) ;

			}
		}) ;
		
	}







}
