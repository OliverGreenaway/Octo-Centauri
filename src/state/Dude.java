package state;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Dude {
	/**
	 * The coordinates of the tile under the bottom corner of the dude.
	 */
	private int x, y;
	private int TILE_HEIGHT = 32;
	private int TILE_WIDTH = 64;
	private int NUM_SPRITES = 16;

	/**
	 * Size of the structure, in tiles.
	 */
	private int width, height;

	/**
	 * The structure's image.
	 */
	private Image image;

	private World world;

	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public Image getImage() {return image;}

	public Dude(World world, int x, int y, int width, int height, String image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = new ImageIcon(image).getImage();
		this.world = world;
	}

	/**
	 * Tries to move the dude.
	 * @param newX The new X position.
	 * @param newY The new Y position.
	 * @return True if successful.
	 */
	public boolean move(int newX, int newY) {
		if(newX-width < -1 || newY-height < -1 || newX >= world.getXSize() || newY >= world.getYSize())
			return false;

		// check for overlap with other dudes
		for(int X = 0; X < width; X++)
			for(int Y = 0; Y < height; Y++) {
				Tile tile = world.getTile(x-X, y-Y);
				if(tile.getDude() != null && tile.getDude() != this)
					return false;
			}

		// unlink the tiles at the old location
		for(int X = 0; X < width; X++)
			for(int Y = 0; Y < height; Y++)
				world.getTile(x-X, y-Y).setDude(null);

		// update the location
		x = newX;
		y = newY;

		// link the tiles at the new location
		for(int X = 0; X < width; X++)
			for(int Y = 0; Y < height; Y++)
				world.getTile(x-X, y-Y).setDude(this);

		return true;
	}

	public void update() {
		// move randomly
		int r = new Random().nextInt(4);
		if(r == 0) move(x+1, y);
		if(r == 1) move(x-1, y);
		if(r == 2) move(x, y+1);
		if(r == 3) move(x, y-1);
	}

	public void draw(Graphics g, int width, int camx, int camy){
		int x = this.x - camx;
		int y = this.y - camy;
		int i = (width/2)-(image.getWidth(null)/2) + (x-y) * (TILE_WIDTH/2);
		int j =  (x+y) * (TILE_HEIGHT/ 2) ;
		g.drawImage(image, i, j-image.getHeight(null), image.getWidth(null), image.getWidth(null), null);
	}
}
