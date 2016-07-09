package com.interitence.dummy;

public class A2 {

	public static int i = 10;
	public int j = 10;
	private int x = 15;

	public A2() {
		x = 15;
	}

	@Override
	protected void finalize() throws Throwable {
		try {
			System.out.println("Finalizing A2");
			x = 0;
		} finally {
			super.finalize();
		}
	};

	public static int run() {
		return i;
	}

	public int x() {
		return x;
	}

	private int cx() {
		return x = 90;
	}

	public int run1() {
		return j;
	}

}
