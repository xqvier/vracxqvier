package com.xqvier.encrypt.des.algorithm;

import com.xqvier.encrypt.framework.FeistelNetwork;


public class DESEncrypt extends FeistelNetwork {

	private final static int ROUND = 16;
	
	private final static int WORD_AND_KEY_LENGTH = 48;
	
	private final static int KEY_LENGTH = 56;
	
	public DESEncrypt(byte[] pKey) {
		super(pKey, ROUND);
	}


	@Override
	protected byte[] keySchedule(byte[] pKey, int pRound) {
		if(pKey.length != KEY_LENGTH ){
			throw new RuntimeException("Wrong key length : " + pKey.length + " - must be : " + KEY_LENGTH);
		}
		if(pRound > ROUND){
			throw new RuntimeException("Wrong round number : " + pRound + " - must be <= " + ROUND);
		}
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected byte[] encryptFunction(byte[] pKey, byte[] pWord) {
		
		if(pKey.length != WORD_AND_KEY_LENGTH ){
			throw new RuntimeException("Wrong key length : " + pKey.length + " - must be : " + WORD_AND_KEY_LENGTH);
		}
		if(pWord.length != WORD_AND_KEY_LENGTH ){
			throw new RuntimeException("Wrong word length : " + pWord.length + " - must be : " + WORD_AND_KEY_LENGTH);
		}
		
		pWord = pc1(pWord);
		for(int i = 0 ; i < pWord.length; i++){
			pWord[i] = (byte) (pWord[i] ^ pKey[i]);
		}
		
		pWord = sbox(pWord);
		pWord = pc2(pWord);
		
		return pWord;
	}


	private byte[] pc2(byte[] pWord) {
		// TODO Auto-generated method stub
		return null;
	}


	private byte[] sbox(byte[] pWord) {
		// TODO Auto-generated method stub
		return null;
	}


	private byte[] pc1(byte[] pWord) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
