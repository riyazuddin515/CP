package revision.dp;

import java.util.Arrays;
import java.util.Scanner;

public class FrogJump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] heights = new int[n];
            for (int j = 0; j < n; j++) {
                heights[j] = scanner.nextInt();
            }
            r[i] = spaceOptimized(n, heights);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int frogJump(int n, int[] heights) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(n - 1, heights, dp);
    }

    private static int helper(int ind, int[] heights, int[] dp) {
        if (ind == 0)
            return 0;
        if (dp[ind] != -1)
            return dp[ind];
        int oneJump = Math.abs(heights[ind] - heights[ind - 1]) + helper(ind - 1, heights, dp);
        int twoJumps = Integer.MAX_VALUE;
        if (ind > 1)
            twoJumps = Math.abs(heights[ind] - heights[ind - 2]) + helper(ind - 2, heights, dp);
        return dp[ind] = Math.min(oneJump, twoJumps);
    }

    private static int tabulation(int n, int[] heights) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int ind = 1; ind < n; ind++) {
            int oneJump = Math.abs(heights[ind] - heights[ind - 1]) + dp[ind - 1];
            int twoJumps = Integer.MAX_VALUE;
            if (ind > 1)
                twoJumps = Math.abs(heights[ind] - heights[ind - 2]) + dp[ind - 2];
            dp[ind] = Math.min(oneJump, twoJumps);
        }
        return dp[n - 1];
    }

    private static int spaceOptimized(int n, int[] heights) {
        int pre1 = 0, pre2 = 0, cur = 0;
        for (int ind = 1; ind < n; ind++) {
            int oneJump = Math.abs(heights[ind] - heights[ind - 1]) + pre1;
            int twoJumps = Integer.MAX_VALUE;
            if (ind > 1)
                twoJumps = Math.abs(heights[ind] - heights[ind - 2]) + pre2;
            cur = Math.min(oneJump, twoJumps);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
