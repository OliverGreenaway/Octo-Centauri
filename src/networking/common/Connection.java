package networking.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;


public class Connection {
	private Socket client ;

	public Connection(Socket client) {
		this.client = client;
	}

	/**
	 * Send an update through the client socket. If anything goes wrong,
	 * you won't know. You must update the local machine with this update
	 * once the update is sent!
	 * @param update
	 */
	public synchronized void sendUpdate(Update update) {
		OutputStream o;
		try {
			o = client.getOutputStream();
			ObjectOutputStream oo = new ObjectOutputStream(o) ;
			oo.writeObject(update) ;
		} catch (IOException e) {
			// TODO A proper error handling procedure needs to be in place!
			e.printStackTrace();
		}
	}
	/**
	 * getUpdate will block while waiting for an Update object to be written to the
	 * input stream. If anything goes wrong, it catches the exception and will return null.
	 * @return
	 */
	public synchronized Update getUpdate() {
		InputStream i;
		Update update = null ;
		try {
			i = client.getInputStream();
			ObjectInputStream oi = new ObjectInputStream(i) ;
			update = (Update) oi.readObject() ;

		} catch (IOException e) {
			// TODO A proper error handling procedure needs to be in place!
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace() ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return update ;
	}
}
