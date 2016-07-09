package com.generalprogramming;

import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerConsumer {

	public static void main(String[] args) {
		final ProducerConsumer pc = new ProducerConsumer();
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					pc.produce();
				}
			}
		}, "producer");

		Thread t4 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					pc.produce();
				}
			}
		}, "producer2");

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					pc.consume();
				}
			}
		}, "consumer");
		t.start();
		t1.start();
		t4.start();
	}

	private final Object mutex = new Object();
	private final AtomicBoolean isEmpty = new AtomicBoolean(true);

	public void produce() {

		synchronized (mutex) {
			while (!isEmpty.get()) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isEmpty.set(false);
			print("produced");
			mutex.notifyAll();
		}
	}

	private void print(String s) {
		System.out.println(Thread.currentThread().getName() + ": " + s);
	}

	public void consume() {

		synchronized (mutex) {
			while (isEmpty.get()) {
				try {
					mutex.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			isEmpty.set(true);
			print("consumed");
			mutex.notifyAll();
		}

	}
}
