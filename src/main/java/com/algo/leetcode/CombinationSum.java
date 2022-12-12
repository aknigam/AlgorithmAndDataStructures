package com.algo.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/*

https://leetcode.com/problems/combination-sum/

Solution from https://www.geeksforgeeks.org/combinational-sum/
 */
public class CombinationSum {

    static List<List<Integer>> combinationSum(int[] candidates, int sum) {



        List<List<Integer>> ans
                = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        // first do hashing since hashset does not always
        // sort
        //  removing the duplicates using HashSet and
        // Sorting the arrayList

        Set<Integer> set = Arrays.stream(candidates).boxed().collect(Collectors.toSet());

        List<Integer> arr = new ArrayList<>(set);
        Collections.sort(arr);

        findNumbers(ans, arr, sum, 0, temp);
        return ans;
    }

    static void
    findNumbers(List<List<Integer>> ans,
                List<Integer> arr, int sum, int index,
                List<Integer> temp) {

        if (sum == 0) {

            // Adding deep copy of list to ans

            ans.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < arr.size(); i++) {

            // checking that sum does not become negative

            if ((sum - arr.get(i)) >= 0) {

                // adding element which can contribute to
                // sum

                temp.add(arr.get(i));

                findNumbers(ans, arr, sum - arr.get(i), i,
                        temp);

                // removing element from list (backtracking)
                temp.remove(arr.get(i));
            }
        }
    }

    // Driver Code

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(2);
        arr.add(4);
        arr.add(6);
        arr.add(8);

        int sum = 8;

        List<List<Integer>> ans
                = combinationSum(new int[]{2, 4, 6, 8}, sum);

        // If result is empty, then
        if (ans.size() == 0) {
            System.out.println("Empty");
            return;
        }

        // print all combinations stored in ans

        for (int i = 0; i < ans.size(); i++) {

            System.out.print("(");
            for (int j = 0; j < ans.get(i).size(); j++) {
                System.out.print(ans.get(i).get(j) + " ");
            }
            System.out.print(") ");
        }
    }


    // leetcode best solution

    public void findcombinationsLC(int index,int[] candidates,int target,List<List<Integer>> ans,ArrayList<Integer> ds)
    {
        if(index==candidates.length)
        {
            if(target==0)
            {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if(candidates[index]<=target)
        {
            ds.add(candidates[index]);
            findcombinationsLC(index,candidates,target- candidates[index],ans,ds);
            ds.remove(ds.size()-1);
        }
        findcombinationsLC(index+1,candidates,target,ans,ds);
    }
    public List<List<Integer>> combinationSumLC(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findcombinationsLC(0,candidates,target,ans,new ArrayList<>());
        return ans;
    }


    // leetcode best solution end
}
