package com.xqvier.encrypt.framework;

import java.util.Arrays;

public class ByteArrayWord implements Word {

	private byte[] word;

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
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public Word concat(Word pWord) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public void subtitute(byte[] pSubtituteTable) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	@Override
	public byte[] getByteArray() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
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
