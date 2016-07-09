package com.algo;

public class ThreadTest {

	public static void main(String[] args) {

		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ": I am runnable");
			}
		};
		Thread t = new Thread(runnable, "Thread A") {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ": I am thread.");
			}
		};

		Thread t1 = new Thread("Thread B") {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + ": I am thread.");
			}
		};

		Thread t2 = new Thread(runnable, "Thread C");
		t.start();
		t1.start();
		t2.start();
	}

}
