package com.xqvier.encrypt.framework.util;

public class ByteUtil {
	public static byte[] toBytes(int... ints) {
		byte[] result = new byte[ints.length];
		for (int i = 0 ; i < ints.length ; i++) {
			result[i] = (byte) ints[i];
		}
		return result;
	}

	public static byte[] createEmptyByteWord(int pSize) {
		byte[] array = new byte[pSize];
		for (int i = 0; i < pSize; i++) {
			array[i] = 0x00;
		}
		return array;
	}

	public static byte[] reverseSubstitutionTable(byte[] subBytesTable) {
		byte[] result = new byte[subBytesTable.length];
		for(int i = 0; i < subBytesTable.length; i++){
			result[subBytesTable[i] & 0xff] = (byte) i;
		}
		
		return result;
	}
}
