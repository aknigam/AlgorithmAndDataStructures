package com.algo.dynamicprogramming;

import com.algo.util.ArrayUtils;

/**
 * Created by anand.nigam on 8/6/2015.
 * Asked in Makemytrip interview
 *
 * http://www.geeksforgeeks.org/dynamic-programming-set-31-optimal-strategy-for-a-game/
 *
 */


public class MaxSumGame2 {


	static int[] arr = {1,8,9,244, 56,32,5};
//	static int[] arr = {8, 15, 3, 7};
//	static  int[] arr = {5, 3, 7, 10};
//	static int[] arr = {2, 9,  6};
	static int n = arr.length;
	static Boolean[][] c = new Boolean[n][n];

	static int[][] sums;




	public static void main(String[] args) {

		sums = new int[arr.length][arr.length];
		ArrayUtils.init2DArray(sums);

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = sum+ arr[i];
		}

		int maxsum = maxsum(arr, 0, arr.length -1, sum);
		System.out.println("\n Solution");
		System.out.println(maxsum);
		System.out.println((sum - maxsum));
	}

	private static int maxsum(int[] arr,  int startIndex, int endIndex, int sum) {

		if(sums[startIndex][endIndex]!= 0)
		{
			return sums[startIndex][endIndex];
		}
		if(startIndex == endIndex){
			return arr[startIndex];
		}

		int selectedStart = arr[startIndex];
		int sumAfterStart = sum - arr[startIndex];
		int maxSumAfterStart =  maxsum(arr, startIndex+1, endIndex, sumAfterStart);

		int a = selectedStart + sumAfterStart  - maxSumAfterStart;

		int selectedEnd =arr[endIndex] ;
		int sumBeforeEnd =  sum - arr[endIndex];
		int maxSumBeforEnd =    maxsum(arr, startIndex, endIndex-1, sumBeforeEnd);

		int b = selectedEnd + sumBeforeEnd - maxSumBeforEnd;

		int x =  Math.max( a , b );
		System.out.print(x +" ");
		sums[startIndex][endIndex] = x;
		return  x;

	}


}