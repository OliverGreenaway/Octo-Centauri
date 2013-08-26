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
	private Point p;


	public Tile(String type){
		img = new ImageIcon("/Assets/Environment_Tiles/"+type+".png").getImage();
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
		return p;
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

}
