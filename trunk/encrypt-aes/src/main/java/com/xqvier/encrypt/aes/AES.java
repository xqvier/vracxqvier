package com.xqvier.encrypt.aes;

import com.xqvier.encrypt.aes.algorithm.AESEncrypt;
import com.xqvier.encrypt.aes.algorithm.AESKey;
import com.xqvier.encrypt.aes.algorithm.AESKeySchedule;
import com.xqvier.encrypt.framework.ByteMatriceWord;
import com.xqvier.encrypt.framework.util.ByteUtil;

public class AES {

	private static final ByteMatriceWord plainWord = new ByteMatriceWord(
			ByteUtil.toBytes(0x32, 0x43, 0xf6, 0xa8, 0x88, 0x5a, 0x30, 0x8d,
					0x31, 0x31, 0x98, 0xa2, 0xe0, 0x37, 0x07, 0x34), true);

	private static final ByteMatriceWord expectedCypherWord = new ByteMatriceWord(
			ByteUtil.toBytes(0x39, 0x25, 0x84, 0x1d, 0x02, 0xdc, 0x09, 0xfb,
					0xdc, 0x11, 0x85, 0x97, 0x19, 0x6a, 0x0b, 0x32), true);

	private static final AESKey aesKey = new AESKey(ByteUtil.toBytes(0x2b,
			0x7e, 0x15, 0x16, 0x28, 0xae, 0xd2, 0xa6, 0xab, 0xf7, 0x15, 0x88,
			0x09, 0xcf, 0x4f, 0x3c));

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

	}
}
