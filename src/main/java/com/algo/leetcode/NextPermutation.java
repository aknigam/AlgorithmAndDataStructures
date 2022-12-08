package com.algo.leetcode;

import com.algo.util.ArrayUtils;

import java.util.Arrays;

/**

 https://leetcode.com/problems/next-permutation/

1, 2, 3, 4
1, 2, 4, 3
1, 3, 2, 4
1, 3, 4, 2
1, 4, 2, 3
1, 4, 3, 2

2, 1, 3, 4
2, 1, 4, 3
2, 3, 1, 4
2, 3, 4, 1
2, 4, 1, 3
2, 4, 3, 1

1, 2, 3, 4, 5

a, b, c, d, e
a, b, c, e, d
a, b, d, c, e
a, b, d, e, c


1, 2, 3, 5, 4
1, 2, 4, 3, 5
1, 2, 4, 5, 3

1, 2, 3, 4, 5, 6, 7, 8, 9
1, 2, 3, 4, 5, 6, 7, 9, 8
1, 2, 3, 4, 5, 6, 8, 7, 9
1, 2, 3, 4, 5, 6, 8, 9, 7
1, 2, 3, 4, 5, 6, 9, 7, 8
1, 2, 3, 4, 5, 6, 9, 8, 7

1, 2, 3, 4, 5, 7, 6, 8, 9
1, 2, 3, 4, 5, 7, 6, 9, 8


9, 8, 7, 6, 4, 1, 2, 3, 5
9, 8, 7, 6, 4, 1, 2, 5, 3
9, 8, 7, 6, 4, 1, 3, 2, 5
9, 8, 7, 6, 5, 4, 3, 2, 1


 */
public class NextPermutation {

    public static void main(String[] args) {



        NextPermutation np = new NextPermutation();
        int[] nums = new int[]{3 ,2, 1};
        np.nextPermutation(nums);
        ArrayUtils.printIntArray(nums);

        nums = new int[]{1,2,3,4,5,6,7,8,9};

        for (int i = 0; i < 10000; i++) {

            np.nextPermutation(nums);
            if(nums[0] == 9 && nums[nums.length -1] == 1)
                break;
        }


    }


    public void nextPermutation(int[] nums) {

        int size =  nums.length;
        if(size == 0 || size == 1) {
            return;
        }
        int prev = nums[size -1];
        boolean found = false;
        for (int i = nums.length -2; i > -1; i--) {
            if( nums[i] < prev ) {
                found = true;
                int s = i + 1;
                int e = nums.length -1;
                int a = nums[i];


                for (int j = e; j >= s ; j--) {
                    if(nums[j] > a) {
                        nums[i] = nums[j];
                        nums[j] = a;
                        break;
                    }
                }
                reverse(nums, s, e+1);
//                Arrays.sort(nums, s, e+1);
                break;

                // nums[i] should be replaced with the smalled number > num[i] in the right side array
            }
            else {
                prev = nums[i];
            }
        }
        if(!found) {
            reverse(nums, 0, nums.length);
        }

        ArrayUtils.printIntArray(nums);

    }

    private static void reverse(int[] nums, int s, int e) {
        for (int i = s; i < e/2; i++) {
            int a = nums[i];
            nums[i] = nums[nums.length -1 -i];
            nums[nums.length -1 -i] = a;
        }
    }
}
