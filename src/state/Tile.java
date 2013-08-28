package state;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.Serializable;

import javax.swing.ImageIcon;
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
	private boolean visited;
	private boolean occupied;


	private boolean collidable;
	private RescaleOp filter;

	// height: 0 = flat plain 1 = the step above 2 = above that so on and so forth
	//		   the height drawing is handled by the display class
	//		   some kind of ramping theory needs to exist to make this less arbitrary

	private int height;
	private transient Image leftSideImg, rightSideImg;
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
	public Tile(String type, int ht, int x, int y) {
		this.imgName = type;
		try {
			imgName = type;
			File tileFile = new File("Assets/EnvironmentTiles/" + type + ".png");
			assert (tileFile.exists());
			imagesCache.add(type);
			leftSideImg = new ImageIcon(
					"Assets/EnvironmentTiles/WestFacingDirt.png").getImage();
			rightSideImg = new ImageIcon(
					"Assets/EnvironmentTiles/EastFacingDirt.png").getImage();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Image Not Found x01", "Warning",
					JOptionPane.WARNING_MESSAGE);
		}
		height = ht;
		this.x = x;
		this.y = y;
		collidable = false; //A normal tile is not collidable by default
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
	}

}


