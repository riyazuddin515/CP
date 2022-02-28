package dp;

import java.util.Arrays;
import java.util.Scanner;

public class RodCuttingProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] price = new int[n];
            for (int j = 0; j < n; j++) {
                price[j] = scanner.nextInt();
            }
            r[i] = spaceOptimized(price, n);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int cutRod(int[] price, int n) {
        // Write your code here.
        int[][] dp = new int[n][n + 1];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, price, n, dp);
    }

    private static int helper(int ind, int[] price, int n, int[][] dp) {
        // Write your code here.
        if (ind == 0)
            return n * price[0];
        if (dp[ind][n] != -1)
            return dp[ind][n];
        int notCut = helper(ind - 1, price, n, dp);
        int cut = Integer.MIN_VALUE;
        if (ind + 1 <= n)
            cut = price[ind] + helper(ind, price, n - (ind + 1), dp);
        return dp[ind][n] = Math.max(notCut, cut);
    }

    private static int tabulation(int[] price, int n) {
        int[][] dp = new int[n][n + 1];
        for (int l = 0; l <= n; l++) {
            dp[0][l] = l * price[0];
        }
        for (int ind = 1; ind < n; ind++) {
            for (int n1 = 0; n1 <= n; n1++) {
                int notCut = dp[ind - 1][n1];
                int cut = Integer.MIN_VALUE;
                int rodL = ind + 1;
                if (rodL <= n1)
                    cut = price[ind] + dp[ind][n1 - rodL];
                dp[ind][n1] = Math.max(notCut, cut);
            }
        }
        return dp[n - 1][n];
    }
    private static int spaceOptimized(int[] price, int n) {
        int[] pre = new int[n + 1];
        int[] cur = new int[n + 1];
        for (int l = 0; l <= n; l++) {
            pre[l] = l * price[0];
        }
        for (int ind = 1; ind < n; ind++) {
            for (int n1 = 0; n1 <= n; n1++) {
                int notCut = pre[n1];
                int cut = Integer.MIN_VALUE;
                int rodL = ind + 1;
                if (rodL <= n1)
                    cut = price[ind] + cur[n1 - rodL];
                cur[n1] = Math.max(notCut, cut);
            }
            pre = cur;
        }
        return pre[n];
    }
}
