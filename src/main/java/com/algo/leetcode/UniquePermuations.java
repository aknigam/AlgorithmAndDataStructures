package com.algo.leetcode;

import com.algo.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UniquePermuations {

    public static void main(String[] args) {

        UniquePermuations sol = new UniquePermuations();

        int[] nums = new int[]{0,1, 2,3,4,5, 5,6};

        Arrays.sort(nums);


        List<List<Integer>> ans = sol.permuteUnique(nums);
        System.out.println("No. of permutations => "+ans.size());
        int count =1;
        for (List<Integer> line : ans) {
            System.out.println(count++ +" => " + line);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {

        if(nums.length == 0) {
            return Collections.emptyList();
        }

        List<List<Integer>> ans = new ArrayList<>();
        List<List<Integer>> ansToEnd = new ArrayList<>();
        ansToEnd.add(new ArrayList<>(Arrays.asList(nums[0])));

        permuteUniqueInternal(nums, 0, 0, ansToEnd, ans);

        return ans;
    }


    private void permuteUniqueInternal(int[] nums, int start, int end, List<List<Integer>> ansToEnd, List<List<Integer>> ans) {

        if(end >= nums.length-1) {
            ans.addAll(ansToEnd);
            return;
        }

        List<List<Integer>> ansToEndA= new ArrayList<>();
        // ans is all permutations till end
        for (List<Integer> line : ansToEnd) {

            // line has 0 to end
            // end + i element will take all possible positions from end+1 to 0
            int e = nums[end + 1];
            for (int j = end+1; j >= 0 ; j--) {
                ArrayList<Integer> nl = new ArrayList<>(line);
                nl.add(j, e);
//                addToAns(nl, nums, ans);
                ansToEndA.add(nl);
            }
        }
        permuteUniqueInternal(nums, 0, end +1, ansToEndA, ans);

    }

    private void addToAns(List<Integer> line, int[] nums, List<List<Integer>> ans) {
        ArrayList<Integer> l = new ArrayList<>(line);
        for (int i = line.size(); i < nums.length ; i++) {
            l.add(nums[i]);
        }
        ans.add(l);
    }


    //    static ArrayList<Integer> nums = new ArrayList<Integer>();
    ArrayList<Integer> curr = new ArrayList<Integer>();
//    static List<List<Integer>> ans = new ArrayList<>();
//    static ArrayList<Boolean> visited = new ArrayList<Boolean>();

    // Function to fill the vector curr
    // while maintaining the indices visited
    // in the array num
    void backtrack(int[] nums, boolean[] visited, List<List<Integer>> ans) {

        // If current permutation is complete
        if (curr.size() == nums.length) {
            ans.add(new ArrayList<>(curr));
//            for (int i = 0; i < curr.size(); i++) {
//                System.out.print(curr.get(i) + " ");
//            }
//            System.out.println();
        }

//        ArrayUtils.printIntArray(nums);
//        ArrayUtils.printIBoolArray(visited);

        // Traverse the input vector
        for (int i = 0; i < nums.length; i++) {

            // If index is already visited
            if (visited[i])
                continue;

            // If the number is duplicate
            // If i > 0 && nums[i] == nums[i – 1]: Add nums[i] in the current permutation
            // only if nums[i – 1] hasn’t been added in the permutation, i.e. visited[i – 1] is false
            if (i > 0 && (nums[i] == nums[i - 1]) && !visited[i - 1]) {
                continue;
            }

            // Set visited[i] flag as true
            visited[i] = true;

            // Push nums[i] to current vector
            curr.add(nums[i]);

            // Recursive function call
            backtrack(nums, visited, ans);

            // Backtrack to the previous
            // state by unsetting visited[i]
            visited[i] = false;

            // Pop nums[i] and place at
            // the back of the vector
            curr.remove(curr.size() - 1);
        }
    }

    // Function to pre-process the vector num
    List<List<Integer>> permuteDuplicates(int[] nums, List<List<Integer>> ans) {
        // Sort the array
//        Collections.sort(UniquePermuations.nums);
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            visited[i] = false;
        }

        // Find the distinct permutations of num
        backtrack(nums, visited, ans);

        return ans;

    }

    // Function call to print all distinct
    // permutations of the vector nums

    List<List<Integer>> getDistinctPermutations(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        permuteDuplicates(nums, ans);

        return ans;


    }

}


