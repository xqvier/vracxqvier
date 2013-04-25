package com.xqvier.test.util.network;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Nmap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InetAddress freeboxAdress = null;
		try {
			freeboxAdress = Inet4Address.getByAddress(new byte[] { (byte) 192,
					(byte) 168, (byte) 1, (byte) 1 });
			int freeboxPort = 1;
			TcpPort testPort;

			for (; freeboxPort < 10000; freeboxPort++) {
				testPort = new TcpPort();
				testPort.destPort = freeboxPort;
				testPort.freeboxAdress = freeboxAdress;
				testPort.start();
			}

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
	}

}
