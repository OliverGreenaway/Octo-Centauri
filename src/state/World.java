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
		worldTile = new Tile[50][50];
		for(int x = 0; x < 50; x++)
			for(int y = 0; y < 50; y++)
				worldTile[x][y] = new Tile(generateRandomTile());
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
}
