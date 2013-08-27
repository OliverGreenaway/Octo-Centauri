package state;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class World {
	private TileInterface[][] worldTile;

	private Random random = new Random();

	public String generateRandomTile(){
		int rand = random.nextInt(5);
		if(rand==1)
			return "tile";
		else if (rand == 2)
			return "dark-sand";
		else if (rand == 3)
			return "barren-grass";
		else if (rand == 4)
			return "dark-sand";
		else
			return "Grass";
	}

	public World(){
		worldTile = new Tile[100][100];
		for(int x = 0; x < 100; x++)
			for(int y = 0; y < 100; y++) {
				worldTile[x][y] = new Tile(generateRandomTile());
			}


		for(int x = 3; x < 100; x += 5)
			for(int y = 3; y < 100; y += 5) {
				if(random.nextBoolean())
					addStructure(new Structure(x, y, 3, 3, "Assets/Environment Objects/dark-tree.png"));
				else {
					for(int dx = -2; dx <= 0; dx++) {
						for(int dy = -2; dy <= 0; dy++) {
							addStructure(new Structure(x+dx, y+dy, 1, 1, "Assets/Templates/TileTemplate.png"));
						}
					}
				}
			}

		addDude(new Dude(7, 7, 1, 1, "Assets/Man.png"));
	}

	public World(TileInterface[][] tiles) {
		worldTile = tiles;
	}

	/**
	 * Adds a structure to all tiles it overlaps and returns true.
	 * If the structure can't be placed, returns false without changing anything.
	 */
	public boolean addStructure(Structure s) {
		int x = s.getX(), y = s.getY(), w = s.getWidth(), h = s.getHeight();

		if(x-w < -1 || y-h < -1 || x >= getXSize() || y >= getYSize())
			return false;

		// check for overlap
		for(int X = 0; X < w; X++)
			for(int Y = 0; Y < h; Y++)
				if(worldTile[x-X][y-Y].getStructure() != null){
					System.out.println("Cannot add structure: overlap");
					return false; // can't have two structures on one tile
				}

		// place the structure
		for(int X = 0; X < w; X++)
			for(int Y = 0; Y < h; Y++)
				worldTile[x-X][y-Y].setStructure(s);

		return true;
	}

	/**
	 * Adds a dude to all tiles it overlaps and returns true.
	 * If the dude can't be placed, returns false without changing anything.
	 */
	public boolean addDude(Dude s) {
		int x = s.getX(), y = s.getY(), w = s.getWidth(), h = s.getHeight();

		if(x-w < -1 || y-h < -1 || x >= getXSize() || y >= getYSize())
			return false;

		// check for overlap
		for(int X = 0; X < w; X++)
			for(int Y = 0; Y < h; Y++)
				if(worldTile[x-X][y-Y].getDude() != null)
					return false; // can't have two structures on one tile

		// place the structure
		for(int X = 0; X < w; X++)
			for(int Y = 0; Y < h; Y++)
				worldTile[x-X][y-Y].setDude(s);

		return true;
	}

	public TileInterface getTile(int x, int y) {
		return worldTile[x][y];
	}

	public void setTile(int x, int y, Tile t) {
		worldTile[x][y] = t;
	}

	public int getXSize() {
		return worldTile.length;
	}

	public int getYSize() {
		return worldTile[0].length;
	}
}
