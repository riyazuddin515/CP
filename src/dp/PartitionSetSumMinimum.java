package dp;

import java.util.Scanner;

public class PartitionSetSumMinimum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            r[i] = minSubsetSumDifference(arr, n);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int minSubsetSumDifference(int[] arr, int n) {
        // Write your code here.
        int sum = 0;
        for (int e : arr)
            sum += e;
        int k = sum;

        boolean[][] dp = new boolean[n][k + 1];
        for (int i = 0; i < n; i++)
            dp[i][0] = true;
        if (arr[0] <= k)
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

        int minDiff = (int) Math.pow(10, 9);
        for (int i = 0; i <= sum / 2; i++) {
            if (dp[n - 1][i]) {
                int s2 = sum - i;
                int abs = Math.abs(s2 - i);
                minDiff = Math.min(minDiff, abs);
            }
        }
        return minDiff;
    }
}
