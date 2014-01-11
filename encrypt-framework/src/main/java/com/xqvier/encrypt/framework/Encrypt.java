package com.xqvier.encrypt.framework;

public interface Encrypt {
	
	
	public Word encrypt(Word pWord, Key pKey);
	
	public Word decrypt(Word pWord, Key pKey);
	
}
