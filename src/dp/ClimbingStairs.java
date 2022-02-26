package dp;

import java.util.Arrays;

public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 3;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println(climbingStairs(n, dp));
    }

    private static int climbingStairs(int n) {
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        return climbingStairs(n - 1) + climbingStairs(n - 2);
    }

    private static int climbingStairs(int n, int[] memo){
        if (n == 0)
            return 1;
        if (n < 0)
            return 0;
        if (memo[n] != -1)
            return memo[n];
        return memo[n] = climbingStairs(n - 1) + climbingStairs(n - 2);
    }
}
