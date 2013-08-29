/**
 *
 */
package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

import javax.swing.ImageIcon;

import UI.Display;

/**
 * @author findlaconn
 *
 */
public class Octodude extends Dude implements Serializable{


	private transient Image img[];

	public Octodude(World world, int x, int y, int width, int height,
			String image) {
		super(world, x, y, width, height, image);
		maxHealth = 50;
	}

	@Override
	protected void loadImage(String image) {
		img = new Image[4];
		img[RIGHT] = new ImageIcon("Assets/Characters/Enemies/AlienOctopus/EyeFrontRight.png").getImage();
		img[DOWN] = new ImageIcon("Assets/Characters/Enemies/AlienOctopus/EyeFrontLeft.png").getImage();
		img[UP] = new ImageIcon("Assets/Characters/Enemies/AlienOctopus/EyeBackRight.png").getImage();
		img[LEFT] = new ImageIcon("Assets/Characters/Enemies/AlienOctopus/EyeBackLeft.png").getImage();
	}

	@Override
	public void draw(Graphics g, Display d, int bottomPixelX, int bottomPixelY,
			boolean drawHealth){

		double percentMoved = count * 0.25;

		// Tile coordinates of The Dude (x,y)
		double x, y;
		if(attacking == null) {
			x = this.oldX + (this.x - this.oldX) * percentMoved;
			y = this.oldY + (this.y - this.oldY) * percentMoved;
		} else {
			double dist = (count % 2 == 1) ? 0.2 : 0.1;
			x = this.x + (facing == LEFT ? -1 : facing == RIGHT ? 1 : 0) * dist;
			y = this.y + (facing == UP ? -1 : facing == DOWN ? 1 : 0) * dist;
		}

		// Pixel coordinates (on screen) of the Dude (i,j)
		Point pt = d.tileToDisplayCoordinates(x, y);

		int height = world.getTile(this.x, this.y).getHeight();
		int oldHeight = world.getTile(oldX, oldY).getHeight();

		pt.y -= TILE_HEIGHT * (oldHeight + (height - oldHeight) * percentMoved);
		//pt.y -= TILE_HEIGHT / 2;

		Image i = img[(facing + d.getRotation()) % 4];

		// Draw image at (i,j)

		int posX = pt.x - i.getWidth(null) / 2;
		int posY = pt.y - i.getHeight(null);

		g.drawImage(i, posX, posY, null);

		if (drawHealth) {
			int tall = 10;
			int hHeight = 4;
			int hWidth = 16;
			g.setColor(Color.red);
			g.fillRect(posX, posY - tall, hWidth, hHeight);
			g.setColor(Color.green);
			g.fillRect(posX, posY - tall, (int)(hWidth * currentHealth / (float)maxHealth), hHeight);
		}

	}
	private static final long serialVersionUID = -5458456094734079547L;

	@Override
	protected void idle() {
		boolean moved=false;
		switch(facing) {
		case UP: moved=move(x, y-1); break;
		case DOWN: moved=move(x, y+1); break;
		case LEFT: moved=move(x-1, y); break;
		case RIGHT: moved=move(x+1, y); break;
		}
		Random r = new Random();
		if(!moved || r.nextInt(8) == 0)
			facing = r.nextInt(4);
	}

	@Override
	public boolean canMine(Resource r) {
		return r instanceof Crystal && ((Crystal)r).shouldOctoMine();
	}

	@Override
	protected void harvest(Resource harvesting) {
		harvesting.harvest(); // don't store
	}

}
