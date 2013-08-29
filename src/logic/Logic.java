package logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import state.Dude;
import state.Tile;
import state.World;

public class Logic {

	private Map<String, List<String>> menus;
	private World world;

	// private Tile[][] tiles = new Tile[20][20];
	// private Tile[][] tiles = World.getTiles();

	public Logic(World world) {
		this.world = world;
		// menus = FileReader.readMenus("menu_mappings.tab");
	}

	/**
	 * Right-clicking brings up the context menu.
	 *
	 * @param object
	 *            - the item or tile selected
	 * @return - stuff to go in the context menu
	 */
	public Menu rightClick(Object object) {
		// if(object instanceof GroundTile){
		// return new Menu(menus.get("GroundTile"));
		// }
		// else if (object instanceof Building){
		// if(object instanceof Barracks){
		//
		// }
		// if(object instanceof Mill){

		// }
		// }
		// else{
		// System.out.println("Was not a game object.");
		// }
		return null;
	}

	/**
	 * Left-clicking selects an object or character.
	 */
	public void leftClick(Object object) {
		// if(object instanceof GroundTile){
		//
		// }
		// else if (object instanceof Building){
		// if(object instanceof Barracks){
		//
		// }
		// if(object instanceof Mill){
		//
		// }
		// }
		// }
		// else{
		// System.out.println("Was not a game object.");
	}

	private List<Tile> getNeighbours(Tile tile, Dude dude) {
		int x = tile.getX();
		int y = tile.getY();
		List<Tile> neighbours = new ArrayList<Tile>();
		neighbours.add(world.getTile(x - 1, y));
		neighbours.add(world.getTile(x, y - 1));
		neighbours.add(world.getTile(x, y + 1));
		neighbours.add(world.getTile(x + 1, y));
		List<Tile> toAdd = new ArrayList<Tile>();
		for (Tile n : neighbours) {
			if (n != null) {
				if (dude.canMove(tile, n)) {
					toAdd.add(n);
				}
			}
		}
		// neighbours.add(tiles[x-1][y-1]);
		// neighbours.add(tiles[x+1][y+1]);
		// neighbours.add(tiles[x-1][y+1]);
		// neighbours.add(tiles[x+1][y-1]);
		return toAdd;
	}

	/**
	 * Given a start point and goal point, returns a Stack of the route to take
	 * using A* algorithm.
	 *
	 * @return Stack route - route to be followed from first value popped,
	 *         through to goal at the bottom of the stack
	 * @param routeStart
	 *            - the point to begin path from
	 * @param routeGoal
	 *            - the goal point of the path
	 */

	public Stack<Tile> findRoute(Tile start, Tile target, Dude dude) { // TODO
																		// Needs
																		// to be
		// extended to
		// account for
		// obstacles
		Stack<Tile> route = new Stack<Tile>();
		HashSet<Tile> visited = new HashSet<Tile>();
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();

		// Enqueue root
		fringe.offer(new SearchNode(start, target, 0, null));

		if (target.getDude() != null && target.getDude() != dude) {

			List<Tile> n = getNeighbours(target, dude);
			target = n.get(new Random().nextInt(n.size()-1));
		}

		while (!fringe.isEmpty()) {
			SearchNode sNode = fringe.poll();
			Tile tile = sNode.tile;

			if (!visited.contains(tile)) {
				visited.add(tile);
				// node.pathFrom <- from
				if (tile == target) {
					// EXIT
					while (sNode.parent != null) {
						route.push(sNode.tile);
						sNode = sNode.parent;
					}
					return route;
				}
				List<Tile> neighbours = getNeighbours(tile, dude);
				for (Tile neighbour : neighbours) {
					if (neighbour != null && !visited.contains(neighbour)) {
						fringe.offer(new SearchNode(neighbour, target,
								sNode.costToStart + 1, sNode));
					}
				}
			}
		}
		return new Stack<Tile>();

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
		private double estTotalCost;// cost to this point + heuristic to goal

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

			return 0;
		}

	}

}
