package networking.common;

import game.GameState;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connection {
	Socket client ;
	GameState state ;

	public Connection(Socket client , GameState state) {
		this.state = state ;
		this.client = client;
	}

	public void sendUpdate(Update update) {
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
	//public Update
}
