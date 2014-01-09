package com.xqvier.encrypt.des;

import com.xqvier.encrypt.des.algorithm.DESEncrypt;

public class DES {
	public static void main(String[] args) {
		String message = "toot va à la plage";
		
		
		byte[] key = null;
		byte[] word = null;

		DESEncrypt desEncrypt = new DESEncrypt(key);
		
		desEncrypt.encrypt(word);
		
		
	}
}
