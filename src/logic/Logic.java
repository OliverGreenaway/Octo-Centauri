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
	private int level;

	private int[][] access;

	public Logic(World world) {
		this.world = world;
		level = 1;
		access = new int[world.getXSize()][world.getYSize()];
		mapCreated();
	}

	/**
	 * Right-clicking brings up the context menu.
	 *
	 * @param object
	 *            - the item or tile selected
	 * @return - stuff to go in the context menu
	 */
	public Menu rightClick(Object object) {
		return null;
	}

	/**
	 * Left-clicking selects an object or character.
	 */
	public void leftClick(Object object) {

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
		return toAdd;
	}

	public void mapChanged(int i, int j) {
		Stack<FourTupleInt> stack = new Stack<FourTupleInt>();

		access[i][j] = level;

		stack.push(new FourTupleInt(i + 1, j, i, j));
		stack.push(new FourTupleInt(i, j + 1, i, j));
		stack.push(new FourTupleInt(i, j - 1, i, j));
		stack.push(new FourTupleInt(i - 1, j, i, j));


		while (!stack.isEmpty()) {
			FourTupleInt current = stack.pop();
			int x = current.getCur().x;
			int y = current.getCur().y;
			int prevX = current.getPrev().x;
			int prevY = current.getPrev().y;

			if (x < 0 || x >= access.length || y < 0 || y >= access[0].length
					|| access[x][y] == level) {
				continue;
			}
			if ((world.getTile(prevX, prevY).getHeight() == world.getTile(x, y)
					.getHeight())) {
				access[x][y] = access[prevX][prevY];
				if (x < access.length - 1 && access[x + 1][y] != level) {
					stack.push(new FourTupleInt(x + 1, y, x, y));
				}
				if (y < access[x].length - 1 && access[x][y + 1] != level) {
					stack.push(new FourTupleInt(x, y + 1, x, y));
				}
				if (y > 0 && access[x][y - 1] != level) {
					stack.push(new FourTupleInt(x, y - 1, x, y));
				}
				if (x > 0 && access[x - 1][y] != level) {
					stack.push(new FourTupleInt(x - 1, y, x, y));
				}
			}
		}
		level++;
	}

	public void mapCreated() {
		access = new int[world.getXSize()][world.getYSize()];
		for (int i = 0; i < access.length; i++) {
			for (int j = 0; j < access.length; j++) {
				access[i][j] = 0;
			}
		}

		Stack<FourTupleInt> stack = new Stack<FourTupleInt>();
		for (int i = 0; i < access.length; i++) {
			for (int j = 0; j < access[i].length; j++) {
				if (access[i][j] == 0) {
					access[i][j] = level;

					if (i < access.length - 1 && access[i + 1][j] == 0) {
						stack.push(new FourTupleInt(i + 1, j, i, j));
					}
					if (j < access[i].length - 1 && access[i][j + 1] == 0) {
						stack.push(new FourTupleInt(i, j + 1, i, j));
					}
					if (j > 0 && access[i][j - 1] == 0) {
						stack.push(new FourTupleInt(i, j - 1, i, j));
					}
					if (i > 0 && access[i - 1][j] == 0) {
						stack.push(new FourTupleInt(i - 1, j, i, j));
					}

					while (!stack.isEmpty()) {
						FourTupleInt current = stack.pop();

						int x = current.getCur().x;
						int y = current.getCur().y;
						int prevX = current.getPrev().x;
						int prevY = current.getPrev().y;
						if (x < 0 || x >= access.length || y < 0
								|| y >= access[0].length || access[x][y] != 0) {
							continue;
						}
						if ((world.getTile(prevX, prevY).getHeight() == world
								.getTile(x, y).getHeight())) {
							access[x][y] = access[prevX][prevY];
							if (x < access.length - 1 && access[x + 1][y] == 0) {
								stack.push(new FourTupleInt(x + 1, y, x, y));
							}
							if (y < access[x].length - 1
									&& access[x][y + 1] == 0) {
								stack.push(new FourTupleInt(x, y + 1, x, y));
							}
							if (y > 0 && access[x][y - 1] == 0) {
								stack.push(new FourTupleInt(x, y - 1, x, y));
							}
							if (x > 0 && access[x - 1][y] == 0) {
								stack.push(new FourTupleInt(x - 1, y, x, y));
							}
						}
					}
					level++;
				}
			}
		}
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
	public Stack<Tile> findRoute(Tile start, Tile target, Dude dude) {
		Stack<Tile> route = new Stack<Tile>();
		HashSet<Tile> visited = new HashSet<Tile>();
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();

		if (access[start.getX()][start.getY()] != access[target.getX()][target
				.getY()]) {
			return new Stack<Tile>();
		}

		// Enqueue root
		fringe.offer(new SearchNode(start, target, 0, null));

		if (target.getDude() != null && target.getDude() != dude) {
			for (int i = 0; i < new Random().nextInt(10); i++) {
				List<Tile> n = getNeighbours(target, dude);
				int temp = new Random().nextInt(n.size() + 1);
				if (!(temp < 0 || temp >= n.size())) {
					target = n.get(temp);
				}


			}

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

	private class FourTupleInt {

		private Point cur, prev;

		public FourTupleInt(int x, int y, int prevX, int prevY) {
			this.cur = new Point(x, y);
			this.prev = new Point(prevX, prevY);
		}

		public Point getCur() {
			return cur;
		}

		public Point getPrev() {
			return prev;
		}
	}

}
