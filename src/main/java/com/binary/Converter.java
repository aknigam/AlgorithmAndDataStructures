package com.binary;

public class Converter {

	public static void main(String[] args) {
		String s = "foo";
		covertStringToBinary(s);
	}

	public static void covertStringToBinary(String s) {

		byte[] bytes = s.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			binary.append(' ');
		}
		System.out.println("'" + s + "' to binary: " + binary);
	}
}
