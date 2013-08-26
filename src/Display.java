import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class Display extends JPanel{

	// FIELDS
	public final Dimension DIMENSION = new Dimension(1920,1080);
	public final int CAMERA_X = 60, CAMERA_Y = 60;	// Camera = 60x60
	//public Tile[][] map = new Tile[MAP_TILES_X][MAP_TILES_Y];

	public final int TILE_WIDTH = 64, TILE_HEIGHT = 32;


	private Coord camera = new Coord(0,0); // ARBITRARY START POINT
			// Camera stores coord of topmost tile

	/**
	 *
	 */

	// CONSTRUCTOR
	public Display(){
		super();
		setPreferredSize(DIMENSION); // Necessary?
	}

	private static final long serialVersionUID = 8274011568777903027L;

	//RENDERING


	public void paintComponent(Graphics g){
		paintTiles(g);

	}

	private void paintTiles(Graphics g){

		for(int x = 0; x<CAMERA_X; x++){
			for(int y = 0; y<CAMERA_Y; y++){
				g.drawImage(t.getImage(),)
			}
		}
	}

	public int[] getCameraCoordinates(){
		return new int[]{camera.x,camera.y};
	}

}
