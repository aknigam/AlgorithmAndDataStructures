package com.jvm;

public class StackTests {

	static {
		System.out.println("Static initializer");
		// main(null);
		// System.out.println("Static initializer finished");
	}
	static AnotherClass mClass;
	{
		System.out.println("Normal initializer");
	}

	public static void main(String[] args) {
		System.out.println("Executing main");
		StackTests stackTests = new StackTests();
		Thread t = new ForEverRunner();
		t.setDaemon(true);
		t.start();
		Thread t2 = new NormalRunner();
		t2.start();
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		System.out.println("EXITING");
		// System.exit(0);
		// StackTests stackTest = new StackTests();
	}

	public static void run() {
		String[] s = new String[] { "anand", "nigam" };
		run();
	}

}

class ForEverRunner extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println("Forever runner");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class NormalRunner extends Thread {
	@Override
	public void run() {
		int i = 0;
		while (true) {
			if (i++ > 5)
				break;
			System.out.println("Normal runner");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Normal finished");
	}
}
