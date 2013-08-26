package networking.common;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Accepts connections from other nodes, and sends updates
 * to all of them.
 */
public class NetworkNode {
	private List<Connection> connections = new ArrayList<Connection>() ;
	private List<ConnectionReadThread> readThreads = new ArrayList<ConnectionReadThread>();
	private ConnectionListenThread listenThread;

	public NetworkNode(ServerSocket listeningSocket) {
		listenThread = new ConnectionListenThread(listeningSocket, this);
	}

	public void addConnection(Connection c) {
		connections.add(c) ;
		readThreads.add(new ConnectionReadThread(c)) ;
	}

	/**
	 * Sends an update to all other nodes in the game.
	 */
	public void sendUpdate(Update update) {
		for (Connection c : connections) {
			c.sendUpdate(update) ;
		}
	}
}
