package com.algo.leetcode;

/*

https://leetcode.com/problems/container-with-most-water/description/


https://www.code-recipe.com/post/container-with-most-water
Solution 2: Optimized Solution


This problem can be solved efficiently in linear time using two pointer technique. If you observe the previous solution
carefully,  you will notice a pattern.


For instance, if [1,8,6,2,5,4,8,3,7] is the given array, if you start with the combination of first and last heights and
 move inwards i.e. (1,7), (1,3), (1,8), (1,4) and so on, you can see that you get no benefit by using combinations
 (1,3), (1,8), (1,4) so on after (1,7) to calculate area, as the minimum height obtained by using any of these
 combinations remains same. This is so because in all cases 2nd height is greater than the 1st height (height1= 1, which
  is same for all the pairs), and since we take the minimum of 1st and 2nd heights while calculating the area, we always
   end up with 1 as the result(for minimum height) for all the above combinations. This is true for all such cases where
    for a set of combinations one of the heights is same and it is smaller than the other height in all pairs. Also the
    width keeps decreasing as we move inwards, for (1,7) width is 8, for (1,3) width is 7, for (1,8) width is 6 and so
     on. Therefore the area also will decrease.


We can use this to our advantage to solve this problem in linear time. This approach involves the following steps:

We need to start our algorithm with left pointer pointing to the 0th index and right pointer pointing to the last index
 of the heights array.

We calculate the area using the left and right heights using the previous formula.

After this we need to retain maximum of left and right pointers (heights) and move the other pointer. For example if our
 (left, right) = (1,7), after calculating the area, we check which of (left height, right height) is larger. In this
 case, right height i.e. 7 is the larger of the two. So, we keep the right pointer at the same position and increment
 the left pointer by 1.

Similarly, if the left element was larger as in (8,3) then we keep the left pointer at the same index and decrement
the right pointer after calculating the area for (8, 3). We can do this because we know, we do not get any benefit
(in terms of area obtained) by retaining the smaller height, and thus this would have no impact on the output.

We have to repeat the above steps as long as our left pointer is less than right pointer, after which we return the
 maximum area obtained thus far as the result.


This algorithm goes through each array element only once to find the result, so the time complexity of this algorithm
 is O(n).





 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        ContainerWithMostWater cwmw = new ContainerWithMostWater();
        int[] h= {1,8,6,2,5,4,8,3,7};
        System.out.println(cwmw.maxArea(h));
    }

    public int maxArea(int[] height) {

        int len = height.length;
        if (len <= 1) {
            return 0;
        }

        int maxArea = 0;
        int area = 0;
        for (int i = 0, k= len -1; i < k ; ) {
            int lh = height[i];
            int rh = height[k];

            if(lh < rh) {
                area = (k-i) * lh;
                i++;
            }
            else {
                area = (k-i) * rh;
                k--;
            }

            maxArea = Math.max(maxArea, area);
        }




        return maxArea;
    }
}
