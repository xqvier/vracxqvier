package com.xqvier.encrypt.framework;

import java.util.Arrays;

public abstract class FeistelNetwork extends SymetricEncrypt {

	private int round;

	public FeistelNetwork(byte[] pKey, int pRound) {
		super(pKey);
		this.round = pRound;
	}

	public byte[] encrypt(byte[] pWord) {
		int partLength = pWord.length / 2;
		byte[] leftPart = new byte[partLength];
		leftPart = Arrays.copyOfRange(pWord, 0, partLength);
		byte[] rightPart = new byte[partLength];
		rightPart = Arrays.copyOfRange(pWord, partLength, pWord.length);
		byte[] memorize = new byte[partLength];
		for (int i = 0; i < round; i++) {
			memorize = Arrays.copyOf(rightPart, partLength);
			rightPart = encryptFunction(keySchedule(key, i + 1), rightPart);
			for (int j = 0; j < partLength; j++) {
				rightPart[j] = (byte) (leftPart[j] ^ rightPart[j]);
			}
			leftPart = Arrays.copyOf(memorize, partLength);

		}
		memorize = Arrays.copyOf(leftPart, partLength);
		leftPart = Arrays.copyOf(rightPart, partLength);
		rightPart = Arrays.copyOf(memorize, partLength);

		System.arraycopy(leftPart, 0, memorize, 0, partLength);
		System.arraycopy(rightPart, 0, memorize, partLength, pWord.length);

		return memorize;

	}

	protected abstract byte[] encryptFunction(byte[] pKey, byte[] pWord);

}
