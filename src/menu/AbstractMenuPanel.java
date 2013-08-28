package menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class AbstractMenuPanel extends JPanel {

	private static Image background = new ImageIcon("Assets/Menus/Background.png").getImage();;
	private static Image planet = new ImageIcon("Assets/Menus/planet.png").getImage();

	private int y = 0;
	private GridBagConstraints c = new GridBagConstraints();

	public void addButton(final MainFrame frame, ActionListener listener, String path) {
		this.setLayout(new GridBagLayout());
		c.gridwidth = 0;
		ImageIcon icon = new ImageIcon("Assets/Menus/" + path + ".png");
		JButton button = new JButton(icon);
		c.insets = new Insets(5, 0, 0, 0);
		c.gridx = 0;
		c.gridy = y;
		this.add(button, c);
		button.setBorder(null);
		button.setBackground(new Color(255, 255, 255, 0));
		button.addActionListener(listener);
		y++;
	}

	protected void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(planet, 0, 0, null);
    }
}
