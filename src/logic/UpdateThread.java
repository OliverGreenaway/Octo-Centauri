package logic;

import java.awt.EventQueue;
import java.io.IOException;

import networking.common.Network;
import networking.common.Update;
import state.World;
import UI.Display;

/**
 * A timer thread for periodic screen and game state updates.
 * At this point, created and started by the Window class.
 * @author markovmadd
 *
 */
public class UpdateThread extends Thread {

private final Display display;
private final World world;
private GameUpdate gUpdate;
private final Network network;
private final boolean multiplayer;

/**
 * Creates a new UpdateThread with references to the World and Display.
 * @param w - world to update.
 * @param d - display to update.
 * @param n - the network to send/receive updates over.  Null if single player.
 * @param initial - the starting game update.
 */
	public UpdateThread(World w, Display d, Network n, GameUpdate initial) {
    world = w;
    display = d;
    if(n == null){
    	multiplayer = false;
    	gUpdate = new DummyGameUpdate(); //This way, no memory is wasted on an unused update

    }
    else{
    	multiplayer = true;
    	gUpdate = initial;
    }
    w.setGameUpdate(gUpdate);
    network = n;
    System.out.println("New update thread created;");
	}

	private void networkUpdate(){
		//New updates may be generated in the world meanwhile we are waiting to get the new update from the network,
		//so go ahead and change the used game update object for a new one
		GameUpdate temp= new GameUpdate();
		world.setGameUpdate(temp);

		try{
		network.sendUpdate(gUpdate);
		}catch(IOException e){System.out.println("Error sending network update: "+e.getMessage());}
		//get the update back here
		Update incomingChanges = null;
		try {
			incomingChanges = network.getUpdate();
		} catch (ClassNotFoundException e) {
			System.out.println("Error getting update from network: "+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assert(incomingChanges != null);
		assert(incomingChanges instanceof GameUpdate); //This assertion should make the following cast safe
		GameUpdate update = (GameUpdate) incomingChanges;
		update.applyUpdates(world);
		gUpdate = new GameUpdate();
		world.setGameUpdate(gUpdate);
		System.out.println("Successfully completed an update cycle.");
	}

	/**
	 * Every 100 milliseconds, updates the display and the world state.
	 */
public void run(){
	while(true){
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				world.update();
			}
		});
		//Only bother with this network stuff if we're in a multiplayer game
		if(multiplayer)
			networkUpdate();
		display.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException i) {
			i.printStackTrace();
	}

}
}
}

