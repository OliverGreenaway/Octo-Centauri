package state;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class World {
	private Tile[][] worldTile;

	private Random random = new Random();

	public String generateRandomTile(){
		if(random.nextInt(2)==1)
			return "tile";
		else
			return "tile0";
	}

	public World(){
		worldTile = new Tile[100][100];
		for(int x = 0; x < 100; x++)
			for(int y = 0; y < 100; y++) {
				worldTile[x][y] = new Tile(generateRandomTile());
				if(random.nextInt(5) == 0)
					worldTile[x][y].setStructure(new Structure(x, y, 3, 3, "dark-tree"));
			}
	}

	public World(Tile[][] tiles) {
		worldTile = tiles;
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
}
