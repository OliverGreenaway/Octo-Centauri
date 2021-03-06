package menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import networking.common.JoinGame;
import networking.common.Network;
import networking.common.ServerNotFoundException;
import networking.common.SocketBusyException;

public class JoinMenuPanel extends AbstractMenuPanel {

	public JoinMenuPanel(final MainFrame frame) {

		final JLabel error = new JLabel();

		this.addLabel(frame, "HostnameLabel");

		final JTextArea hostName = new JTextArea(1, 30);
		this.addComponent(hostName);

		this.addLabel(frame, "PortLabel");

		final JTextArea port = new JTextArea(1, 30);
		this.addComponent(port);

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = hostName.getText();
				int portNumber = 0;
				try {
					portNumber = Integer.parseInt(port.getText());
				} catch (NumberFormatException e2) {
					showError("Please enter a valid port number");
					return;
				}
				int minPort = 1024;
				int maxPort = (int) Math.pow(2, 16) - 1;
				if (portNumber <= minPort) {
					showError("Port number must excede " + minPort);
					return;
				}
				if (portNumber > maxPort) {
					showError("Port number must precede " + maxPort);
					return;
				}
				final Network n;
				try {
					n = new Network(name, portNumber);
				} catch (ServerNotFoundException e1) {
					showError("Server not found");
					return;
				} catch (SocketBusyException e1) {
					showError("Port is being used by another program");
					return;
				} catch (IOException e1) {
					showError("Connection failed, please try again");
					return;
				}
				showError("game joined");
				frame.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent evt) {
						try {
							n.close();
						} catch (IOException e) {
							showError("Connection failed, please try again");
							return;
						}
					}
				});
				JoinGame jg;
				try {
					jg = new JoinGame(n, false, frame);
				} catch (IOException e1) {
					showError("Connection failed, please try again");
					return;
				} catch (ClassNotFoundException e1) {
					showError("Connection failed, please try again");
					return;
				}
				frame.addMenu(jg.getWindow());
			}

			private void showError(String string) {
				error.setText(string);
			}

		};
		this.addButton(frame, listener, "JoinButton");

		listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.back();
			}
		};
		this.addButton(frame, listener, "BackButton");

		this.add(error);
	}
}
