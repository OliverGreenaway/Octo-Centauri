package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
/**
 * The root frame of the game. Presents all the menus, and can show the game.
 * @author muruphenr , antunomate , richarhayd
 *
 */
public class MainFrame extends JFrame {
	Stack<JPanel> frameStack;

	public MainFrame() {
		frameStack = new Stack<JPanel>();
		frameStack.add(new MainMenuPanel(this));
		this.add(frameStack.peek());
		this.setSize(1920, 1080);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String args[]) {
		MainFrame f = new MainFrame();
	}
	/**
	 * Add another JPanel. The current JPanel is stored.
	 * @param comp
	 */
	public void addMenu(JPanel comp) {
		if (frameStack.size() > 0)
			this.remove(frameStack.peek());
		frameStack.add(comp);
		this.add(frameStack.peek());
		this.validate() ;
		this.repaint();
	}
	/**
	 * Return to previous JPanel.
	 */
	public void back() {
		if (frameStack.size() == 1) return ;

		this.remove(frameStack.pop());
		this.add(frameStack.peek());
		this.validate() ;
		this.repaint();
	}
	
	public static void addButton(final MainFrame frame, final JPanel panel, ActionListener listener,  String path, int y) {
		ImageIcon icon = new ImageIcon("Assets/Menus/" + path + ".png");
		JButton button = new JButton(icon);	
		button.setBorder(null);
		button.setBackground(new Color(255, 255, 255, 0));
		panel.add(button);
		button.addActionListener(listener);
	}
}
