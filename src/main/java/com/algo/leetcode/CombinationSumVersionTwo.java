package com.algo.leetcode;

import java.util.*;

/*

https://leetcode.com/problems/combination-sum/

Solution from https://www.geeksforgeeks.org/combinational-sum/
 */
public class CombinationSumVersionTwo {





    public static void main(String[] args) {

        int sum = 8;

        CombinationSumVersionTwo solution= new CombinationSumVersionTwo();

        long t = System.currentTimeMillis();
        int[] nums = new int[]{10,1,2,7,6,1,5, 5, 5, 10, 5, 10}; //new int[]{1,1,1,1,1,1,1,1,1}
        List<List<Integer>> ans
                = solution. combinationSum2(nums, 15);

        t = System.currentTimeMillis() -t;
        System.out.println("Time taken: "+ t);
        // If result is empty, then
        printResults(solution, ans);
    }




    private static Boolean[][] cache;

    private Set<String> unique = new HashSet<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        Map<Integer, Integer> indexTargetmap = new HashMap<>();
        findcombinationsLC(0,candidates,target,ans,new ArrayList<>(), indexTargetmap);
        return ans;
    }

    int counter =0;

    public void findcombinationsLC(int index, int[] candidates, int target, List<List<Integer>> ans,
                                   List<Integer> ds, Map<Integer, Integer> indexTargetmap)
    {


        if(indexTargetmap.containsKey(index) && indexTargetmap.get(index).equals(target)){
            return;
        }
        indexTargetmap.put(index, target);

        if(index==candidates.length)
        {
            if(target==0 )
            {
                ArrayList<Integer> a = new ArrayList<>(ds);
                ans.add(a);
            }
            return;
        }

        if(candidates[index]<=target)
        {
            ds.add(candidates[index]);
            findcombinationsLC(index+1,candidates,target- candidates[index],ans,ds, indexTargetmap);
            ds.remove(ds.size()-1);
        }
        findcombinationsLC(index+1,candidates,target,ans,ds, indexTargetmap);
    }


    private static void printResults(CombinationSumVersionTwo solution, List<List<Integer>> ans) {
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



    // leetcode best solution end
}
