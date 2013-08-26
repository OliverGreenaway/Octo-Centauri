package networking.common;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
				node.addConnection(new Connection(connected));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
