import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;



public class Window extends JFrame implements KeyListener {

	private int yvalue=200;
	private int xvalue=100;

	boolean up 		= false;
	boolean down 	= false;
	boolean left 	= false;
	boolean right 	= false;

	JComponent drawing;
	// Display display

	public Window(){
		initialize();
		this.setSize(800, 800 );
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initialize(){

		drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				redraw(g);
			}


		};

		this.addKeyListener(this);
		this.setFocusable(true);
		this.add(drawing);
		drawing.repaint();


	}


	private void redraw(Graphics g) {
		g.setColor(Color.black);

		if(up){
			yvalue--;
		//	redraw(g);
		}
		if(down)
			yvalue++;
		if(right)
			xvalue++;
		if(left)
			xvalue--;

		g.fillOval(xvalue, yvalue, 500, 500);


	}


	public static void main(String[] args) {
		new Window();

	}


	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				right = true;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				left = true;
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				up = true;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				down = true;
				break;
		}
		drawing.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_KP_RIGHT:
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_KP_LEFT:
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_KP_UP:
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_KP_DOWN:
		case KeyEvent.VK_S:
			down = false;
			break;
		}
	}
}




