package dp;

import java.util.Arrays;
import java.util.Scanner;

public class NumberOfSubsets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int tar = scanner.nextInt();
            int[] num = new int[n];
            for (int j = 0; j < n; j++) {
                num[j] = scanner.nextInt();
            }
            r[i] = spaceOptimized(num, tar);
        }
        scanner.close();
        for(int e: r)
            System.out.println(e);
    }
    public static int findWays(int[] num, int tar) {
        // Write your code here..
        int n = num.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][tar + 1];
        for (int[] e: dp)
            Arrays.fill(e, - 1);
        return helper(n - 1, num, tar, dp);
    }
    private static int helper(int i, int[] arr, int tar, int[][] dp) {
        if (tar == 0)
            return 1;
        if (i == 0){
            if (arr[0] == tar)
                return 1;
            else
                return 0;
        }
        if (dp[i][tar] != -1)
            return dp[i][tar];
        int pick = 0;
        if (arr[i] <= tar)
            pick = helper(i - 1, arr, tar - arr[i], dp);
        int notPick = helper(i - 1, arr, tar, dp);
        return dp[i][tar] = pick + notPick;
    }

    private static int tabulation(int[] num, int tar) {
        int n = num.length;
        int[][] dp = new int[n][tar + 1];
        for(int i = 0; i < n; i++)
            dp[i][0] = 1;
        if (num[0] <= tar)
            dp[0][num[0]] = 1;
        for (int i = 1; i < n; i++) {
            for (int t = 1; t <= tar; t++) {
                int pick = 0;
                if (num[i] <= t)
                    pick = dp[i - 1][t - num[i]];
                int notPick = dp[i - 1][t];
                dp[i][t] = pick + notPick;
            }
        }
        return dp[n - 1][tar];
    }

    //All cases not passed
    private static int spaceOptimized(int[] num, int tar) {
        int n = num.length;
        int[] pre = new int[tar + 1];
        int[] cur = new int[tar + 1];
        Arrays.fill(pre, 0);
        pre[0] = 1;
        cur[0] = 1;
        if (num[0] <= tar)
            pre[num[0]] = 1;

        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= tar; t++) {
                int pick = 0;
                if (num[i] <= t)
                    pick = pre[t - num[i]];
                int notPick = pre[t];
                cur[t] = pick + notPick;
            }
            pre = cur;
        }
        return pre[tar];
    }
}
