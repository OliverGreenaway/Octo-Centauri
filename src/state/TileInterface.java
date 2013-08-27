package state;

import java.awt.Image;
import java.awt.Point;

public interface TileInterface {

	/**
	 *
	 * @return - the x-coordinate of this tile.
	 */
	public int getX();

	/**
	 *
	 * @return - the y-coordinate of this tile.
	 */
	public int getY();

	public void setY(int y);

	public void setX(int x);

	/**
	 *
	 * @return - the x,y point of this tile.
	 */
	public Point getPoint();

	/**
	 * Used by path-searching algorithm in Logic.
	 * @return - whether this tile has been visited in the path search.
	 */
	public boolean visited();

	/**
	 * Checks whether this tile is occupied.  Used by path-finding
	 * algorithm in Logic.
	 * @return - whether this tile is occupied or not.
	 */
	public boolean occupied();

	public void setVisited(boolean b);

	public void setPrevTile(TileInterface tileInterface);

	public TileInterface getPrevTile();

	public void setHeight(int height);

	public int getHeight();

	public Structure getStructure();

	public void setStructure(Structure s);

	public Image getImage();
}
