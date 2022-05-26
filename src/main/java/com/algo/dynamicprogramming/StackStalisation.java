package com.algo.dynamicprogramming;

public class StackStalisation {

    public static void main(String[] args) {
        StackStalisation ss = new StackStalisation();

//        int[] a = {6, 5, 4, 3}; //{2, 5, 3, 6, 5};
//        int[] a = {5, 2};
        int[] a = {100, 100, 1, 1};
        int A = 2;
        int B =  1;

        long cost = ss.getMinimumSecondsRequired(a.length, a, A, B);

        System.out.println(cost);
    }

    public long getMinimumSecondsRequired(int N, int[] R, int A, int B) {



        /*
         1. if it is the first step then calculate min height
         2. now find the allowed action based on previous
         2. two options


         if both the costs or same then
         */



        // Write your code here
        print(R, -1);
        return getMinimumSecondsRequiredInternal(N, R, A, B, 0, 0, 0);
    }

    // A in increase cost in seconds
    // B in decrease cost in seconds
    public long getMinimumSecondsRequiredInternal(int N, int[] R, int A, int B, int index, long cost, int prevHeight) {


        int[] d = new int[R.length];
        System.arraycopy(R, 0, d, 0, R.length);


        if(index == R.length) {
            print(d, 0);
            return 0;
        }
        int minHeight = index == 0 ? 1 : prevHeight+ 1;

        int actualHeight = R[index];


        if(actualHeight > minHeight) {
            // reduce
            int maxDecrease =  actualHeight - minHeight;

            if(index == R.length -1) {
                print(d, cost);
                return cost;
            }

            long minCost = Integer.MAX_VALUE;
            for (int i = 0; i <= maxDecrease; i++) {
                long stepCost = i * B;
                d[index] = R[index] - i;
                long c =  getMinimumSecondsRequiredInternal(N, d, A , B , index + 1, cost + stepCost, R[index] -i);
                if( c < minCost) {
                    minCost = c;
                }
            }
            cost = minCost;
            print(d, cost);
            return cost;
            // decrease can be lesser than maxDecrease. Try all options.
        }
        else if(actualHeight < minHeight) {
            // increase
            int minIncrease = minHeight - actualHeight;
            // this is not optional and there is no advantage of increasing more

            if(index == R.length -1) {
                cost =  cost + minIncrease * A;
                d[index] = d[index] + minIncrease;
                print(d, cost);
                return cost;
            }


            int stepCost = minIncrease * A;
            d[index] = d[index] + minIncrease;
            cost =  getMinimumSecondsRequiredInternal(N, d, A , B , index + 1, cost + stepCost, R[index] + minIncrease);
            print(d, cost);
            return cost;

        }
        else {
            if(index == R.length -1) {
                print(d, cost);
                return cost;
            }
            // do nothing
            cost =  getMinimumSecondsRequiredInternal(N, R, A , B , index + 1, cost, R[index] );
            print(d, cost);
            return cost;
        }

    }

    private void print(int[] d, long cost) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < d.length; i++) {
            sb.append(d[i]);
            if(i < d.length) {
                sb.append(",");
            }
        }
//        System.out.println("Cost: "+ cost +" > "+ sb.toString());
    }
}
