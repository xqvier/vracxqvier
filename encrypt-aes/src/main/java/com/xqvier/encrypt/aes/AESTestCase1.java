package com.xqvier.encrypt.aes;

import com.xqvier.encrypt.aes.algorithm.AESEncrypt;
import com.xqvier.encrypt.aes.algorithm.AESKey;
import com.xqvier.encrypt.aes.algorithm.AESKeySchedule;
import com.xqvier.encrypt.framework.ByteMatriceWord;
import com.xqvier.encrypt.framework.util.ByteUtil;

public class AESTestCase1 {

	 private static final ByteMatriceWord plainWord = new ByteMatriceWord(
	 ByteUtil.toBytes(0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d,
	 0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34), true);
	
	 private static final ByteMatriceWord expectedCypherWord = new
	 ByteMatriceWord(
	 ByteUtil.toBytes(0x39, 0x25, 0x84, 0x1d, 0x02, 0xdc, 0x09, 0xfb,
	 0xdc, 0x11, 0x85, 0x97, 0x19, 0x6a, 0x0b, 0x32), true);
	
	 private static final AESKey aesKey = new AESKey(ByteUtil.toBytes(0x2b,
	 0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88,
	 0x09, 0xcf, 0x4f, 0x3c));

//	private static final ByteMatriceWord plainWord = new ByteMatriceWord(
//			ByteUtil.toBytes(0x00, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77,
//					0x88, 0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff), true);
//
//	private static final ByteMatriceWord expectedCypherWord = new ByteMatriceWord(
//			ByteUtil.toBytes(0x69, 0xc4, 0xe0, 0xd8, 0x6a, 0x7b, 0x04, 0x30,
//					0xd8, 0xcd, 0xb7, 0x80, 0x70, 0xb4, 0xc5, 0x5a), true);
//
//	private static final AESKey aesKey = new AESKey(ByteUtil.toBytes(0x00,
//			0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b,
//			0x0c, 0x0d, 0x0e, 0x0f));

	public static void main(String[] args) {

		AESEncrypt aesEncrypt = new AESEncrypt(10, new AESKeySchedule());

		System.out.println("PLAIN WORD : ");
		System.out.println(plainWord);
		System.out.println("KEY : ");
		System.out.println(aesKey);

		ByteMatriceWord cypherWord = aesEncrypt.encrypt(plainWord, aesKey);

		System.out.println("CYPHER WORD : ");
		System.out.println(cypherWord);
		System.out.println("EXPECTED CYPHER WORD : ");
		System.out.println(expectedCypherWord);

		if (cypherWord.equals(expectedCypherWord)) {
			System.out.println("SEEMS PRETTY GOOD :)");
		} else {
			System.out.println("You're Wrong About It :(");
		}

		ByteMatriceWord decypherWord = aesEncrypt.decrypt(cypherWord, aesKey);
		System.out.println("DECYPHER WORD : ");
		System.out.println(decypherWord);
		System.out.println("EXPECTED DECYPHER WORD : ");
		System.out.println(plainWord);

		if (decypherWord.equals(plainWord)) {
			System.out.println("SEEMS PRETTY GOOD :)");
		} else {
			System.out.println("You're Wrong About It :(");
		}
	}
}
