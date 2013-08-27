package state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageConsumer;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Dude {
	/**
	 * The coordinates of the tile under the bottom corner of the dude.
	 */
	private int x, y; // Tile coords of Dude
	private int TILE_HEIGHT = 32;
	private int TILE_WIDTH = 64;
	private int NUM_SPRITES = 16; // Number of model sprites per  images

	/**
	 * Size of the structure, in tiles.
	 */

	private final int FORWARD = 0, LEFT = 1, RIGHT = 3, BACK = 2; // Numerical constants for facing
		// NOTE: Usable as images array indices

	private int width, height; // ???
	private int facing = FORWARD; // Facing constant
	private Image[] images = new  Image[4]; // A single image stored per facing

	/**
	 * The structure's image.
	 */
	//private Image image;

	private World world;

	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public Image getImage() {
		return images[facing];
		}

	public Dude(World world, int x, int y, int width, int height, String image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		//this.image = new ImageIcon(image).getImage();
		this.world = world;
		JPanel panel = new JPanel(); // Instantiated JPanel to use createImage method

		// Load Images
		int num_images = NUM_SPRITES/4; // NOTE: Currently skips out all but first image of each facing

		for (int i = 0; i<4; i++){ // Iterate through facings --> Load an image into each facing
			// For animations this code will need to be extended to include an array of images per facing
			// Idea: Make images double array?

			images[i] = new ImageIcon(image).getImage();
			CropImageFilter filter = new CropImageFilter((images[i].getWidth(null)/NUM_SPRITES)*i*num_images,0,(images[i].getWidth(null)/NUM_SPRITES),images[i].getHeight(null));
			images[i] = panel.createImage(new FilteredImageSource(images[i].getSource(), filter));
		}
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

		// Tile coordinates of The Dude (x,y)
		int x = this.x - camx;
		int y = this.y - camy;

		// Pixel coordinates (on screen) of the Dude (i,j)
		int i = (width/2)-(images[facing].getWidth(null)/2) + (x-y) * (TILE_WIDTH/2);
		int j =  (x+y) * (TILE_HEIGHT/ 2) -images[facing].getHeight(null)-(TILE_HEIGHT/2);
		// Draw image at (i,j)
		g.drawImage(images[facing], i, j, images[facing].getWidth(null), images[facing].getHeight(null), null);

	}
}
