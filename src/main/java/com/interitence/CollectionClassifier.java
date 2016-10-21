package com.interitence;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionClassifier {
	public static String classify(Set<?> s) {
		return "Set";
	}

	public ACC run(BCC acc) {
		System.out.println("Super ACC return");
		return null;
	}

	public static String classify(List<?> lst) {
		return "List";
	}

	public static String classify(Collection<?> c) {
		return "Unknown Collection";
	}

	public String classify1(Collection<?> c) {
		return "Unknown Collection 1 super";
	}

	public String run(ACC acc) {
		return "super acc";
	}

	public static void main(String[] args) {
		Collection<?>[] collections = { new HashSet<String>(), new ArrayList<BigInteger>(),
				new HashMap<String, String>().values() };

		for (Collection<?> c : collections)
			System.out.println(classify(c));

		Set<String> s = new HashSet<String>();
		System.out.println(classify(s));

		CollectionClassifier cc = new CollectionClassifier();
		System.out.println(cc.classify1(s));

		cc = new CollectionClassifierSub();
		System.out.println(cc.classify1(s));

		BCC acc = new BCC();
		System.out.println(cc.run(acc));

		Object o = new Object();
		acc.run(o);
		o = "string";
		acc.run("sting");
	}
}

class CollectionClassifierSub extends CollectionClassifier {
	// @Override
	public String classify1(List<?> c) {
		return "Unknown Collection 1";
	}

	@Override
	public BCC run(BCC acc) {
		System.out.println("sub acc");
		return new BCC();
	}
}

class ACC {

	public void run(String s) {
		System.out.println("Running string");
	}

}

class BCC extends ACC {
	public void run(Object o) {
		System.out.println("Running object");
	}
}