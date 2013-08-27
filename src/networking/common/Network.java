package networking.common;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Network {

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket peer;

	public Network(String server, int port) throws ServerNotFoundException,
			SocketBusyException, IOException {

		if (server != null) {
			Socket output;
			try {
				output = new Socket(server, port);
			} catch (UnknownHostException e) {
				throw new ServerNotFoundException();
			} catch (IOException e) {
				throw new SocketBusyException();
			}
			System.out.println("server established");
			peer = output;
		}

		else {
			System.out.println("listening");
			ServerSocket listen;
			try {
				listen = new ServerSocket(port);
			} catch (IOException e) {
				throw new SocketBusyException();
			}
			Socket input;
			try {
				input = listen.accept();
			} catch (IOException e) {
				throw e;
			}
			System.out.println("connection established");
			peer = input;
		}

		out = new ObjectOutputStream(peer.getOutputStream());
		in = new ObjectInputStream(peer.getInputStream());

		/*
		 * out.writeObject("hi"); out.flush();
		 */
	}

	public void close() throws IOException {
		try {
			peer.close();
		} catch (IOException e) {
			throw e;
		}
	}

	public void sendUpdate(Update u) throws IOException {
		try {
			out.writeObject(u);
			out.flush();
		} catch (IOException e) {
			throw e;
		}
	}

	public Update getUpdate() throws IOException, ClassNotFoundException {
		try {
			return (Update) in.readObject();
		} catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}

	public static void main(String args[]) throws ServerNotFoundException,
			SocketBusyException, IOException, ClassNotFoundException {
		if (args.length == 0) {
			Network n = new Network("cafe-saint-paul", 10020);
			System.out.println(n.getUpdate());
		} else {
			Network n = new Network(null, 10020);
			n.sendUpdate(new ConcreteUpdate("hey"));
		}

	}

}

class ConcreteUpdate extends Update {
	String s;

	public ConcreteUpdate(String s) {
		this.s = s;
	}

	public String toString() {
		return s;
	}
}
