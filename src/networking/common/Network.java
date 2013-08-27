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
	/**
	 *	Either connect to another host or set up a server
	 *
	 * @param server Name of host to connect to - null if this is the game host
	 * @param port
	 * @throws ServerNotFoundException
	 * @throws SocketBusyException
	 * @throws IOException
	 */
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
	/**
	 * Closes the connection. Must always be called before program terminates
	 * @throws IOException
	 */
	public void close() throws IOException {
		try {
			peer.close();
		} catch (IOException e) {
			throw e;
		}
	}
	/**
	 * Send update to peer
	 * @param u
	 * @throws IOException
	 */
	public void sendUpdate(Update u) throws IOException {
		try {
			out.writeObject(u);
			out.flush();
		} catch (IOException e) {
			throw e;
		}
	}
	/**
	 * Wait for and receive an update from peer
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
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
/**
 * Used for testing purposes
 * @author muruphenr
 *
 */
class ConcreteUpdate extends Update {
	String s;

	public ConcreteUpdate(String s) {
		this.s = s;
	}

	public String toString() {
		return s;
	}
}
