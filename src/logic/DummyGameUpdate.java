package logic;

import state.Dude;
import state.Structure;
import state.Tile;
import state.World;

/** The idea of this class is to save resources and time when running a single-player game.
 * The world always expects to have a GameUpdate object to send updates to whether running
 * in single-player or multiplayer mode.  However, in single-player GameUpdates are never used.
 * So this is a class that can be given to the world and which has ALL the methods of a GameUpdate,
 * except they do nothing.
 * IF YOU ADD METHODS TO GAMEUPDATE YOU SHOULD OVERRIDE THEM HERE so that you don't defeat the
 * purpose of this class.
 * @author markovmadd
 *
 */
public class DummyGameUpdate extends GameUpdate {

	public DummyGameUpdate() {

	}
	public void applyUpdates(World world){

	}

	/**
	 * Called by World? whenever a structure is added
	 * @param s - the structure that was added.
	 */
	public void structureAdded(Structure s){

	}

	/**
	 * Called by World whenever a structure is removed
	 * @param s - the structure that was removed.
	 */
	public void structureRemoved(Structure s){

	}

	/**
	 * Called by World whenever a Dude is added
	 * @param d - the Dude that was added
	 */
	public void dudeAdded(Dude d){


	}

	/**
	 * Called by World whenever a dude is removed.
	 * @param d - the dude that was removed.
	 */
	public void dudeRemoved(Dude d){

	}

	public void dudePositionChanged(Dude d){

	}

	/**
	 * Called by World in the test where clicking on a tile changes its colour.
	 * @param t - the tile that was clicked on.
	 */
	public void changedTileColour(Tile t){

	}


}
