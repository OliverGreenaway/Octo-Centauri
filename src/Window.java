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

	ArrayList<pressCheck> presses;
	JComponent drawing;
	// Display display

	public Window(){
		presses = new ArrayList<pressCheck>();
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
		g.fillOval(xvalue, yvalue, 500, 500);

		// TODO Auto-generated method stub

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
		presses.add(e);
		for(KeyEvent key: presses)
				presses.add(new pressCheck(key));
		/*switch (code) {



			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_KP_RIGHT:
			case KeyEvent.VK_D:
				xvalue++;
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_KP_LEFT:
			case KeyEvent.VK_A:
				xvalue--;
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_KP_UP:
			case KeyEvent.VK_W:
				yvalue--;
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_KP_DOWN:
			case KeyEvent.VK_S:
				yvalue++;
				break;
		}

		if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT || code == KeyEvent.VK_D) {
			xvalue++;
		}else if(code == KeyEvent.VK_LEFT || code == KeyEvent.VK_KP_LEFT || code == KeyEvent.VK_A){
			xvalue--;
		}else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_KP_DOWN || code == KeyEvent.VK_S) {
			yvalue++;
		}else if( code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP || code == KeyEvent.VK_W){
			yvalue--;
		}else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_KP_RIGHT || code == KeyEvent.VK_D
			   || code == KeyEvent.VK_UP || code == KeyEvent.VK_KP_UP || code == KeyEvent.VK_W) {
			xvalue++;
		}*/
		drawing.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		presses.remove(e);
	}
	//Is passed each current key press, performs the given action, one created for each current key
	private class pressCheck{
		private pressCheck(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_UP)
					up();
		}
		
		private void up(){
			while (true)
				yvalue--;
			
		}
		
		private void left(){
			
		}
		
		private void right(){
			
		}
		
		private void down(){
			
		}
	}
}



