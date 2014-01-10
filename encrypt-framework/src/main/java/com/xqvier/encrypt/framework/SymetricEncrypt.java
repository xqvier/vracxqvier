package com.xqvier.encrypt.framework;

public interface SymetricEncrypt {
	
	
	public Word encrypt(Word pWord, Key pKey);
	
	public Word decrypt(Word pWord, Key pKey);
	
}
