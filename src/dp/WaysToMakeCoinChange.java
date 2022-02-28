package dp;

import java.util.Arrays;
import java.util.Scanner;

public class WaysToMakeCoinChange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = scanner.nextInt();
        }
        int value = scanner.nextInt();
        System.out.println(spaceOptimized(d, value));
    }

    public static long countWaysToMakeChange(int[] denominations, int value) {
        //write your code here
        int n = denominations.length;
        long[][] dp = new long[n][value + 1];
        for (long[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, denominations, value, dp);
    }

    private static long helper(int ind, int[] arr, int target, long[][] dp) {
        if (ind == 0) {
            if (target % arr[ind] == 0)
                return 1;
            else
                return 0;
        }

        if (dp[ind][target] != -1)
            return dp[ind][target];

        long p = 0;
        if (arr[ind] <= target)
            p = helper(ind, arr, target - arr[ind], dp);
        long np = helper(ind - 1, arr, target, dp);

        return dp[ind][target] = p + np;
    }

    private static long tabulation(int[] denominations, int value) {
        int n = denominations.length;
        long[][] dp = new long[n][value + 1];
        for (int target = 0; target <= value; target++) {
            if (target % denominations[0] == 0)
                dp[0][target] = 1;
            else
                dp[0][target] = 0;
        }
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= value; target++) {
                long p = 0;
                if (denominations[ind] <= target)
                    p = dp[ind][target - denominations[ind]];
                long np = dp[ind - 1][target];
                dp[ind][target] = p + np;
            }
        }
        return dp[n - 1][value];
    }

    private static long spaceOptimized(int[] denominations, int value) {
        int n = denominations.length;
        long[] pre = new long[value + 1];
        long[] cur = new long[value + 1];
        Arrays.fill(cur, 0);
        for (int target = 0; target <= value; target++) {
            if (target % denominations[0] == 0)
                pre[target] = 1;
            else
                pre[target] = 0;
        }
        for (int ind = 1; ind < n; ind++) {
            for (int target = 0; target <= value; target++) {
                long p = 0;
                if (denominations[ind] <= target)
                    p = cur[target - denominations[ind]];
                long np = pre[target];
                cur[target] = p + np;
            }
            pre = cur;
        }
        return pre[value];
    }
}
