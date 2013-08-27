package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SinglePlayerMenuPanel extends JPanel {

	private JButton back;

	public SinglePlayerMenuPanel(final MainFrame frame) {
		
		JButton newGame = new JButton("New Game");
		this.add(newGame);
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Launch Game
				
			}
		});
		
		JButton loadGame = new JButton("Load Game");
		this.add(loadGame);
		loadGame.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Load Game
			}
		});
		
		back = new JButton("Back");
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back();

			}
		});
	}
}
