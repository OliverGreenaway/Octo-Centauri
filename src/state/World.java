package state;

import java.awt.Image;
import java.util.ArrayList;

public class World {
	private Tile[][] worldTile;

	public World(){
		worldTile = new Tile[50][50];
	}

	public Tile getTile(int x, int y) {
		return worldTile[x][y];
	}

	public void setTile(int x, int y, Tile t) {
		worldTile[x][y] = t;
	}
}
