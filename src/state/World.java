package state;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class World {
	private Tile[][] worldTile;

	long seed = System.currentTimeMillis();
	private Random random = new Random(seed);

	private Set<Dude> allDudes = new HashSet<Dude>();

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
				if (random.nextInt(2) == 1)
					worldTile[x][y] = new Tile(generateRandomTile(),0);
				else
					worldTile[x][y] = new Tile(generateRandomTile(),1);
			}

		for(int x = 3; x < 100; x += 1){
			for(int y = 3; y < 100; y += 1) {
				if(random.nextInt(20)==1)
					addStructure(new Structure(x, y, 3, 3, "Assets/Environment Objects/dark-tree.png"));
				}
			}

		addDude(new Dude(this, 7, 7, 1, 1, "Assets/Man.png"));
	}

	public World(Tile[][] tiles) {
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
					return false; // can't have two structures on one tile <--The best comment! =)

		// place the structure
		for(int X = 0; X < w; X++)
			for(int Y = 0; Y < h; Y++)
				worldTile[x-X][y-Y].setDude(s);

		allDudes.add(s);

		return true;
	}

	public Tile getTile(int x, int y) {
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

	public void update() {
		for(Dude d : allDudes)
			d.update();
	}
}
