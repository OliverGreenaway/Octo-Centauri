package networking.common;

import java.io.IOException;

import javax.swing.JPanel;

import UI.Window;

public class JoinGame {

	private JPanel window;
	
	public JoinGame(Network n, boolean isServer) throws IOException, ClassNotFoundException {
		long seed = System.currentTimeMillis();
		if (isServer) {
			n.sendUpdate(new WorldSeedUpdate(seed));
		} else {
			((WorldSeedUpdate)n.getUpdate()).getSeed();
		}
		
		window = new Window();
	}
	
	public JPanel getWindow() {
		return window;
	}

}
