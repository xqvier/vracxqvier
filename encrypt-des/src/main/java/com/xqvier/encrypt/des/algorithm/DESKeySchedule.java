package com.xqvier.encrypt.des.algorithm;

import java.util.LinkedList;
import java.util.List;

import com.xqvier.encrypt.framework.Key;
import com.xqvier.encrypt.framework.KeySchedule;

public class DESKeySchedule implements KeySchedule {

	List<Key> keyList = new LinkedList<Key>();

	public void initializeKey(Key key, int pNbRound) {
		keyList = new LinkedList<Key>();
		for (int i = 0; i < pNbRound; i++) {
			// FIXME
			keyList.add(key);
		}

	}

	public Key getRoundKey(int pRound) {
		return keyList.get(pRound);
	}

	public void initializeReverseKey(Key key, int pNbRound) {
		// TODO Auto-generated method stub
		
	}

}
