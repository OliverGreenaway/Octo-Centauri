package networking.common;

import java.awt.EventQueue;

/**
 * Handles incoming updates on a connection.
 */
public class ConnectionReadThread extends Thread {
	private Connection from ;
	private NetworkNode node ;

	public ConnectionReadThread(Connection from, NetworkNode node) {
		this.from = from;
		this.node = node;
	}
	/**
	 * Reads an update from the connection, then runs it, then repeats until disconnected.
	 */
	@Override
	public void run() {
		while(true) {
			final Update update = from.getUpdate();
			if(update == null) {
				// disconnected
				return;
			}

			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					update.doUpdate(node);
				}
			});
		}
	}
}
