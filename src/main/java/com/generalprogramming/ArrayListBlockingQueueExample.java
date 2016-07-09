package com.generalprogramming;

import java.util.concurrent.LinkedBlockingQueue;

public class ArrayListBlockingQueueExample {

	public static void main(String[] args) {
		A a11 = new A("a");
		A a12 = new A("a");
		A a3 = new A("b");
		A a4 = new A("c");
		A a5 = new A("d");
		long time = System.currentTimeMillis();

		// ArrayBlockingQueue<A> queue = new ArrayBlockingQueue<A>(1000006);
		LinkedBlockingQueue<A> queue = new LinkedBlockingQueue<ArrayListBlockingQueueExample.A>();
		try {
			// queue.put(a11);
			// queue.put(a12);
			// queue.put(a3);
			// queue.put(a4);
			// queue.put(a5);
			// queue.put(a4);
			for (int i = 0; i < 1000000; i++) {
				// System.out.println("putting " + i);
				queue.put(new A("a." + i));
			}
			System.out.println(queue.size());
			// System.out.println(queue);
			// System.out.println("Before");
			queue.remove(new A("a." + 500000));
			System.out.println(queue.size());
			queue.put(a3);
			System.out.println(queue.size());
			// System.out.println("After");
			// queue.remove(a12);
			// System.out.println(queue);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Time take: " + (System.currentTimeMillis() - time));

	}

	static class A {
		public String a;
		public static int counter = 0;
		public int i = ++counter;

		@Override
		public String toString() {
			return a + "." + i;
		}

		public A(String s) {
			a = s;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj instanceof A) {
				return a.equals(((A) obj).a);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return i;
		}
	}

}
