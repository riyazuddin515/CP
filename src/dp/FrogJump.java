package dp;

import java.util.Scanner;

public class FrogJump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        scanner.close();
        System.out.println(frogJump(n - 1, heights));
//        int[] dp = new int[n];
//        Arrays.fill(dp, -1);
//        System.out.println(frogJump(n - 1, heights, dp));
    }

    private static int frogJump(int n, int[] heights) {
        int pre1 = 0, pre2 = 0, cur = 0;
        for (int i = 1; i < n; i++) {
            int o = pre1 + Math.abs(heights[i] - heights[i - 1]);
            int t = Integer.MAX_VALUE;
            if (i > 1)
                t = pre2 + Math.abs(heights[i] - heights[i - 2]);
            cur = Math.min(o, t);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }

    private static int frogJumpTabulation(int n, int[] heights) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int o = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int t = Integer.MAX_VALUE;
            if (i > 1)
                t = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            dp[i] = Math.min(o, t);
        }
        return dp[n - 1];
    }

    private static int frogJump(int n, int[] heights, int[] dp) {
        if (n == 0)
            return 0;
        if (dp[n] != -1)
            return dp[n];
        int o = frogJump(n - 1, heights, dp) + Math.abs(heights[n] - heights[n - 1]);
        int t = Integer.MAX_VALUE;
        if (n > 1)
            t = frogJump(n - 2, heights, dp) + Math.abs(heights[n] - heights[n - 2]);
        return dp[n] = Math.min(o, t);
    }
}
