package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class JoinMenuPanel extends JPanel{

	public JoinMenuPanel(final MainFrame frame) {
		JButton back = new JButton("Back") ;
		this.add(back) ;
		back.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back() ;
			}

		}) ;
	}

}
