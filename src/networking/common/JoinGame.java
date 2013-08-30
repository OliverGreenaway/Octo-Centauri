package networking.common;

import java.io.IOException;

import javax.swing.JPanel;

import menu.MainFrame;

import UI.Window;

/**
 *
 * @author muruphenr
 *
 */
public class JoinGame {
	/**
 *
 */
	private JPanel window;

	/**
	 * When JoinGame is instantiated, it is either a server or a client. When
	 * isServer is true, a single FilePathUpdate is sent with the chosen
	 * location of the map for both client and server - then it is sent.
	 *
	 * When client, it waits for a FilePathUpdate.
	 *
	 * Then a new Window is created. It can then be accessed through the
	 * getWindow() method.
	 *
	 * @param n
	 *            valid network connection, must be joined
	 * @param isServer
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public JoinGame(Network n, boolean isServer, MainFrame frame)
			throws IOException, ClassNotFoundException {
		long seed = System.currentTimeMillis();
		String file = "resources/map";

		if (isServer) {
			n.sendUpdate(new FilePathUpdate(file));
		} else {
			((FilePathUpdate) n.getUpdate()).getPath();
		}

		window = new Window(seed, n, file, null);

	}

	public JPanel getWindow() {
		return window;
	}

}
