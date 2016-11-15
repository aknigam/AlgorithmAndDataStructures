package com.algo.linkedlist;



import java.util.LinkedList;

public class LinkedListAlog {

	public static void run(String[] args) {
		LinkedList<Integer> a = new LinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			a.add(i);
		}
		LinkedList<Integer> b = new LinkedList<Integer>();
		for (int i = 6; i < 15; i++) {
			b.add(i);
		}
		System.out.println(a);
		System.out.println(b);

		LinkedList<Integer> c = merge(a, b);
	}

	private static LinkedList<Integer> merge(LinkedList<Integer> a, LinkedList<Integer> b) {
		LinkedList<Integer> c = new LinkedList<Integer>();
		// TODO Auto-generated method stub

		return null;
	}

	public static void frontBack(Node n) {
		Node fast = n;
		Node slow = n;

		while (fast != null) {
			if (fast.next == null) {
				break;
			}
			fast = fast.next.next;
			slow = slow.next;
		}

		Node back = slow.next;
		slow.next = null;
		Node front = n;
		System.out.println(front);
		System.out.println(back);
	}

	public static Node getDummyNode() {
		Node n = new Node(0, null);
		Node current = n;
		for (int i = 1; i < 13; i++) {
			current.next = new Node(i, null);
			current = current.next;
		}
		System.out.println("Size: " + 13);
		return n;
	}

	public static Node recursiveReverse(Node n) {

		Node temp = null;
		if (n == null || n.next == null) {
			return n;
		}
		if (n.next.next == null) {
			temp = n.next;
			n.next = null;
			temp.next = n;
			return temp;
		}
		Node head = n;
		Node a = n.next;
		Node rr = recursiveReverse(n.next);
		head.next = null;
		a.next = head;
		return rr;
	}

	public static void main(String[] args) {
		System.out.println(getDummyNode());
		frontBack(getDummyNode());
		System.out.println(recursiveReverse(getDummyNode()));
	}
}
