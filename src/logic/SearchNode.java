package logic;

import state.Tile;

public class SearchNode implements Comparable{
	public final Tile tile;
	public final int costToStart;
	public final int heuristicTotal;
	public final SearchNode parent;

	public SearchNode(Tile t, Tile target, int cts, SearchNode p){
		parent = p;
		costToStart = cts;
		heuristicTotal = cts + calcHeuristic(t,target);
		tile = t;
	}

	public int calcHeuristic(Tile current,Tile target){
		int x = Math.abs(current.getX()-target.getX());
		int y = Math.abs(current.getY()-target.getY());
		return x +y;
	}

	@Override
	public int compareTo(Object o) {
		SearchNode sNode = (SearchNode)o;
		return (this.heuristicTotal-sNode.heuristicTotal);
	}

}
