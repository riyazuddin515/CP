package dp;

import java.util.Arrays;
import java.util.Scanner;

public class TargetSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int target = scanner.nextInt();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = scanner.nextInt();
            }
            r[i] = targetSum(n, target, arr);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    public static int targetSum(int n, int target, int[] arr) {
        // Write your code here.
        return countPartitions(n - 1, target, arr);
    }

    private static int helper(int i, int[] arr, int tar, int[][] dp) {
        if (i == 0) {
            if (tar == 0 && arr[0] == 0)
                return 2;
            if (tar == 0 || arr[0] == tar)
                return 1;
            return 0;
        }
        if (dp[i][tar] != -1)
            return dp[i][tar];
        int pick = 0;
        if (arr[i] <= tar)
            pick = helper(i - 1, arr, tar - arr[i], dp);
        int notPick = helper(i - 1, arr, tar, dp);
        return dp[i][tar] = (pick + notPick);
    }

    public static int countPartitions(int n, int d, int[] arr) {
        // Write your code here.
        int sum = 0;
        for (int e : arr)
            sum += e;
        if (sum - d < 0 || (sum - d) % 2 == 1)
            return 0;
        int tar = (sum - d) / 2;
        int[][] dp = new int[n][tar + 1];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, arr, tar, dp);
    }

}
