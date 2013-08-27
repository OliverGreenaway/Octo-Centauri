package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SinglePlayerMenuPanel extends JPanel {

	private JButton back;
	//private final JFrame frame;

	public SinglePlayerMenuPanel(final MainFrame frame) {
		//this.frame = frame;
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
