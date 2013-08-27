package state;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageConsumer;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Stores information about a dude.
 */
public class Dude implements Serializable{
	public final long serialVersionUID = 55558278392826626L;
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

	private final int UP = 0, LEFT = 1, RIGHT = 3, DOWN = 2; // Numerical constants for facing
		// NOTE: Usable as images array indices

	private int width, height; // ???
	private int facing = UP; // Facing constant
	private Image[] images = new  Image[4]; // A single image stored per facing

	/**
	 * The dude's image.
	 */
	//private Image image;

	private World world;

	/**
	 * Returns the X coordinate of the bottom corner of the dude.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the Y coordinate of the bottom corner of the dude.
	 */
	public int getY() {
		return y;
	}
	/**
	 * Returns the width of the dude, in tiles.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the dude, in tiles.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the dude's image.
	 */
	public Image getImage() {
		return images[facing];
	}

	/**
	 * Creates a dude.
	 * @param world The world the dude is in.
	 * @param x The X coordinate of the bottom corner of the dude.
	 * @param y The Y coordinate of the bottom corner of the dude.
	 * @param width The width of the dude.
	 * @param height The height of the dude.
	 * @param image The path to the dude's image.
	 */
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

		try{
		for (int i = 0; i<4; i++){ // Iterate through facings --> Load an image into each facing
			// For animations this code will need to be extended to include an array of images per facing
			// Idea: Make images double array?

			images[i] = new ImageIcon(image).getImage();
			CropImageFilter filter = new CropImageFilter((images[i].getWidth(null)/NUM_SPRITES)*i*num_images,0,(images[i].getWidth(null)/NUM_SPRITES),images[i].getHeight(null));
			images[i] = panel.createImage(new FilteredImageSource(images[i].getSource(), filter));
		}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, "Image Not Found", "Warning", JOptionPane.WARNING_MESSAGE);
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

	/**
	 * Called every tick. Does stuff.
	 */
	public void update() {
		// move randomly
		int r = new Random().nextInt(4);
		if(r == 0) {
			move(x+1, y);
			facing = RIGHT;
		}
		if(r == 1){
			move(x-1, y);
			facing = LEFT;
		}
		if(r == 2){
			move(x, y+1);
			facing = DOWN;
		}
		if(r == 3){
			move(x, y-1);
			facing = UP;
		}
	}

	/**
	 * Draws the dude.
	 * @param g The Graphics object to draw on.
	 * @param width The width of the display.
	 * @param camx The camera X.
	 * @param camy The camera Y.
	 */
	public void draw(Graphics g, int width, int camx, int camy){

		// Tile coordinates of The Dude (x,y)
		int x = this.x - camx +1;
		int y = this.y - camy +1;

		// Pixel coordinates (on screen) of the Dude (i,j)
		int i = (width/2)-(images[facing].getWidth(null)/2) + (x-y) * (TILE_WIDTH/2);
		int j =  (x+y) * (TILE_HEIGHT/ 2) -images[facing].getHeight(null)-(TILE_HEIGHT/2);
		// Draw image at (i,j)
		g.drawImage(images[facing], i, j, images[facing].getWidth(null), images[facing].getHeight(null), null);

	}
}
