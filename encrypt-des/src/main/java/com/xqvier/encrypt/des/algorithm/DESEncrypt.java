package com.xqvier.encrypt.des.algorithm;

import com.xqvier.encrypt.framework.ByteArrayWord;
import com.xqvier.encrypt.framework.FeistelNetwork;
import com.xqvier.encrypt.framework.Key;
import com.xqvier.encrypt.framework.KeySchedule;
import com.xqvier.encrypt.framework.Word;

public class DESEncrypt extends FeistelNetwork {

	public DESEncrypt(int pNbRound, KeySchedule pKeySchedule) {
		super(pKeySchedule, pNbRound);
		if (pNbRound < 1) {
			throw new RuntimeException(
					"Can't create a AES encryption with less than 1 round... (chosen : "
							+ pNbRound + ")");
		}
		if (!(pKeySchedule instanceof DESKeySchedule)) {
			System.out
					.println("Warning you did not chose a DESKeySchedule for the AES Encrypt."
							+ " It will be converted but it may cause subsequent problem");
			pKeySchedule = new DESKeySchedule();
		}
	}

	@Override
	protected Word encryptFunction(Key pKey, Word pWord) {
		if (!(pWord instanceof ByteArrayWord)) {
			System.out
					.println("Warning you did not chose a ByteMatriceWord for the AES Encrypt."
							+ " It will be converted but it may cause subsequent problem");

			pWord = new ByteArrayWord(pWord.getByteArray());
		}

		ByteArrayWord word = (ByteArrayWord) pWord;

		if (!(pKey instanceof DESKey)) {
			System.out
					.println("Warning you did not chose a DESKey for the DES Encrypt."
							+ " It will be converted but it may cause subsequent problem");

			pWord = new DESKey(pKey.getByteArray());

		}
		DESKey desKey = (DESKey) pKey;
		if (desKey.getLength() != word.getLength()) {
			throw new RuntimeException("Error : key length ("
					+ desKey.getLength() + ") and word length ("
					+ word.getLength() + ") should not be different");
		}

		pWord = pc1(pWord);
		pWord.doXOR(desKey);

		pWord = sbox(pWord);
		pWord = pc2(pWord);

		return pWord;
	}

	private Word pc2(Word pWord) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	private Word sbox(Word pWord) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	private Word pc1(Word pWord) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

}
