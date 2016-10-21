package com.generalprogramming;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExamples {

	public static void main(String[] args) {
		BlockingQueue q = new ArrayBlockingQueue<Object>(4);
		Producer p = new Producer(q);
		Consumer c1 = new Consumer(q);
		Consumer c2 = new Consumer(q);
		new Thread(p).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}

	static class Producer implements Runnable {
		private final BlockingQueue queue;

		Producer(BlockingQueue q) {
			queue = q;
		}

		@Override
		public void run() {
			try {
				while (true) {
					queue.put(produce());
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		Object produce() {
			System.out.println("Producing");
			return new Object();
		}
	}

	static class Consumer implements Runnable {
		private final BlockingQueue queue;

		Consumer(BlockingQueue q) {
			queue = q;
		}

		@Override
		public void run() {
			try {
				while (true) {
					consume(queue.take());
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		void consume(Object x) {
			System.out.println("Consuming");
		}
	}

}
