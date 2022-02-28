package dp;

import java.util.Arrays;
import java.util.Scanner;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int W = scanner.nextInt();
            int[] weight = new int[n];
            int[] profit = new int[n];
            for (int j = 0; j < n; j++) {
                profit[j] = scanner.nextInt();
            }
            for (int j = 0; j < n; j++) {
                weight[j] = scanner.nextInt();
            }
            r[i] = spaceOptimized(n, W, profit, weight);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }
    public static int unboundedKnapsack(int n, int W, int[] profit, int[] weight) {
        // Write your code here.
        int[][] dp = new int[n][W + 1];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, weight, profit, W, dp);
    }
    private static int helper(int i, int[] weight, int[] profit, int W, int[][] dp) {
        if (i == 0) {
            return W / weight[0] * profit[0];
        }
        if (dp[i][W] != -1)
            return dp[i][W];

        int notPick = helper(i - 1, weight, profit, W, dp);
        int pick = 0;
        if (weight[i] <= W)
            pick = profit[i] + helper(i, weight, profit, W - weight[i], dp);

        return dp[i][W] = Math.max(pick, notPick);
    }

    private static int tabulation(int n, int W, int[] profit, int[] weight){
        int[][] dp = new int[n][W + 1];
        for (int w = 0; w <= W; w++) {
            dp[0][w] = w / weight[0] * profit[0];
        }
        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notPick = dp[i - 1][w];
                int pick = 0;
                if (weight[i] <= w)
                    pick = profit[i] + dp[i][w - weight[i]];
                dp[i][w] = Math.max(pick, notPick);
            }
        }
        return dp[n - 1][W];
    }

    private static int spaceOptimized(int n, int W, int[] profit, int[] weight){
        int[] pre = new int[W + 1];
        int[] cur = new int[W + 1];
//        Arrays.fill(cur, 0);
        for (int w = 0; w <= W; w++) {
            pre[w] = w / weight[0] * profit[0];
        }
        for (int i = 1; i < n; i++) {
            for (int w = 0; w <= W; w++) {
                int notPick = pre[w];
                int pick = 0;
                if (weight[i] <= w)
                    pick = profit[i] + cur[w - weight[i]];
                cur[w] = Math.max(pick, notPick);
            }
            pre = cur;
        }
        return pre[W];
    }
}
