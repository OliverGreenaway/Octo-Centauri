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

	private BufferedImageOp filter;
	private BufferedImage bufferedImage;

	/**
	 * Size of the structure, in tiles.
	 */
	private int width, height;

	/**
	 * The structure's image.
	 */
	private Image image;

	//This is used to get the image from later
	private ImageIcon imageIcon;

	/**
	 * Returns the X coordinate of the bottom corner of the structure.
	 */
	public int getX() {
		return x;
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
	public Image getImage() {
		return image;
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
		//bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
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
	 /** Draws the structure.
	 * @param g The Graphics object to draw on.
	 * @param width The width of the display.
	 * @param camx The camera X.
	 * @param camy The camera Y.
	 */
	public void draw(Graphics g, int width, int height, int camx, int camy){
		int x = this.x - camx; //tile coords of structure
		int y = this.y - camy; //tile coords of structure
		int i = (width/2)-(image.getWidth(null)/2) + (x-y) * (TILE_WIDTH/2); //pixel coords of the structure
		int j =  (x+y) * (TILE_HEIGHT/ 2) - height ;									 //pixel coords of the structure
		//draws the structure at (i,j) using the images dimensions to find its final dimension
		if(filter != null){
			Graphics2D g2d = (Graphics2D) g;
			g2d.drawImage(bufferedImage, filter, i, j-image.getHeight(null));
		}
		else
			g.drawImage(image, i, j-image.getHeight(null), image.getWidth(null), image.getHeight(null), null);
	}

	/**
	 * Adds a transparency filter to this structure.
	 * @param rs
	 */
	public void setFilter(RescaleOp rs){
		filter = rs;
	}
}
