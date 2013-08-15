package com.xqvier.antiplagiat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.xml.namespace.QName;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Feed;
import org.apache.abdera.protocol.Response.ResponseType;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class AntiPlagiat {
	private static String API_KEY = "AIzaSyD9BmsRas2py8--nRZL2L4-PiuT8645sJc";
	private static String cx = "014955674612467653838:zxmxcehdmn4";
	private static final Integer NB_MOT = 10;
	private static final Integer FUZZ_FACTOR = 100;

	private static int pos_next = 0;

	public static void main(String[] args) {
		JFileChooser fileChoose = new JFileChooser();
		fileChoose.setEnabled(true);

		fileChoose.setVisible(true);
		fileChoose.showOpenDialog(null);
		File file = fileChoose.getSelectedFile();
		XWPFDocument doc;
		try {
			doc = new XWPFDocument(new FileInputStream(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		List<String> stringList = new LinkedList<String>();
		for (XWPFParagraph paragraph : doc.getParagraphs()) {
			for (XWPFRun run : paragraph.getRuns()) {
				String text = run.getText(0);
				String result = searchMots(text, true);
				if (result != null) {
					if (googleSearch(result) >= FUZZ_FACTOR) {
						stringList.add(result);
					}
				}
				while ((result = searchMots(text, false)) != null) {
					if (googleSearch(result) >= FUZZ_FACTOR) {
						stringList.add(result);
					}
				}

			}
		}

		System.out.println("Voici les phrases qui ont plus de " + FUZZ_FACTOR
				+ " résultats dans google :");
		for (String toto : stringList) {
			System.out.println(toto);
		}
		Scanner sc = new Scanner(System.in);
		sc.next();
		sc.close();
	}

	private static Integer googleSearch(String pQuery) {
		Abdera abdera = new Abdera();
		AbderaClient abderaClient = new AbderaClient(abdera);
		ClientResponse cr = null;
		try {
			cr = abderaClient
					.get("https://www.googleapis.com/customsearch/v1?key="
							+ API_KEY
							+ "&cx="
							+ cx
							+ "&q="
							+ URLEncoder.encode("\"" + pQuery + "\"",
									"ISO-8859-15") + "&alt=atom");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		if (cr.getType() == ResponseType.SUCCESS) {
			Document<Feed> doc = cr.getDocument();

			Feed feed = doc.getRoot();
			return Integer.parseInt(feed.getFirstChild(
					new QName("http://a9.com/-/spec/opensearch/1.1/",
							"totalResults", "opensearch")).getText());
		}
		return 0;
	}

	private static String searchMots(String phrase, boolean reset) {
		if (phrase == null) {
			return null;
		}
		if (reset) {
			pos_next = 0;
		}
		int pos_start = pos_next;
		int j = 0;
		for (int i = pos_next; i < phrase.length(); i++) {
			if (j == 0 && Character.isWhitespace(phrase.charAt(i))) {
				pos_next = i + 1;
				j++;
			} else if (Character.isWhitespace(phrase.charAt(i))) {
				j++;

			}

			if (j == NB_MOT) {
				return phrase.substring(pos_start, i);
			}
		}
		return null;
	}
}
