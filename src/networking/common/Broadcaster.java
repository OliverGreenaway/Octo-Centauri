package networking.common;

import java.util.List;

public class Broadcaster {
	private List<Connection> connections ;

	public Broadcaster(List<Connection> connections) {
		this.connections = connections ;
	}

	public void addConnection(Connection c) {
		connections.add(c) ;
	}
	/**
	 * Send update to all connections
	 * @param update
	 */
	public void broadcast(Update update) {
		for (Connection c : connections) {
			c.sendUpdate(update) ;
		}
	}
}
