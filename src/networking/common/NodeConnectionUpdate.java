package networking.common;

import java.io.IOException;
import java.net.InetAddress;

/**
 * When node A connects to node B, this update is sent by B to A to tell it about B's
 * other connections. A then connects to these addresses.
 */
public class NodeConnectionUpdate extends Update {

	public NodeConnectionUpdate(InetAddress a, int p) {
		this.address = a;
		this.port = p;
	}

	private InetAddress address;
	private int port;

	@Override
	public void doUpdate(NetworkNode node) {
		try {
			node.connectTo(address, port);
		} catch(IOException e) {
			// TODO figure out what to do with connection errors
			throw new RuntimeException("Failed to connect!", e);
		}
	}
}
