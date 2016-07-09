package com.jvm;

public class AnotherClass {

	static {
		System.out.println("Another class static initializer");
	}
	{
		System.out.println("Another class normal initializer");
	}
}
