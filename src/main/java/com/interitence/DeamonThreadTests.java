package com.interitence;

public class DeamonThreadTests {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new TestT(false), "Simple");
		Thread t2 = new Thread(new TestT(true), "Deamon");
		t2.setDaemon(true);
		t1.start();
		t2.start();
		t2.join();
		System.out.println("FINISHED");
	}

	static class TestT implements Runnable {

		private final boolean isDeamon;

		public TestT(boolean b) {
			isDeamon = b;
		}

		@Override
		public void run() {
			while (true) {
				System.out.println(Thread.currentThread().getName() + ": Running...");
				if (!isDeamon) {
					System.out.println(Thread.currentThread().getName() + ": Not a deamon thread. dying...");
					break;
				}
			}
		}
	}
}
