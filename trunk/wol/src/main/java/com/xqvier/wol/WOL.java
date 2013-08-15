package com.xqvier.wol;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WOL {
	private static final String hostName = "rodez.xqvier.com";

	private static final byte[] pcmacAddress = new byte[] { (byte) 0x90,
			(byte) 0x2b, (byte) 0x34, (byte) 0x37, (byte) 0x8b, (byte) 0xb5 };

	private static final byte[] UNIXmacAddress = new byte[] { (byte) 0x00,
			(byte) 0x11, (byte) 0xd8, (byte) 0x17, (byte) 0xb4, (byte) 0x13 };

	private static final Integer portNumber = 9;

	private static final Integer MAGIC_PACKET_SIZE = 102;

	public static void main(String[] args) {
		InetAddress inetAddress;
		try {
			inetAddress = Inet6Address.getAllByName(hostName)[0];
			System.out.println(inetAddress);
			DatagramSocket socket = new DatagramSocket();
			byte[] buffer = new byte[MAGIC_PACKET_SIZE];
			int i = 0;
			// On commence par FF:FF:FF:FF:FF:FF
			for (i = 0; i < 6; i++) {
				buffer[i] = (byte) 0xff;
			}
			// puis 16 fois l'adresse MAC de l'ordinateur à réveiller.
			for (; i < 16 * 6 + 6; i++) {
				buffer[i] = UNIXmacAddress[i % 6];
			}

			DatagramPacket datagramPacket = new DatagramPacket(buffer,
					MAGIC_PACKET_SIZE, inetAddress, portNumber);

			socket.send(datagramPacket);

			socket.close();

		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
