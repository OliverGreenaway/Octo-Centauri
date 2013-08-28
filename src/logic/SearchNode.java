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
		double xSquared = Math.pow((current.getX()-target.getX()),2);
		double ySquared = Math.pow((current.getY()-target.getY()),2);
		return (int)(Math.sqrt(xSquared +ySquared));
	}

	@Override
	public int compareTo(Object o) {
		SearchNode sNode = (SearchNode)o;
		return (this.heuristicTotal-sNode.heuristicTotal);
	}

}
