package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import state.GameObject;
import state.TileInterface;


public class Logic {

	private Map<String, List<String>> menus;
	private TileInterface[][] tiles = new TileInterface[20][20];

	public Logic() {
		menus = FileReader.readMenus("menu_mappings.tab");
	}

	/**
	 * Right-clicking brings up the context menu.
	 * @param object - the item or tile selected
	 * @return - stuff to go in the context menu
	 */
	public Menu rightClick(GameObject object){
		//if(object instanceof GroundTile){
		//	return new Menu(menus.get("GroundTile"));
		//}
		//else if (object instanceof Building){
		//	if(object instanceof Barracks){
//
	//		}
	//		if(object instanceof Mill){

	//		}
	//	}
	//	else{
	//		System.out.println("Was not a game object.");
	//	}
		return null;
	}


	/**
	 * Left-clicking selects an object or character.
	 */
	public void leftClick(GameObject object){
//	if(object instanceof GroundTile){
//
//		}
//		else if (object instanceof Building){
//			if(object instanceof Barracks){
//
//			}
//			if(object instanceof Mill){
//
//			}
//		}
//	}
//	else{
//		System.out.println("Was not a game object.");
	}

	private List<TileInterface> getNeighbours(TileInterface tile){
		int x = tile.getX();
		int y = tile.getY();
		List<TileInterface> neighbours = new ArrayList<TileInterface>();
		neighbours.add(tiles[x-1][y]);
		neighbours.add(tiles[x+1][y]);
		neighbours.add(tiles[x][y+1]);
		neighbours.add(tiles[x][y-1]);
		neighbours.add(tiles[x-1][y-1]);
		neighbours.add(tiles[x+1][y+1]);
		neighbours.add(tiles[x-1][y+1]);
		neighbours.add(tiles[x+1][y-1]);
		return neighbours;
	}

}



