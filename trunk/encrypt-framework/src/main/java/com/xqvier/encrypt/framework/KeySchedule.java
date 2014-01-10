package com.xqvier.encrypt.framework;

public interface KeySchedule {
	public void initializeKey(Key key, int pNbRound);
	
	public Key getRoundKey(int pRound);
}
