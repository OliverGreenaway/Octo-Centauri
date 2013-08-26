package networking.common;

import java.awt.EventQueue;

public class ConnectionReadThread extends Thread {
	private Connection from ;

	public ConnectionReadThread(Connection from) {
		this.from = from;
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
					update.doUpdate();
				}
			});
		}
	}
}
