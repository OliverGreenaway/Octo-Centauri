package menu;

import java.util.Stack;

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
		this.setSize(600, 600);
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
}
