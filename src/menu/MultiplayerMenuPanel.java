package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Prompts user to either start a server on the local machine, and wait for a connection -
 * or to connect to an existing server
 * @author muruphenr
 *
 */
public class MultiplayerMenuPanel extends JPanel {
	public MultiplayerMenuPanel(final MainFrame frame) {
		JButton host = new JButton("Host a game") ;

		this.add(host) ;
		host.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						frame.addMenu(new HostMenuPanel(frame)) ;
					}

				}
				) ;
		JButton connection = new JButton("Join a game") ;
		this.add(connection) ;

		connection.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addMenu(new JoinMenuPanel(frame)) ;
			}
		}) ;
		JButton back = new JButton("Back") ;
		this.add(back) ;
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back() ;
			}

		}) ;
	}
}
