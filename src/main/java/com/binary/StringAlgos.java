package com.binary;

import java.nio.charset.Charset;
import java.text.BreakIterator;

public class StringAlgos {

	public static void run2(String[] args) {

		String s1 = "hello anand";
		String s2 = "hello_anand";
		Converter.covertStringToBinary(s1);
		Converter.covertStringToBinary(s2);
		byte[] b1 = s1.getBytes();
		System.out.print("'" + s1 + "' to binary: ");
		for (byte b : b1) {
			System.out.print(Integer.toBinaryString(b) + " ");
			System.out.print(Character.toChars(b));
		}
		System.out.println("");
		System.out.print("'" + s2 + "' to binary: ");
		byte[] b2 = s2.getBytes();
		for (byte b : b2) {
			System.out.print(Integer.toBinaryString(b) + " ");
		}
	}

	public static void run1(String[] args) {
		System.out.println(Character.codePointAt("ABC".toCharArray(), 1));
		System.out.println(Character.toChars(65));
		System.out.println(Character.toChars(0x0040));
		char x = 41;
		System.out.println(x);
		System.out.println(Character.toCodePoint('A', 'B'));
		System.out.println(Charset.defaultCharset().name());
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Character.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Byte.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Short.MAX_VALUE));

	}

	public static void run(String[] args) {
		// System.out.println(Character.toChars(270));
		System.out.println(Integer.toBinaryString(Character.MAX_VALUE));
		System.out.println(Integer.toBinaryString(Byte.MAX_VALUE) + " " + Integer.valueOf(Byte.MAX_VALUE));
		// System.out.println(Character.get(Byte.MAX_VALUE));
		char ch = Character.MAX_VALUE;
		char[] src = new char[] { ch, ch, ch, ch, ch };
		String s = new String(src);
		System.out.println("S: " + s);
		char[] charar = s.toCharArray();
		for (char c : charar) {
			System.out.print(Character.getNumericValue(c));
			System.out.print(" ");
			System.out.print(Character.toChars(c));
		}
		System.out.println("");
		byte[] sb = s.getBytes();
		for (byte b : sb) {
			System.out.print(Byte.toString(b));
			System.out.print(" ");
			System.out.print(Character.toChars(b));
		}
		System.out.println("------------------------");
		for (int i = 0; i < sb.length; i++) {
			System.out.println("'" + Integer.toBinaryString(charar[i]) + "' " + Integer.valueOf(charar[i]));
			System.out.println("'" + Integer.toBinaryString(sb[i]) + "' " + Integer.valueOf(sb[i]));
		}

		System.out.println(sb.length);
		System.out.println("S: " + new String(sb));
	}

	public static void charinfo() {

		System.out.println(Charset.defaultCharset().name());
		char c = '0';
		int i = c;
		System.out.println(i);
		i = Character.MAX_VALUE;
		System.out.println(Integer.toBinaryString(i) + " " + Integer.toHexString(i) + " " + Integer.toOctalString(i));
		i = Character.MIN_VALUE;
		System.out.println(Integer.toBinaryString(i) + " " + Integer.toHexString(i) + " " + Integer.toOctalString(i));
		Byte b = Byte.MAX_VALUE;
		i = b;
		System.out.println(Integer.toBinaryString(i) + " " + Integer.toHexString(i) + " " + Integer.toOctalString(i));
		System.out.println(System.getProperty("file.encoding"));
		System.out.println(Float.MAX_VALUE + " " + Float.toHexString(Float.MAX_VALUE));

	}

	public static void breakIteratorTest() {
		BreakIterator iterator = BreakIterator.getWordInstance();

		String words = "Hello, I am Anand";
		iterator.setText(words);
		System.out.println(words.charAt(iterator.next()));
		System.out.println(words.charAt(iterator.next()));
		System.out.println(words.charAt(iterator.next()));
		System.out.println(words.charAt(iterator.next()));
		System.out.println(words.charAt(iterator.next()));
		System.out.println(words.charAt(iterator.next()));

	}

	public static void unicodexamples() {
		String str = "\u00F6";
		System.out.println(str);
	}

}
