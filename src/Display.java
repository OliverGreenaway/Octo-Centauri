import java.awt.*;

import javax.swing.*;

public class Display extends JPanel{

	// FIELDS
	private final Dimension DIMENSION = new Dimension(1920,1080);
	private final int VIEW_WIDTH = 30, VIEW_HEIGHT = 30;	// Camera = 60x60

	private Tile[][] map;

	private final int TILE_WIDTH = 64, TILE_HEIGHT = 32;


	private Coord camera = new Coord(0,0); // ARBITRARY START POINT
			// Camera stores coord of topmost tile

	/**
	 *
	 */

	// CONSTRUCTOR
	public Display(Tile[][] map){
		super();
		setPreferredSize(DIMENSION); // Necessary?
		this.map = map;
	}

	private static final long serialVersionUID = 8274011568777903027L;

	//RENDERING
	public void paintComponent(Graphics g){
		paintMap(g);
		paintPlayer(g);
	}

	private void paintPlayer(Graphics g) {
		
		
	}

	public int[] getCameraCoordinates(){
		return new int[]{camera.x,camera.y};
	}

	public void setCameraCoordinates(int[] coord){
		camera = new Coord(coord[0],coord[1]);
	}

	public void panLeft(int idx){
		if(camera.x - idx < 0 || camera.y - idx < 0){
			camera=new Coord(0,0);
			return;
		}
		camera = new Coord(camera.x - idx, camera.y - idx);
	}

	public void panRight(int idx){
		if(camera.y - idx < 0 || camera.x + idx >= map[0].length){
			camera=new Coord(0,0);
			return;
		}
		camera = new Coord(camera.x + idx, camera.y - idx);
	}

	public void panDown(int idx){
		if(camera.x - idx < 0 || camera.y + idx >= map[0].length){
			camera=new Coord(0,0);
			return;
		}
		camera = new Coord(camera.x - idx, camera.y + idx);
	}

	public void panUp(int idx){
		if(camera.x - idx < 0 || camera.y - idx < 0){
			camera=new Coord(0,0);
			return;
		}
		camera = new Coord(camera.x + idx, camera.y + idx);
	}

	/**Paints the "view" on-screen at any one time. The algorithm goes through,
	 * drawing the tiles from the top down, and draws them on the graphics pane.
	 *
	 * @param g OVERRIDEN Parameter.
	 */
	private void paintMap(Graphics g){

		for(int x = 0; x<VIEW_WIDTH; x++){
			for(int y = 0; y<VIEW_HEIGHT; y++){
				Tile t = map[x+camera.x][y+camera.y];
				System.out.println("CAMERA: " + camera.x + " " + camera.y +".");
				/*This is the "magic line" -- It calculates the position of the
				 * tile on screen, and was a slightly tricky piece of trigonometry.
				 *
				 * DON'T CHANGE IT UNLESS YOU *REALLY* KNOW WHAT YOU'RE DOING.
				 * Bask in it's majesty and awe-inspiring splendour.
				 */
				g.drawImage(t.getImage(), (this.getWidth()/2)-(TILE_WIDTH/2) + (x-y) * (TILE_WIDTH/2), (x+y) * (TILE_HEIGHT/ 2), TILE_WIDTH, t.getImage().getHeight(null), null);
			}
		}
	}



}
