package com.algo.linkedlist;

public class Node {

	int data;
	Node next;

	public Node(int j, Node next) {
		data = j;
		this.next = next;
	}

	@Override
	public String toString() {
		if (next == null)
			return "" + data;
		else {
			return data + "\t" + next.toString();
		}
	}
}
