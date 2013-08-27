import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import logic.FileReader;
import logic.Logic;		// TODO Auto-generated catch block
import logic.UpdateThread;

import state.Tile;
import state.World;




@SuppressWarnings("serial")
public class Window extends JFrame implements KeyListener, MouseListener {


	private int mouseX = 0;
	private int mouseY = 0;

	boolean up 			= false;
	boolean down 		= false;
	boolean left 		= false;
	boolean right 		= false;

	Random random = new Random();

	JComponent drawing;
	Display display;
	UpdateThread update;

	public Window(){
		this.setSize(1900, 1080 );
		initialize();
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public String generateRandomTile(){
		if(random.nextInt(2)==1)
			return "tile";

			else
				return "tile0";

	}

	public void initialize(){
		Tile[][] map = FileReader.readMap("resources/map");


//		for(int i = 0; i < 200; i++){
//			for(int j = 0; j < 200; j++){
//				map[i][j] = new Tile(generateRandomTile());
//			}
//		}


		// set up menu
		TopMenu menu =new TopMenu();
		setJMenuBar(menu);		// TODO Auto-generated catch block
		World world = new World(FileReader.readMap("resources/map"));
		display = new Display(world); //was just new World()
		FileReader.setStructures(world); //Set up the structures that the file reader now knows about

		drawing = new JComponent() {
			protected void paintComponent(Graphics g) {
				redraw(g);
			}
		};

		addMouseListener(this);
		addKeyListener(this);
		setFocusable(true);

		add(drawing);
		drawing.repaint();
        update = new UpdateThread(world, display);
        update.start();

	}

//Draws a basic graphic pane needs actual graphical outlines and suchlike
	private void redraw(Graphics g) {

		add(display);
		//display.repaint();
		g.setColor(Color.BLACK);
		//Bottom pane
		g.fillRect(0,getHeight()-(getHeight()/4),getWidth(),getHeight()/4);
		//left hand pane
		g.fillRect(0, 0, 25, getHeight()-(getHeight()/4));
		//right hand pane
		g.fillRect(getWidth() - 25, 0, 50, getHeight()-(getHeight()/4));
		g.fillRect(25, 0, getWidth(), 25);
		g.setColor(Color.red);

		g.fillOval(mouseX - 10, mouseY - 20, 20, 20);

	}


	private void panMap(){
		if(up)
			display.panUp(1);
		if(down)
			display.panDown(1);
		if(right)
			display.panRight(1);
		if(left)
			display.panLeft(1);
	}


	public static void main(String[] args) {
		new Window();
	}


	@Override
	public void keyTyped(KeyEvent e) {

	}
	//gets key events for panning possibly add shortcuts
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
		panMap();
		repaint();
		//display.repaint();
	}
	//disables a given pan direction
	@Override
	public void keyReleased(KeyEvent e) {

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
	//mouse commands, awaiting some level of world to play with
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();

		//SwingUtilities.convertPointFromScreen(p,display); // Adjusts point visually

		System.out.println(p.getX() + " " + p.getY());
		mouseX = p.x;
		mouseY = p.y;


		int endX = mouseX - 960;


		double cx =  Math.cos(Math.toRadians(-30));  //.8509035245; // cos 45 also sin 45
		double sx =  Math.sin(Math.toRadians(-60)); // .8509035245; // cos 45 also sin 45

		double newX = (cx*endX) - (sx*mouseY);
		double newY = (sx*endX) + (cx*mouseY);

		System.out.println("trig: " + (int)(newX/32) + " " +(int)(newY/32));



		int [] cameraPoint = display.getCameraCoordinates();

//
//		if(mouseX < 960){//left side of the screen
////			xSquare = 960 - mouseX;
//		}



	//	cameraPoint[0];




//		Dimension DIMENSION = new Dimension(1920,1080);
//		VIEW_WIDTH = 30, VIEW_HEIGHT = 30;	// Camera = 60x60
//		TILE_WIDTH = 64, TILE_HEIGHT = 32;

//		finds centre of next square
//		this.getWidth()/2)-(TILE_WIDTH/2) + (x-y) * (TILE_WIDTH/2)
//		(x+y) * (TILE_HEIGHT/ 2)
//		TILE_WIDTH
//		t.getImage().getHeight(null)
//



		drawing.repaint();
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}



}




