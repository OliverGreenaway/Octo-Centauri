package logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import networking.common.Update;
import state.Dude;
import state.Structure;
import state.Tile;

/**
 * The GameUpdate class contains fields representing changes in the game state.
 * It is used to send updates across the network.
 * @author markovmadd
 *
 */
public class GameUpdate extends Update {
	private final long serialVersionUID = 748279847839274987L;

	private List<Structure> structuresAdded;
	private List<Structure> structuresRemoved;
	private List<Dude> dudesAdded;
	private List<Dude> dudesRemoved;

	private List<Tile> colourChangedTiles;
	private Map<Dude, Integer> dudeHealthChanges;


	public GameUpdate() {
		structuresAdded = new ArrayList<Structure>();
		structuresRemoved = new ArrayList<Structure>();
		dudesAdded = new ArrayList<Dude>();
		dudesRemoved = new ArrayList<Dude>();
		dudeHealthChanges = new HashMap<Dude, Integer>();
		colourChangedTiles = new ArrayList<Tile>();
	}

	/**
	 * Called by World? whenever a structure is added
	 * @param s - the structure that was added.
	 */
	public void structureAdded(Structure s){
		structuresAdded.add(s);
	}

	/**
	 * Called by World whenever a structure is removed
	 * @param s - the structure that was removed.
	 */
	public void structureRemoved(Structure s){
		structuresRemoved.add(s);
		//For the case where a structure was added but then immdiately removed:
		if(structuresAdded.contains(s))
			structuresAdded.remove(s);
	}

	/**
	 * Called by World whenever a Dude is added
	 * @param d - the Dude that was added
	 */
	public void dudeAdded(Dude d){
		dudesAdded.add(d);
	}

	/**
	 * Called by World whenever a dude is removed.
	 * @param d - the dude that was removed.
	 */
	public void dudeRemoved(Dude d){
		dudesRemoved.add(d);
		//For the unusual case where a dude was created and then died immediately:
		if(dudesAdded.contains(d))
			dudesAdded.remove(d);
	}

	/**
	 * Called by World in the test where clicking on a tile changes its colour.
	 * @param t - the tile that was clicked on.
	 */
	public void changedTileColour(Tile t){
		colourChangedTiles.add(t);
	}

}
