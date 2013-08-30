package logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import networking.common.Update;
import state.Dude;
import state.Structure;
import state.Tile;
import state.World;

/**
 * The GameUpdate class contains fields representing changes in the game state.
 * It is used to send updates across the network.
 * @author markovmadd
 *
 * IF YOU ADD METHODS TO THIS CLASS YOU SHOULD OVERRIDE THEM IN DUMMYGAMEUPDATE
 * WITH EMPTY VERSIONS OF THEMSELVES
 *
 * NOTE I think there is a problem with the current update method.
 * Specifically, when a game update applies itself to the world,
 * the world's methods apply those changes to the game update.
 * This creates an infinite chain of updates.
 * A simple way to do this would be to add a boolean to every world-modifying
 * method which specifies whether the update is from gameplay or from
 * the network, and if it's from the network don't add it to the
 * game update object.
 *
 */
public class GameUpdate extends Update {
	private final long serialVersionUID = 748279847839274987L;

	private List<Structure> structuresAdded;
	private List<Structure> structuresRemoved;
	private List<Dude> dudesAdded;
	private List<Dude> dudesRemoved;

	private transient List<Dude> dudesMoved;
	//For now, I am going to assume that only one position change can take place
	// for each character each timer tick.  If this is not the case, we need to make
	//this a change-set of position changes
	//(or discard intermediate moves and have them jump)

	private transient List<Tile> colourChangedTiles; //We don't use the colour change method right now
	private transient Map<Dude, Integer> dudeHealthChanges;


	public GameUpdate() {
		structuresAdded = new ArrayList<Structure>();
		structuresRemoved = new ArrayList<Structure>();
		dudesAdded = new ArrayList<Dude>();
		dudesRemoved = new ArrayList<Dude>();
		dudeHealthChanges = new HashMap<Dude, Integer>();
		dudesMoved = new ArrayList<Dude>();
		colourChangedTiles = new ArrayList<Tile>();
	}

	/**
	 * Applies this update to the given world.
	 * @param world - world to apply the updates to.
	 */
	public void applyUpdates(World world){
		//Add all the new structures
		for (Structure s: structuresAdded){
			world.addStructure(s);
		}
		//Remove all deleted structures
		for(Structure s: structuresRemoved){
			world.removeStructure(s);
		}
		//Add all new dudes
		for(Dude d: dudesAdded){
			world.addDude(d);
		}
		//Remove all dead dudes
		for(Dude d: dudesRemoved){
			world.removeDude(d);
		}
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

	public void dudePositionChanged(Dude d){
		dudesMoved.add(d);
	}

	/**
	 * Called by World in the test where clicking on a tile changes its colour.
	 * @param t - the tile that was clicked on.
	 */
	public void changedTileColour(Tile t){
		colourChangedTiles.add(t);
	}

}
