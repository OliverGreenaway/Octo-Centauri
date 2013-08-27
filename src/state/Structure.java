package state;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class Structure {
	/**
	 * The coordinates of the tile under the bottom corner of the structure.
	 */
	private int x, y;

	/**
	 * Size of the structure, in tiles.
	 */
	private int width, height;

	/**
	 * The structure's image.
	 */
	private Image image;

	public int getX() {return x;}
	public int getY() {return y;}
	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public Image getImage() {return image;}

	public Structure(int x, int y, int width, int height, String image) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		File imgFile = new File(image);
		assert(imgFile.exists());
		this.image = new ImageIcon(image).getImage();
	}

	public void draw(Graphics g, int x, int y){

		g.drawImage(image, x, y-image.getHeight(null), image.getWidth(null), image.getWidth(null), null);
	}
}
