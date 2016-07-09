package com.generalprogramming;

class FinalFieldExample {
	final int x;
	int y;
	static FinalFieldExample f;

	public FinalFieldExample() {
		x = 3;
		y = 4;
	}

	static void writer() {
		f = new FinalFieldExample();
	}

	static void reader() {
		System.out.println();
		System.out.println(FinalFieldExample.f);
		if (FinalFieldExample.f != null) {
			int i = f.x; // guaranteed to see 3
			int j = f.y; // could see 0
			System.out.println("i: " + i);
			System.out.println("j: " + j);
		} else {
			System.out.println("f is not initialized.");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread r = new ReaderT();
		Thread w = new WriterT();
		r.start();
		w.start();
		r.join();
		w.join();
	}

	static class ReaderT extends Thread {
		@Override
		public void run() {
			FinalFieldExample.reader();
		}
	}

	static class WriterT extends Thread {
		@Override
		public void run() {
			FinalFieldExample.writer();
		}
	}
}