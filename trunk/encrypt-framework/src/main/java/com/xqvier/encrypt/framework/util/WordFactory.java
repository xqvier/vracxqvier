package com.xqvier.encrypt.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.xqvier.encrypt.framework.ByteArrayWord;
import com.xqvier.encrypt.framework.Word;

public class WordFactory {
	public static List<Word> createWordList(byte[] pWord,
			Class<? extends Word> pClazz, int pSize) {
		List<Word> wordList = new LinkedList<Word>();
		Word word;
		try {
			int i = 0;
			for (; i < (pWord.length / pSize); i++) {

				word = pClazz.newInstance();
				word.createWord(Arrays.copyOfRange(pWord, i * pSize, i * pSize
						+ pSize));
				wordList.add(word);
			}
			word = pClazz.newInstance();
			pWord = Arrays.copyOfRange(pWord, i * pSize, pWord.length);
			pWord = rightPadding(pWord, pSize);
			word.createWord(pWord);
			wordList.add(word);

		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return wordList;
	}

	public static byte[] createByteArray(List<Word> pWordList) {
		int i = 0;
		Word result = new ByteArrayWord(new byte[0]);
		for (; i < pWordList.size(); i++) {
			result = result.concat(pWordList.get(i));
		}

		return removePadding(result.getByteArray());

	}

	public static List<Word> createWordList(File pFile,
			Class<? extends Word> pClazz, int pSize) {

		List<Word> wordList = new LinkedList<Word>();
		Word word;
		byte[] buf = new byte[pSize];
		int nbRead;
		try {
			FileInputStream fis = new FileInputStream(pFile);
			while ((nbRead = fis.read(buf)) != -1) {
				if (nbRead < pSize) {
					buf = rightPadding(buf, nbRead, pSize);
				}
				word = pClazz.newInstance();
				word.createWord(buf);
				wordList.add(word);

			}
			fis.close();
		} catch (IOException e) {
			throw new RuntimeException();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}

		return wordList;
	}

	public static void createFile(File pFile, List<Word> pWordList) {
		try {
			pFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(pFile);
			int i = 0;
			for (; i < pWordList.size() - 1; i++) {
				fos.write(pWordList.get(i).getByteArray());
			}
			fos.write(removePadding(pWordList.get(i).getByteArray()));
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static byte[] removePadding(byte[] pWord) {
		return Arrays.copyOfRange(pWord, 0, pWord.length - (pWord[pWord.length - 1] & 0xff));
	}

	private static byte[] rightPadding(byte[] pWord, int pLength) {
		byte[] result = new byte[pLength];

		result = Arrays.copyOfRange(pWord, 0, pLength);

		for (int i = pWord.length; i < pLength; i++) {
			result[i] = (byte) (pLength - pWord.length);
		}

		return result;
	}

	private static byte[] rightPadding(byte[] buf, int nbRead, int pSize) {
		return rightPadding(Arrays.copyOfRange(buf, 0, nbRead), pSize);
	}

}
