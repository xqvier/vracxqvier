package com.xqvier.encrypt.framework;

import java.util.Arrays;

public class ByteArrayWord implements Word {

	private byte[] word;

	@Deprecated
	public ByteArrayWord() {
	}

	public ByteArrayWord(byte[] pWord) {
		word = pWord;
	}

	@Override
	public void createWord(byte[] pWord) {
		word = pWord;

	}

	@Override
	public int getLength() {
		return word.length;
	}

	@Override
	public Word getLeftPart() {
		return new ByteArrayWord(Arrays.copyOfRange(word, 0, word.length / 2));
	}

	@Override
	public Word getRightPart() {
		return new ByteArrayWord(Arrays.copyOfRange(word, word.length / 2,
				word.length - 1));
	}

	@Override
	public Word getCopy() {
		return new ByteArrayWord(Arrays.copyOfRange(word, 0, word.length - 1));
	}

	@Override
	public void doXOR(Word pWord) {
		byte[] wordToXOR = pWord.getByteArray();
		for (int i = 0; i < word.length; i++) {
			word[i] = (byte) (word[i] ^ wordToXOR[i]);
		}
	}

	@Override
	public Word concat(Word pWord) {
		byte[] result = Arrays.copyOf(word, word.length + pWord.getLength());
		byte[] secondWord = pWord.getByteArray();
		for (int i = word.length; i < result.length; i++) {
			result[i] = secondWord[i - word.length];
		}
		return new ByteArrayWord(result);
	}

	@Override
	public void subtitute(byte[] pSubtituteTable) {
		for (int i = 0; i < word.length; i++) {
			word[i] = pSubtituteTable[word[i] & 0xff];
		}
	}

	@Override
	public byte[] getByteArray() {
		return Arrays.copyOf(word, word.length);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < word.length; i++) {
			sb.append("[" + String.format("%02x", word[i]) + "]");
		}
		return sb.toString();
	}

}
