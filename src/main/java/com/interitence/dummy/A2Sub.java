package com.interitence.dummy;

public class A2Sub extends A2 {

	public static int i = 20;
	public int j = 20;
	private final int x = 25;

	public static int run() {
		return i;
	}

	@Override
	public int run1() {
		return j;
	}

	@Override
	public int x() {
		return x;
	}

}
