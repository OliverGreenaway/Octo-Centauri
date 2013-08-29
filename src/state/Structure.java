package state;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import UI.Display;

/**
 * Stores information about a structure.
 */
public class Structure implements Serializable {
	private final long serialVersionUID = 22222397388326626L;
	/**
	 * The coordinates of the tile under the bottom corner of the structure.
	 */
	private int x, y;
	private int TILE_HEIGHT = 32;
	private int TILE_WIDTH = 64;

	public final int WALL_HEALTH = 300;

	public int currentHealth = WALL_HEALTH; // !!!

	private transient BufferedImageOp filter;
	private transient BufferedImage bufferedImage;

	/**
	 * The world the structure is in.
	 */
	private transient World world; //We don't want to serialise the whole world

	/**
	 * Size of the structure, in tiles.
	 */
	private int width, height;

	/**
	 * The structure's image.
	 */
	private transient Image image; //We don't want to serialize this either

	//This is used to get the image from later
	private transient ImageIcon imageIcon; //And also don't want to serialize this

	/**
	 * Returns the X coordinate of the bottom corner of the structure.
	 */
	public int getX() {
		return x;
	}

	public void setImage(String image){
		File imgFile = new File(image);
		assert(imgFile.exists()) : image+" not found";
		try {
			imageIcon = new ImageIcon(image);
			this.image = new ImageIcon(image).getImage();
			bufferedImage = ImageIO.read(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read buffered image of structure: "+e.getMessage());
		}
	}

	/**
	 * Returns the Y coordinate of the bottom corner of the structure.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the width of the structure, in tiles.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the structure, in tiles.
	 */
	public int getHeight() {

		return height;
	}

	/**
	 * Returns the structure's image.
	 */
	public Image getImage(int viewRotation) {
		return image;
	}

	public void update() {
	}

	/**
	 * Creates a structure.
	 * @param x The X coordinate of the bottom corner of the structure.
	 * @param y The Y coordinate of the bottom corner of the structure.
	 * @param width The width of the structure.
	 * @param height The height of the structure.
	 * @param image The path to the structure's image.
	 */
	public Structure(int x, int y, int width, int height, String image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		File imgFile = new File(image);
		assert(imgFile.exists()) : image+" not found";
		try {
			imageIcon = new ImageIcon(image);
			this.image = new ImageIcon(image).getImage();
			bufferedImage = ImageIO.read(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to read buffered image of structure: "+e.getMessage());
		}
	}

	/**	/**
	 * Used when drawing the tile to check if its structure is transparent.
	 * @return the filter if there is one, or null if not.
	 */
	public BufferedImageOp getFilter(){
		if(filter == null)
			return null;
		return filter;
	}


	/**
	 * Adds a transparency filter to this structure.
	 * @param rs
	 */
	public void setFilter(RescaleOp rs){
		filter = rs;
	}

	 /** Draws the structure.
	 * @param g The Graphics object to draw on.
	 * @param d The Display.
	 * @param bottomPixelX The X coordinate of the bottom corner of the object
	 * @param bottomPixelY The Y coordinate of the bottom corner of the object
	 */
	public void draw(Graphics g, Display d, int bottomPixelX, int bottomPixelY) {
		if(filter != null){
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(bufferedImage, filter, bottomPixelX-image.getWidth(null)/2, bottomPixelY-image.getHeight(null));
		} else {
			Image i = getImage(d.getRotation());
			if(i != null)
				g.drawImage(i, bottomPixelX-image.getWidth(null)/2, bottomPixelY-image.getHeight(null), null);
		}
	}

	public void setWorld(World w) {
		assert world == null || world == w;
		world = w;
	}

	public World getWorld() {
		return world;

	}


}
