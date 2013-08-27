package menu;

import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	Stack<JPanel> frameStack;

	public MainFrame() {
		frameStack = new Stack<JPanel>();
		frameStack.add(new MainMenuPanel(this));
		this.add(frameStack.peek());
		this.setSize(600, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String args[]) {
		MainFrame f = new MainFrame();
		// f.add(new MainMenuComponent());

	}

	public void addMenu(JPanel comp) {
		if (frameStack.size() > 0)
			this.remove(frameStack.peek());
		frameStack.add(comp);
		this.add(frameStack.peek());
		this.pack();
		this.repaint();
	}

	public void back() {
		this.remove(frameStack.pop());
		this.add(frameStack.peek());
		this.pack();
		this.repaint();
	}
}
