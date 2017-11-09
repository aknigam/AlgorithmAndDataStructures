package com.algo.dynamicprogramming;

/**
 * Created by anand.nigam on 8/6/2015.
 * Asked in Makemytrip interview
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
 *
 */


public class MaxSumGame {


	static int[] arr = {1,8,9,244, 56,32,5};
//	static int[] arr = {8, 15, 3, 7};
	//static int[] arr = {2, 4, 6};
	static int n = arr.length;
	static Boolean[][] c = new Boolean[n][n];


	public static void main(String[] args) {


		int[] moves = new int[arr.length];
		int r=arr.length -1;
		int l = 0;
		int i =0;

		// start playing game.

		while(r>=l) {
			if (isleftMoveOptimal(arr, l, r)) {
				moves[i++] = arr[l];l++;
			}
			else{
				moves[i++] = arr[r];r--;
			}
		}
		// game finished. now printing the moves and the sum.
		printMovesWithSum(moves);


	}

	public static boolean isleftMoveOptimal(int[] a, int l, int r){
		//System.out.println(l+" "+ r);
		if(c[l][r] != null){
			// System.out.println(l+" "+ r +" cache hit");
			return c[l][r];
		}
		if(l==r){
			c[l][r]= true;
			return true;
		}
		if(r-l == 1){
			boolean val =  a[l]>a[r]? true: false;
			c[l][r]= val;
			return val;
		}

		int ls = a[l] - (isleftMoveOptimal(a, l + 1, r) ? a[l+1]: a[r]);

		int rs = a[r] - (isleftMoveOptimal(a, l, r - 1) ? a[l]: a[r-1]);

		boolean val = ls > rs;
		c[l][r]= val;
		return val;

	}


	private static void printMovesWithSum(int[] p) {
		StringBuilder player1Moves = new StringBuilder();
		StringBuilder player2Moves = new StringBuilder();
		int player1Sum =0;
		int player2Sum = 0;
		int i =0;
		for(int x : p){
			if(i++%2 ==0 ) {
				player1Moves.append(x).append(" ");
				player1Sum = player1Sum+ x;
			}else {
				player2Moves.append(x).append(" ");
				player2Sum = player2Sum+ x;
			}
		}
		System.out.println("P1 : "+player1Moves+ " sum= "+ player1Sum);
		System.out.println("P2 : "+player2Moves+ " sum= "+ player2Sum);
	}


}