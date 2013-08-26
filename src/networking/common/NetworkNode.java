package networking.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accepts connections from other nodes, and sends updates
 * to all of them.
 */
public class NetworkNode {

	private class ConnectionInfo {
		Connection connection;
		ConnectionReadThread readThread;
	}

	private Map<AddressAndPort, ConnectionInfo> connections = new HashMap<AddressAndPort, ConnectionInfo>();

	private ConnectionListenThread listenThread;

	public NetworkNode(ServerSocket listeningSocket) {
		listenThread = new ConnectionListenThread(listeningSocket, this);
		listenThread.start();
	}

	public void addConnection(Connection c) {
		ConnectionInfo ci = new ConnectionInfo();
		ci.connection = c;
		ci.readThread = new ConnectionReadThread(c, this);
		ci.readThread.start();

		// TODO THIS IS WRONG.
		// TODO Instead of using the address we are actually connected to, it needs to use
		// TODO the other node's listeningSocket address.
		AddressAndPort where = new AddressAndPort(c.getAddress(), c.getPort());

		connections.put(where, ci);

		System.out.println("Now connected to "+where.address+":"+where.port);
	}

	/**
	 * Sends an update to all other nodes in the game.
	 */
	public void sendUpdate(Update update) {
		for (ConnectionInfo ci : connections.values()) {
			ci.connection.sendUpdate(update) ;
		}
	}

	/**
	 * Connects to another node.
	 * @param address The node's address.
	 * @param port The node's port.
	 */
	public void connectTo(InetAddress address, int port) throws IOException {
		if(connections.containsKey(new AddressAndPort(address, port))) {
			System.out.println("Already connected to "+address+":"+port);
			return;
		}

		System.out.println("Connecting to "+address+":"+port);

		Socket socket = new Socket(address, port);
		addConnection(new Connection(socket));
	}

	public Collection<AddressAndPort> getConnectedAddresses() {
		return connections.keySet();
	}
}
