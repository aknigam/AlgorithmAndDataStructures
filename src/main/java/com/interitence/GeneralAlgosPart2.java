package com.interitence;

import com.interitence.dummy.A2;
import com.interitence.dummy.A2Sub;

public class GeneralAlgosPart2 {

	public static void main(String[] args) {
		A2 a = new A2();
		A2 as = new A2Sub();

		System.out.println(a.i + " " + a.j + " " + a.run() + " " + a.run1() + " " + a.x());
		System.out.println(as.i + " " + as.j + " " + as.run() + " " + as.run1() + " " + as.x());

		A2Sub as1 = new A2Sub();
		System.out.println(as1.i + " " + as1.j + " " + as1.run() + " " + as1.run1() + " " + as1.x());
//		System.runFinalizersOnExit(true);
	}

}
