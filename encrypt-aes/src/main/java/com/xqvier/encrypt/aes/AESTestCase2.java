package com.xqvier.encrypt.aes;

import java.util.LinkedList;
import java.util.List;

import com.xqvier.encrypt.aes.algorithm.AESEncrypt;
import com.xqvier.encrypt.aes.algorithm.AESKey;
import com.xqvier.encrypt.aes.algorithm.AESKeySchedule;
import com.xqvier.encrypt.framework.ByteMatriceWord;
import com.xqvier.encrypt.framework.Word;
import com.xqvier.encrypt.framework.util.ByteUtil;
import com.xqvier.encrypt.framework.util.WordFactory;

public class AESTestCase2 {
	private static final AESKey aesKey = new AESKey(ByteUtil.toBytes(0x2b,
			0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88,
			0x09, 0xcf, 0x4f, 0x3c));

	public static void main(String[] args) {

		String message = "Je vais encrypter ce message de la mort qui tue";

		AESEncrypt aesEncrypt = new AESEncrypt(10, new AESKeySchedule());
		List<Word> wordList = WordFactory.createWordList(message.getBytes(),
				ByteMatriceWord.class, 16);
		List<Word> cypherWordList = new LinkedList<Word>();
		for (Word word : wordList) {
			cypherWordList.add(aesEncrypt.encrypt(word, aesKey));
		}

		wordList.clear();

		for (Word word : cypherWordList) {
			wordList.add(aesEncrypt.decrypt(word, aesKey));
		}
		
		

		System.out.println(new String(WordFactory.createByteArray(wordList)));

	}
}
