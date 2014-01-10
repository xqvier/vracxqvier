package com.xqvier.encrypt.framework;

public interface Word {
	
	public void createWord(byte[] pWord);

	public int getLength();

	public Word getLeftPart();

	public Word getRightPart();

	public Word getCopy();

	public void doXOR(Word pWord);

	public Word concat(Word pWord);
	
	public void subtitute(byte[] pSubtituteTable);
	
	public byte[] getByteArray();

}
