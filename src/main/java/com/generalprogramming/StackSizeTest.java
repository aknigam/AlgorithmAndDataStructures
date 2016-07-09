package com.generalprogramming;

import java.util.ArrayList;
import java.util.List;

public class StackSizeTest {
	static List<LargeObject> list = new ArrayList<LargeObject>();

	public static void main(String[] args) throws InterruptedException {
		Thread runner = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10000; i++) {
					Thread t = new Thread(new Runnable() {

						@Override
						public void run() {
							StackSizeTest.run(0, getArray());
						}
					});
					t.start();

				}
			}
		});
		runner.start();
		runner.join();

	}

	public static void run(int i, int[] a) {
		// list.add(new LargeObject());
		int frames = Thread.currentThread().getStackTrace().length;
		System.out.println(i + "\t" + frames + "\t" + "\t" + Runtime.getRuntime().totalMemory() / 1000000 + "\t"
				+ Runtime.getRuntime().maxMemory() / 1000000 + "\t" + a.length);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		run(++i, getArray());

	}

	private static int[] getArray() {
		int[] a = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = i;

		}
		return a;
	}

}
