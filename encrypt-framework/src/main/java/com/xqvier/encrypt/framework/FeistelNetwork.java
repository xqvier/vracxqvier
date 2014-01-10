package com.xqvier.encrypt.framework;


public abstract class FeistelNetwork implements SymetricEncrypt {

	private KeySchedule keySchedule;

	private int round;

	public FeistelNetwork(KeySchedule pKeySchedule, int pRound) {

		keySchedule = pKeySchedule;
		this.round = pRound;
	}

	public Word encrypt(Word pWord, Key pKey) {
		keySchedule.initializeKey(pKey, round);
		
		
		int partLength = pWord.getLength() / 2;
		Word leftPart = pWord.getLeftPart();
		Word rightPart = pWord.getRightPart();
		Word memorize;
		for (int i = 0; i < round; i++) {
			memorize = rightPart.getCopy();
			rightPart = encryptFunction(keySchedule.getRoundKey(i), rightPart);
			for (int j = 0; j < partLength; j++) {
				rightPart.doXOR(leftPart);
			}
			leftPart = memorize.getCopy();

		}
		memorize = leftPart.getCopy();
		leftPart = rightPart.getCopy();
		rightPart = memorize.getCopy();


		return leftPart.concat(rightPart);

	}
	
	@Override
	public Word decrypt(Word pWord, Key pKey) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	protected abstract Word encryptFunction(Key pKey, Word pWord);

}
