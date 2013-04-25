package com.xqvier.test.util.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

public class Http {
	private static final String page = "";
	private static final String protocole = "http";
	private static final String port = "80";
	private static final String urlString = "192.168.1.1";

	public static void main(String[] args) {
		try {

			URL url = new URL(protocole + "://" + urlString + ":" + port + "/" + page);
			HttpURLConnection httpConnection = (HttpURLConnection) url
					.openConnection();

			
			InputStream is = httpConnection.getInputStream();

			int nbRead;
			byte[] data = new byte[16534];

			while ((nbRead = is.read(data)) != -1) {
				System.out.println(new String(data));
			}

		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
