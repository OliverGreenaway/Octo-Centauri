package state;

public enum Direction {
	DOWN, LEFT, RIGHT, UP;

	public static Direction getDirectionBetween(Tile from, Tile to) {
		if(to.getX() == from.getX() + 1 && to.getY() == from.getY())
			return Direction.RIGHT;
		if(to.getX() == from.getX() - 1 && to.getY() == from.getY())
			return Direction.LEFT;
		if(to.getX() == from.getX() && to.getY() == from.getY() - 1)
			return Direction.UP;
		if(to.getX() == from.getX() && to.getY() == from.getY() + 1)
			return Direction.DOWN;
		throw new IllegalArgumentException((to.getX()+"/"+to.getY())+" is not next to "+from.getX()+"/"+from.getY());
	}
}
