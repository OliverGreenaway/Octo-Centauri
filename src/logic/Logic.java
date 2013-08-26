package logic;


import state.TileInterface;

>>>>>>> 8f2bbfd473df82cb02e98a1bc26664ba4f4f2239
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
<<<<<<< HEAD
=======
import java.util.PriorityQueue;
>>>>>>> 8f2bbfd473df82cb02e98a1bc26664ba4f4f2239
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import state.GameObject;
import state.TileInterface;


public class Logic {

	private Map<String, List<String>> menus;
	private TileInterface[][] tiles = new TileInterface[20][20];
//	private TileInterface[][] tiles = World.getTiles();

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

	/**
	 * Given a start point and goal point, returns a Stack of the route to take using A* algorithm.
	 *
	 * @return Stack route - route to be followed from first value popped, through to goal at the bottom of the stack
	 * @param routeStart - the point to begin path from
	 * @param routeGoal - the goal point of the path
	 */
	public Stack<Point> findRoute(Point routeStart, Point routeGoal) {
		Stack<Point> route = new Stack<Point>(); //to return

		PriorityQueue<Tuple> fringe = new PriorityQueue<Tuple>();

		fringe.add(new Tuple(routeStart, null, routeGoal, calcHeuristic(
				routeStart, routeGoal), 0));
		// Repeat until fringe is empty:
		while (!fringe.isEmpty()) {
			Tuple currentTuple = fringe.poll();
			TileInterface currentTile = tiles[(int) currentTuple.getPoint().getX()][(int) currentTuple
					.getPoint().getY()];
			if (!currentTile.occupied()) {
				// If not tile.visited then
				if (!currentTile.visited()) {
					currentTile.setVisited(true);
					// If tile = goal then exit
					if (!currentTile.equals(routeGoal)) {
						// for each edge to neigh out of node

						for (TileInterface t : getNeighbours(currentTile)) {
							// if neighbour tile hasn't been visited then
							if (!t.visited()) {
								fringe.offer(new Tuple(
										t.getPoint(),
										currentTile.getPoint(),
										routeGoal,
										calcHeuristic(t.getPoint(), routeGoal),
										currentTuple.getCostToHere() + 1));
								t.setPrevTile(tiles[(int) currentTuple.getPoint().getX()][(int) currentTuple.getPoint().getY()]);
							}
						}
					} else {
						//found the goal so go back through the tuples adding it and it's previous point to the route Stack
						route.add(currentTile.getPoint());
						TileInterface tile = currentTile.getPrevTile();
						while(tile != null){
							route.add(tile.getPoint());
							tile = currentTile.getPrevTile();
						}
					}
				}
			}
		}
		return route;
	}

	/**
	 * Calculates straight line heuristic between two point start and goal
	 */
	private double calcHeuristic(Point routeStart, Point routeGoal) {
		return routeStart.distance(routeGoal);
	}

	/**
	 * Tuples of a point and it's previous point for A* path calculation.
	 */
	private class Tuple implements Comparable {
		private double heuristic;
		private Point point;
		private Point prevPoint;
		private Point goal;
		private int costToHere;
		private double estTotalCost;//cost to this point + heuristic to goal

		public Tuple(Point point, Point prevPoint, Point goal,
				double heuristic, int costToHere) {
			this.heuristic = heuristic;
			this.costToHere = costToHere;
			this.point = point;
			this.prevPoint = prevPoint;
			this.estTotalCost = costToHere + heuristic;
			this.prevPoint = prevPoint;
		}

		public Object getPrevPoint() {
			return prevPoint;
		}

		public double getEstTotalCost() {
			return estTotalCost;
		}

		public int getCostToHere() {
			return costToHere;
		}

		public Point getPoint() {
			return point;
		}

		@Override
		public int compareTo(Object t) {
			if (t != null && this.getClass().equals(t.getClass())) {
				if (estTotalCost - ((Tuple) t).getEstTotalCost() > 0)
					return 1;// this is more costly
				if (estTotalCost - ((Tuple) t).getEstTotalCost() < 0)
					return -1;// this is less costly
				if (estTotalCost - ((Tuple) t).getEstTotalCost() == 0)
					return 0;
			}
			throw new IllegalArgumentException("Need to compare same type");
		}

	}


}



