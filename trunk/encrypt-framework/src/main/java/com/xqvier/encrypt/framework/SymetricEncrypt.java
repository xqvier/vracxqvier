package com.xqvier.encrypt.framework;

public abstract class SymetricEncrypt{
	protected byte[] key;
	
	public SymetricEncrypt(byte[] pKey) {
		key = pKey;
	}
	
	
	public abstract byte[] encrypt(byte[] pWord);
	

	protected abstract byte[] keySchedule(byte[] pKey, int pRound);
	
}
