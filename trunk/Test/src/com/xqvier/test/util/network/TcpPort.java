package com.xqvier.test.util.network;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TcpPort extends Thread {
	public int destPort;
	public InetAddress freeboxAdress;

	@Override
	public void run() {
		try {
			boolean ok = true;
			Socket dsDest;
			try {
				dsDest = new Socket(freeboxAdress, destPort);
			} catch (ConnectException e) {
				ok = false;
			} catch (SocketException e) {
				System.out.println("port " + destPort + " denied");
			}
			if (ok) {
				System.out.println("port " + destPort + " is reachable");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
