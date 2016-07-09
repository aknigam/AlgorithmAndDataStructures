package com;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

	public static void main(String[] args) throws InterruptedException {
		ThreadPool pool = new ThreadPool();
		for (int i = 0; i < 100; i++) {
			pool.execute(new Runnable() {

				@Override
				public void run() {
					print("\t Office again...");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});

		}
	}

	public ThreadPool() {
		Thread[] ts = new WorkerThread[5];
		int i = 0;
		for (Thread thread : ts) {
			String name = "T" + i++;
			System.out.println(name);
			thread = new WorkerThread(queue, name);
			thread.start();
		}

	}

	private final LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

	public void execute(Runnable runnable) throws InterruptedException {
		queue.put(runnable);
	}

	static class WorkerThread extends Thread {
		private final LinkedBlockingQueue<Runnable> queue;

		WorkerThread(LinkedBlockingQueue<Runnable> queue, String string) {
			super(string);
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				Runnable work = null;
				try {
					print("Waiting for work...");
					work = queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
					return;
				}

				work.run();
				print("Work done...");

			}
		}

	}

	public static void print(String string) {
		System.out.println(Thread.currentThread().getName() + ": " + string);
	}

	public static void testCallable(String[] args) throws InterruptedException, ExecutionException {
		Callable<String> callable = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(500);
				return "Finished";
			}
		};
		ExecutorService pool = Executors.newSingleThreadExecutor();
		Future<String> result = pool.submit(callable);
		System.out.println("1. " + result.get());
		pool.shutdown();
		result = pool.submit(callable);
		System.out.println("2. " + result.get());
	}
}
