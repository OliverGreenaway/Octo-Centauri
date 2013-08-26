package networking.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Handles incoming connections.
 */
public class ConnectionListenThread extends Thread {

	private ServerSocket listeningSocket;
	private NetworkNode node;

	public ConnectionListenThread(ServerSocket listeningSocket, NetworkNode node) {
		this.listeningSocket = listeningSocket;
		this.node = node;
	}

	@Override
	public void run() {
		try {
			while(true) {
				Socket connected = listeningSocket.accept();

				Connection connection = new Connection(connected);

				// Tell the newly connected nodes about
				for(AddressAndPort aap : node.getConnectedAddresses())
					connection.sendUpdate(new NodeConnectionUpdate(aap.address, aap.port));

				node.addConnection(connection);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
