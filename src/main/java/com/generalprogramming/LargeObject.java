package com.generalprogramming;

public class LargeObject {

	long[] l;

	public LargeObject() {
		l = new long[10];
		for (long i = 0; i < l.length; i++) {
			l[(int) i] = i;

		}
	}
}
