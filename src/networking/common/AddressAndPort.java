package networking.common;

import java.net.InetAddress;

public class AddressAndPort {
	final InetAddress address;
	final int port;

	public AddressAndPort(InetAddress address, int port) {
		this.address = address;
		this.port = port;
	}

	@Override
	public int hashCode() {
		return address.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AddressAndPort))
			return false;
		AddressAndPort o = (AddressAndPort)obj;
		return address.equals(o.address) && port == o.port;
	}
}