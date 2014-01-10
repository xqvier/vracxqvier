package com.xqvier.encrypt.aes.algorithm;

import java.util.LinkedList;
import java.util.List;

import com.xqvier.encrypt.framework.ByteMatriceWord;
import com.xqvier.encrypt.framework.Key;
import com.xqvier.encrypt.framework.KeySchedule;
import com.xqvier.encrypt.framework.util.ByteUtil;

public class AESKeySchedule implements KeySchedule {

	private List<AESKey> keyList = new LinkedList<AESKey>();

	private static final ByteMatriceWord RCON_MATRICE_TABLE = new ByteMatriceWord(
			ByteUtil.toBytes(0x01, 0x00, 0x00, 0x00, 0x02, 0x00, 0x00, 0x00,
					0x04, 0x00, 0x00, 0x00, 0x08, 0x00, 0x00, 0x00, 0x10, 0x00,
					0x00, 0x00, 0x20, 0x00, 0x00, 0x00, 0x40, 0x00, 0x00, 0x00,
					0x80, 0x00, 0x00, 0x00, 0x1b, 0x00, 0x00, 0x00, 0x36, 0x00,
					0x00, 0x00), 4, 10, true);

	public void initializeKey(Key pKey, int pNbRound) {
		keyList = new LinkedList<AESKey>();
		if (!(pKey instanceof AESKey)) {
			throw new RuntimeException(
					"The AESKeySchedule did not receive a proper AESKey");
		}
		AESKey previousKey = (AESKey) pKey;

		for (int k = 0; k < pNbRound; k++) {

			AESKey newKey = new AESKey(previousKey.getLength());
			// first column
			newKey.copyColumn(0, previousKey, 3);
			newKey.shiftColumn(0, 1, true);
			newKey.subtituteColumn(0, AESEncrypt.SUB_BYTES_TABLE);
			newKey.doColumnXOR(0, previousKey, 0);
			newKey.doColumnXOR(0, RCON_MATRICE_TABLE, 0);
			// others column (easy one)
			for (int i = 1; i < newKey.getColumnCount(); i++) {
				newKey.doColumnXOR(i, previousKey, i);
			}			
			keyList.add(newKey);
		}
	}

	public Key getRoundKey(int pRound) {
		return keyList.get(pRound);
	}

}
