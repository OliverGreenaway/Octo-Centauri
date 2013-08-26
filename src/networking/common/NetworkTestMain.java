package networking.common;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * For testing, creates three nodes on different ports.
 * Then connects node 2 to node 1, and node 3 to node 2.
 * All three nodes should be mutually connected.
 */
public class NetworkTestMain {
	private static final int PORT1 = 1234;
	private static final int PORT2 = 1235;
	private static final int PORT3 = 1236;

	public static void main(String[] args) throws IOException {
		NetworkNode node1 = new NetworkNode(new ServerSocket(PORT1));
		NetworkNode node2 = new NetworkNode(new ServerSocket(PORT2));
		NetworkNode node3 = new NetworkNode(new ServerSocket(PORT3));

		node2.connectTo(InetAddress.getByName("localhost"), PORT1);
		node3.connectTo(InetAddress.getByName("localhost"), PORT2);
	}
}
