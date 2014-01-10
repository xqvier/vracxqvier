package com.xqvier.encrypt.des;

import java.util.LinkedList;
import java.util.List;

import com.xqvier.encrypt.des.algorithm.DESEncrypt;
import com.xqvier.encrypt.des.algorithm.DESKey;
import com.xqvier.encrypt.des.algorithm.DESKeySchedule;
import com.xqvier.encrypt.framework.ByteArrayWord;
import com.xqvier.encrypt.framework.Word;
import com.xqvier.encrypt.framework.util.WordFactory;

public class DES {
	public static void main(String[] args) {
		String message = "toot va à la plage et il se fait chier alors il y va plus c'est relou";
		
		String keyText = "mon mot de passe trop secret";
		if((keyText.length() % 2 )!= 0){
			keyText += " ";
		}
		
		DESKey desKey = new DESKey(keyText.getBytes());
		
		DESEncrypt desEncrypt = new DESEncrypt(16, new DESKeySchedule());
		List<Word> wordList = WordFactory.createWordList(message.getBytes(), ByteArrayWord.class, keyText.length());
		List<Word> cypherWordList = new LinkedList<Word>();
		for(Word word : wordList){
		   desEncrypt.encrypt(word, desKey);
		}
		
		
		System.out.println(new String(WordFactory.createByteArray(cypherWordList)));
		
		
	}
}
