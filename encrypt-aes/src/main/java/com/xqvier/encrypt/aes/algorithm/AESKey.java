package com.xqvier.encrypt.aes.algorithm;

import com.xqvier.encrypt.framework.ByteMatriceWord;
import com.xqvier.encrypt.framework.Key;
import com.xqvier.encrypt.framework.util.ByteUtil;

public class AESKey extends ByteMatriceWord implements Key {

	public AESKey(byte[] pWord) {
		super(pWord, true);
	}
	
	public AESKey(int pSize){
		this(ByteUtil.createEmptyByteWord(pSize));
	}

}
