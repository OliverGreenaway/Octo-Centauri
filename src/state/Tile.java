package state;
import java.awt.Image;
import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;

public class Tile {
	// FIELDS
	private Image img;
	private int x;
	private int y;
	private boolean visited;
	private boolean occupied;
	private int height;
	private Image leftSideImg, rightSideImg;
	private Structure structure;
	private Dude dude;


	public Tile(String type){
		img = new ImageIcon("Assets/Environment_Tiles/"+type+".png").getImage();
		File tileFile = new File("Assets/Environment_Tiles/"+type+".png");
		assert(tileFile.exists());
		leftSideImg = new ImageIcon("Assets/Environment Tiles/WestFacingDirt.png").getImage();
		rightSideImg = new ImageIcon("Assets/Environment Tiles/EastFacingDirt.png").getImage();
	}

	public Image getImage(){
		return img;
	}

	public void setX(int x){
		this.x = x;
	}

	public void setY(int y){
		this.y = y;
	}

	public Image getLeftSideImg() {
		return leftSideImg;
	}

	public Image getRightSideImg() {
		return rightSideImg;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getPoint() {
		return new Point(x, y);
	}

	public boolean visited() {
		return visited;
	}

	public boolean occupied() {
		return occupied;
	}

	public void setVisited(boolean b) {
		visited = b;
	}

	public void setPrevTile(Tile tileInterface) {
		// TODO Auto-generated method stub

	}

	public Tile getPrevTile() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		height = h;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure s) {
		structure = s;
	}

	public Dude getDude() {
		return dude;
	}

	public void setDude(Dude d) {
		dude = d;
	}
}

