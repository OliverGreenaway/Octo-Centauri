package logic;

import networking.common.Network;
import UI.Display;

import state.World;

/**
 * A timer thread for periodic screen and game state updates.
 * At this point, created and started by the Window class.
 * @author markovmadd
 *
 */
public class UpdateThread extends Thread {

private final Display display;
private final World world;

/**
 * Creates a new UpdateThread with references to the World and Display.
 * @param w - world to update.
 * @param d - display to update.
 */
	public UpdateThread(World w, Display d) {
    world = w;
    display = d;
	}

	/**
	 * Every 100 milliseconds, updates the display and the world state.
	 */
public void run(){
	while(1 == 1){
		world.update();
		GameUpdate gUpdate = new GameUpdate();
		//Network.sendUpdate(gUpdate);
		//get the update back here
		display.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
	}

}
}
}
