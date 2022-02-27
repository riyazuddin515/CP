package dp;

import java.util.Scanner;

public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        boolean[] r = new boolean[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            r[i] = canPartition(arr, n);
        }
        scanner.close();
        for(boolean e: r)
            System.out.println(e);
    }
    private static boolean canPartition(int[] arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int e: arr)
            sum += e;
        if (sum % 2 == 1)
            return false;
        int k = sum / 2;
        boolean[][] dp = new boolean[n][k + 1];
        return tabulation(n, k, arr);
    }

    private static boolean helper(int k, int[] arr, int i, boolean[][] dp) {
        if (k == 0)
            return true;
        if (i == 0)
            return arr[0] == k;
        if (dp[i][k])
            return dp[i][k];
        boolean pick = false;
        if (arr[i] <= k)
            pick = helper(k - arr[i], arr, i - 1, dp);
        boolean notPick = helper(k, arr, i - 1, dp);
        return dp[i][k] = pick || notPick;
    }

    private static boolean tabulation(int n, int k, int[] arr) {
        boolean[][] dp = new boolean[n][k + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        dp[0][arr[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= k; t++) {
                boolean pick = false;
                if (arr[i] <= t)
                    pick = dp[i - 1][t - arr[i]];
                boolean notPick = dp[i - 1][t];
                dp[i][t] = pick || notPick;
            }
        }
        return dp[n - 1][k];
    }
}
