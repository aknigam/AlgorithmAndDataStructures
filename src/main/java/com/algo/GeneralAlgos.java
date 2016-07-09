package com.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GeneralAlgos {

	private String state;

	public static void run1() {
		System.out.println("run1");
	}

	public static void run2(String s) {
		System.out.println("run2");
	}

	public static void main(String[] args) {

	}

	public static void genericsTest(String[] args) {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new Integer(42));
		String s = strings.get(0); // Compiler-generated cast
	}

	private static void unsafeAdd(List list, Object o) {
		list.add(o);
	}

	public static void run(String[] args) {

		TreeSet<C> set = new TreeSet<C>();
		set.add(new C());
		set.add(new C());
	}

	void print(int n) {

		if (n > 0) {
			System.out.println("Hello");
			System.out.println(n - 1);
		} else {
			System.out.println("world");
		}
	}

	class B {
		public void run() {
			System.out.println(state);
		}

	}

	static class C {
		public void run() {
			// following does not compiles
			// System.out.println(state);
		}

	}

	static class D {
		static int i = 10;
		static {
			if (i == 10)
				throw new RuntimeException("Can't create class D");
		};
	}
}
