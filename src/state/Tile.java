package state;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.Serializable;

import javax.swing.JOptionPane;

import util.TileImageStorage;

/**
 * A tile in the world.
 */
public class Tile implements Serializable {
	private final long serialVersionUID = 7789269378738222222L;
	// FIELDS
	private transient Image img;
	private int x;
	private int y;
	private World world;
	private boolean visited;
	private boolean occupied;
	private String type;

	public boolean hasDigTask = false;


	private boolean collidable;
	private transient RescaleOp filter;

	// height: 0 = flat plain 1 = the step above 2 = above that so on and so forth
	//		   the height drawing is handled by the display class
	//		   some kind of ramping theory needs to exist to make this less arbitrary

	private int height;
	private Structure structure;
	private Dude dude;
	private String imgName; // Used to save the image for de-serializing later

	// cache of all images loaded so far
	private static TileImageStorage imagesCache = new TileImageStorage();

	/**
	 * Creates a tile.
	 *
	 * @param type
	 *            The icon name.
	 * @param ht
	 *            The height.
	 * @param x
	 *            The X coordinate.
	 * @param y
	 *            The Y coordinate.
	 */
	public Tile(String type, int ht, World world, int x, int y) {
		this.type = type;
		this.imgName = type;
		try {
			imgName = type;
			imagesCache.add(type);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found x01", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		height = ht;
		this.x = x;
		this.y = y;
		this.world = world;
		collidable = false; //A normal tile is not collidable by default
	}

	public static TileImageStorage getImagesCache() {
		return imagesCache;
	}

	/**
	 * Returns the tile's icon.
	 */
	public BufferedImage getImage() {
		return imagesCache.get(imgName);
	}

	/**
	 * Returns the tile's X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the tile's Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the tile's coordinates as a Point.
	 */
	public Point getPoint() {
		return new Point(x, y);
	}

	/**
	 * Returns this tile's height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets this tile's height.
	 */
	public void setHeight(int h) {
		height = h;
	}

	/**
	 * Returns the structure on this tile.
	 */
	public Structure getStructure() {
		return structure;
	}

	/**
	 * Sets the structure on this tile.
	 * @param c - whether this structure is collidable or not
	 */
	public void setStructure(Structure s, boolean c) {
		structure = s;
		collidable = c;
		world.getLogic().mapChanged(x, y);
	}

	/**
	 * Returns the dude on this tile.
	 */
	public Dude getDude() {
		return dude;
	}

	/**
	 * Sets the dude on this tile.
	 */
	public void setDude(Dude d) {
		dude = d;
	}


	public Color getColor() {
		return imagesCache.getColor(imgName);
		/*BufferedImage img = getImage();
		if (img != null) {
			if (storedColor != null) {
				return storedColor;
			}
			int r = 0;
			int g = 0;
			int b = 0;
			int count = 0;
			for (int i = 0; i < img.getWidth(); i++) {
				for (int j = 0; j < img.getHeight(); j++) {
					Color temp = new Color(img.getRGB(i, j), true);
					if (temp.getAlpha() > 0) {

						r += temp.getRed();
						b += temp.getBlue();
						g += temp.getGreen();
						count++;
					}
				}
			}
			storedColor = new Color((int) (r / count), (int) (g / count),
					(int) (b / count));
			return storedColor;
		} else {
			return Color.BLACK;
		}*/
	}



	public void setImage(String string) {
		imagesCache.add(string);
		imgName = string;
		world.getLogic().mapChanged(x, y);
	}

	public void setWorld(World world) {
		assert this.world == null || this.world == world;
		this.world = world;
	}

	public String getImageName() {
		return imgName;
	}

	public boolean isTraversible(){
		if(type.equals("Water"))
			return false;

		return true;
	}

}


