package state;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

public class Tile implements TileInterface{
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

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Point getPoint() {
		return new Point(x, y);
	}

	@Override
	public boolean visited() {
		return visited;
	}

	@Override
	public boolean occupied() {
		return occupied;
	}

	@Override
	public void setVisited(boolean b) {
		visited = b;
	}

	@Override
	public void setPrevTile(TileInterface tileInterface) {
		// TODO Auto-generated method stub

	}

	@Override
	public TileInterface getPrevTile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
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

