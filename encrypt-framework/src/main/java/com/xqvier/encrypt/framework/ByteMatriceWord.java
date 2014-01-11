package com.xqvier.encrypt.framework;

public class ByteMatriceWord implements Word {

	private byte[][] word;

	private int rowCount = 0;

	private int columnCount = 0;

	private boolean pageAxis = true;

	public ByteMatriceWord(byte[] pWord, int pRowCount, int pColumnCount,
			boolean pPageAxis) {
		rowCount = pRowCount;
		columnCount = pColumnCount;

		createWord(pWord, pPageAxis);

	}

	public ByteMatriceWord(byte[] pWord, int pSize, boolean pPageAxis) {
		this(pWord, pSize, pSize, pPageAxis);
	}

	public ByteMatriceWord(byte[] pWord, boolean pPageAxis) {
		this(pWord, (int) Math.sqrt(pWord.length), pPageAxis);
	}

	public void createWord(byte[] pWord) {
		createWord(pWord, false);
	}

	public int getLength() {
		return rowCount * columnCount;
	}

	public int getRowCount() {
		return word.length;
	}

	public int getColumnCount() {
		return word[0].length;
	}

	public Word getLeftPart() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	public Word getRightPart() {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	public Word getCopy() {
		return new ByteMatriceWord(getByteArray(), rowCount, columnCount,
				pageAxis);
	}

	public void doXOR(Word pWord) {
		for (int i = 0; i < columnCount; i++) {
			doColumnXOR(i, (ByteMatriceWord) pWord, i);
		}
	}

	public void doColumnXOR(int pOriginalColumnIndex,
			ByteMatriceWord pByteMatriceWord, int pColumIndex) {
		for (int i = 0; i < rowCount; i++) {
			word[i][pOriginalColumnIndex] = (byte) (word[i][pOriginalColumnIndex] ^ pByteMatriceWord.word[i][pColumIndex]);
		}

	}

	public Word concat(Word pWord) {
		// TODO Auto-generated method stub
		throw new RuntimeException("not implemented yet");
	}

	public void subtitute(byte[] pSubtituteTable) {
		for (int i = 0; i < columnCount; i++) {
			subtituteColumn(i, pSubtituteTable);
		}
	}

	public void subtituteColumn(int pColumnIndex, byte[] pSubtituteTable) {
		for (int i = 0; i < rowCount; i++) {
			word[i][pColumnIndex] = pSubtituteTable[word[i][pColumnIndex] & 0xff];
		}

	}

	public byte[] getByteArray() {
		byte[] array = new byte[rowCount * columnCount];
		if (pageAxis) {
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					array[i * rowCount + j] = word[i][j];
				}
			}
		} else {
			for (int j = 0; j < columnCount; j++) {
				for (int i = 0; i < rowCount; i++) {
					array[j * columnCount + i] = word[i][j];
				}
			}
		}
		return array;
	}

	public void shiftRow(int pRowIndex, int pNbShift, boolean pRightShift) {
		byte memorize;
		if (pRightShift) {
			for (int i = 0; i < pNbShift; i++) {
				int j = columnCount - 1;
				memorize = word[pRowIndex][j];
				for (; j > 0; j--) {
					word[pRowIndex][j] = word[pRowIndex][j - 1];
				}
				word[pRowIndex][j] = memorize;
			}
		} else {
			for (int i = 0; i < pNbShift; i++) {
				int j = 0;
				memorize = word[pRowIndex][j];
				for (; j < columnCount - 1; j++) {
					word[pRowIndex][j] = word[pRowIndex][j + 1];
				}
				word[pRowIndex][j] = memorize;
			}
		}

	}

	public void shiftColumn(int pColumnIndex, int pNbShift, boolean pUpwardShift) {
		byte memorize;
		if (pUpwardShift) {
			for (int i = 0; i < pNbShift; i++) {
				int j = 0;
				memorize = word[j][pColumnIndex];
				for (; j < rowCount - 1; j++) {
					word[j][pColumnIndex] = word[j + 1][pColumnIndex];
				}
				word[j][pColumnIndex] = memorize;
			}
		} else {

			// TODO Auto-generated method stub
			throw new RuntimeException("not implemented yet");
		}

	}

	public void copyColumn(int pOriginalColumnIndex,
			ByteMatriceWord pByteMatriceWord, int pColumnIndex) {
		for (int i = 0; i < rowCount; i++) {
			word[i][pOriginalColumnIndex] = pByteMatriceWord.word[i][pColumnIndex];
		}
	}

	// public void GF2Multiplication(int pColumnIndex, ByteMatriceWord pMatrice)
	// {
	//
	//
	// }

	public ByteMatriceWord GF2Multiplication(ByteMatriceWord pMatrice) {
		ByteMatriceWord newWord = (ByteMatriceWord) this.getCopy();
		for (int j = 0; j < columnCount; j++) {
			newWord.word[0][j] = (byte) (GF2Multiplication((byte) 0x02,
					word[0][j])
					^ GF2Multiplication((byte) 0x03, word[1][j])
					^ word[2][j] ^ word[3][j]);
			newWord.word[1][j] = (byte) (word[0][j]
					^ GF2Multiplication((byte) 0x02, word[1][j])
					^ GF2Multiplication((byte) 0x03, word[2][j]) ^ word[3][j]);
			newWord.word[2][j] = (byte) (word[0][j] ^ word[1][j]
					^ GF2Multiplication((byte) 0x02, word[2][j]) ^ GF2Multiplication(
					(byte) 0x03, word[3][j]));
			newWord.word[3][j] = (byte) (GF2Multiplication((byte) 0x03,
					word[0][j]) ^ word[1][j] ^ word[2][j] ^ GF2Multiplication(
					(byte) 0x02, word[3][j]));
		}

		return newWord;
	}

	private byte GF2Multiplication(byte a, byte b) {
		byte p = 0;
		byte counter;
		byte hi_bit_set;
		for (counter = 0; counter < 8; counter++) {
			if ((b & 1) != 0) {
				p ^= a;
			}
			hi_bit_set = (byte) (a & 0x80);
			a <<= 1;
			if (hi_bit_set != 0) {
				a ^= 0x1b; /* x^8 + x^4 + x^3 + x + 1 */
			}
			b >>= 1;
		}
		return p;
	}

	private void createWord(byte[] pWord, boolean pPageAxis) {
		if (pWord == null || pWord.length == 0) {
			throw new RuntimeException("Can not create a word of size 0");
		}
		pageAxis = pPageAxis;

		if (rowCount == 0) {
			rowCount = (int) Math.sqrt(pWord.length);
			columnCount = rowCount;
		}

		word = new byte[rowCount][columnCount];
		if (pPageAxis) {
			for (int i = 0; i < columnCount; i++) {
				for (int j = 0; j < rowCount; j++) {
					word[j][i] = pWord[i * rowCount + j];
				}
			}
		} else {
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					word[i][j] = pWord[i * columnCount + j];
				}
			}
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				sb.append("[" + String.format("%02x", word[i][j]) + "]");
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		ByteMatriceWord byteMatriceWord = (ByteMatriceWord) obj;
		boolean equals = true;
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				if (word[i][j] != byteMatriceWord.word[i][j]) {
					equals = false;
				}
			}
		}

		return equals;

	}

}
