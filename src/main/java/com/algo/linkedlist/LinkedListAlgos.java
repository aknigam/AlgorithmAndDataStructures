package com.algo.linkedlist;



import java.util.LinkedList;

public class LinkedListAlgos {

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

	/**
	 * Split the list into two such that
	 * 1. all even index nodes in one list with same order
	 * 2. all odd index nodes in another list reversed
	 * @param n
     */
	public static void split(Node n){

		if(n == null){
			return;
		}
		Node even = new Node(n.data, null);
		Node currentEven = even;
		if(n.next == null){
			System.out.println(even);
			return;
		}
		Node oddReversed = new Node(n.next.data,  null);
		n = n.next.next;
		int index = 2;
		while (n != null){
			if(index++%2 == 0){
				currentEven = addToTail(currentEven, n.data);
			}
			else{
				oddReversed = addToHead(oddReversed, n.data);
			}
			n=  n.next;
		}

		System.out.println(even);
		System.out.println(oddReversed);

	}

	private static Node addToHead(Node head, int data) {
		if(head == null){
			head = new Node(data, null);
			return head;
		}
		return new Node(data, head);

	}

	private static Node addToTail(Node evenLast, int data) {
		if(evenLast == null){
			return new Node(data, null);
		}
		evenLast.next = new Node(data, null);
		return evenLast.next;
	}


	public static void main(String[] args) {
		Node n = new Node(0, new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, null))))));
//		System.out.println(n);
		split(new Node(0, new Node(1, new Node(2, new Node(3, null)))));

	}

	public static void recursiveReverseDemo() {
		System.out.println(getDummyNode());
		frontBack(getDummyNode());
		System.out.println(recursiveReverse(getDummyNode()));
	}
}
