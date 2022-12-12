package com.algo.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/*

https://leetcode.com/problems/combination-sum/

Solution from https://www.geeksforgeeks.org/combinational-sum/
 */
public class CombinationSumVersionTwo {





    public static void main(String[] args) {

        int sum = 30;

        CombinationSumVersionTwo solution= new CombinationSumVersionTwo();

        long t = System.currentTimeMillis();
        List<List<Integer>> ans
                = solution. combinationSum2(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, sum);

        t = System.currentTimeMillis() -t;
        System.out.println("Time taken: "+ t);
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


        System.out.println("\n\n"+solution.unique);
    }

    Set<String> unique = new HashSet();
    // leetcode best solution

    private static Boolean[][] cache;


    public void findcombinations(int index,int[] candidates,int target,List<List<Integer>> ans,ArrayList<Integer> ds)
    {

        if(cache[index][target] != null && !cache[index][target]) {
                return;
        }
        if(index>=candidates.length)
        {
            return;
        }


        if(target==0)
        {
            ans.add(new ArrayList<>(ds));
            cache[index][target] = true;
            System.out.println("Added => ["+index+" - "+target+"] "+ds);
        }
        else {
            cache[index][target] = false;
        }


        for (int i = index; i < candidates.length; i++) {

            if((target - candidates[i]) > 0 )
            {
                ds.add(candidates[i]);
                findcombinations(index+1,candidates,target- candidates[index],ans,ds);
                ds.remove(ds.size()-1);
            }
        }

//        findcombinations(index+1,candidates,target,ans,ds);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        cache = new Boolean[candidates.length+1][target+1];
        List<List<Integer>> ans = new ArrayList<>();
        findcombinations(0,candidates,target,ans,new ArrayList<>());
        return ans;
    }



    // leetcode best solution end
}
