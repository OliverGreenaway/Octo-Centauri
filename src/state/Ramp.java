package state;

import java.awt.Image;

import javax.swing.ImageIcon;

import util.TileImageStorage;

public class Ramp extends Structure {
	/** The direction the "up" side of the ramp is in. */
	private Direction direction;

	public Direction getDirection() {
		return direction;
	}

	private Image leftImage, rightImage;

	public Ramp(int x, int y, int width, int height, String type, Direction dir) {
		super(x, y, width, height, "Assets/EnvironmentTiles/EastFace"+type+".png");
		direction = dir;
		leftImage = super.getImage(0);
		rightImage = new ImageIcon("Assets/EnvironmentTiles/WestFace"+type+".png").getImage();
	}

	@Override
	public Image getImage(int viewRotation) {
		return new Image[] {
			rightImage, null, null, leftImage
		}[(viewRotation + 4 - direction.ordinal()) % 4];
	}
}
