package dp;

import java.util.Arrays;
import java.util.Scanner;

public class ZeroOneKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] weight = new int[n];
            int[] value = new int[n];
            for (int j = 0; j < n; j++) {
                weight[j] = scanner.nextInt();
            }
            for (int j = 0; j < n; j++) {
                value[j] = scanner.nextInt();
            }
            int maxWeight = scanner.nextInt();
            r[i] = spaceOptimized(weight, value, n, maxWeight);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, weight, value, maxWeight, dp);
    }

    private static int helper(int i, int[] weight, int[] value, int maxWeight, int[][] dp) {
        if (i == 0) {
            if (weight[i] <= maxWeight)
                return value[i];
            return 0;
        }
        if (dp[i][maxWeight] != -1)
            return dp[i][maxWeight];
        int notPick = helper(i - 1, weight, value, maxWeight, dp);
        int pick = Integer.MIN_VALUE;
        if (weight[i] <= maxWeight)
            pick = value[i] + helper(i - 1, weight, value, maxWeight - weight[i], dp);
        return dp[i][maxWeight] = Math.max(pick, notPick);
    }

    private static int tabulation(int[] weight, int[] value, int n, int maxWeight) {
        int[][] dp = new int[n][maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
            dp[0][i] = value[0];
        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                int notPick = dp[i - 1][w];
                int pick = Integer.MIN_VALUE;
                if (weight[i] <= w)
                    pick = value[i] + dp[i - 1][w - weight[i]];
                dp[i][w] = Math.max(pick, notPick);
            }
        }
        return dp[n - 1][maxWeight];
    }

    //Not working - two arrays pre and curr
    private static int spaceOptimized(int[] weight, int[] value, int n, int maxWeight) {
        int[] pre = new int[maxWeight + 1];
        int[] cur = new int[maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
            pre[i] = value[0];
        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= maxWeight; w++) {
                int notPick = pre[w];
                int pick = Integer.MIN_VALUE;
                if (weight[i] <= w)
                    pick = value[i] + pre[w - weight[i]];
                cur[w] = Math.max(pick, notPick);
            }
            pre = cur;
        }
        return pre[maxWeight];
    }

    // Single array of MaxWeight length
    private static int spaceOptimized1(int[] weight, int[] value, int n, int maxWeight) {
        int[] pre = new int[maxWeight + 1];
        for(int i = weight[0]; i <= maxWeight; i++)
            pre[i] = value[0];
        for (int i = 1; i < n; i++) {
            for (int w = maxWeight; w >= 0; w--) {
                int notPick = pre[w];
                int pick = Integer.MIN_VALUE;
                if (weight[i] <= w)
                    pick = value[i] + pre[w - weight[i]];
                pre[w] = Math.max(pick, notPick);
            }
        }
        return pre[maxWeight];
    }
}
