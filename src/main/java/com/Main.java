package com;

import java.util.ArrayList;
import java.util.List;

public class Main {

	private static final String LARGE_STRING = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
			+ "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

	public static void main(String[] args) throws InterruptedException {
		List<String> list = new ArrayList<String>();
		while (true) {
			System.out.println("Working...");
			list.add(LARGE_STRING);
			Thread.sleep(2);
		}
		/*
		 * Object wait = new Object(); synchronized (wait) { wait.wait(); }
		 */
	}
}
