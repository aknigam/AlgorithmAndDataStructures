package com;

public class Test1 {

	public static void main(String[] args) {
		String s = "a";
		String l = "b";
		System.out.println(l.equals(s = l));

		int i = 10;
		boolean b = (i++ > 10) && (++i < 15);
		System.out.println(i);
	}
}
