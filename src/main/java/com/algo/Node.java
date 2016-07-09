package com.algo;

public class Node {

	int i;
	Node next;

	public Node(int j, Node next) {
		i = j;
		this.next = next;
	}

	@Override
	public String toString() {
		if (next == null)
			return "" + i;
		else {
			return i + "\t" + next.toString();
		}
	}
}
