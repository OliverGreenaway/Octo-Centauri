import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class TopMenu extends JMenuBar {

	public TopMenu(){
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);

		add(menu);

		JMenuItem newGame = new JMenuItem("New Game");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.add(newGame);

		newGame.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Add new game thing here

			}

		});

		JMenuItem exit = new JMenuItem("Exit");
		menu.add(exit);
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}



}
