package dp;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumElements {
    private static final int MAX = (int) Math.pow(10, 9);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int[] num = new int[n];
            for (int j = 0; j < n; j++) {
                num[j] = scanner.nextInt();
            }
            r[i] = spaceOptimized(num, x);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    public static int minimumElements(int[] num, int x) {
        // Write your code here..
        int n = num.length;
        if (n == 0)
            return 0;
        int[][] dp = new int[n][x + 1];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        int ans = helper(n - 1, num, x, dp);
        if (ans >= MAX)
            return -1;
        return ans;
    }

    private static int helper(int i, int[] num, int x, int[][] dp) {
        if (i == 0) {
            if (x % num[i] == 0)
                return x / num[i];
            else
                return MAX;
        }
        if (dp[i][x] != -1)
            return dp[i][x];
        int notPick = helper(i - 1, num, x, dp);
        int pick = Integer.MAX_VALUE;
        if (num[i] <= x)
            pick = 1 + helper(i, num, x - num[i], dp);
        return dp[i][x] = Math.min(pick, notPick);
    }

    private static int tabulation(int[] num, int x) {
        int n = num.length;
        int[][] dp = new int[n][x + 1];
        for (int i = 0; i <= x; i++) {
            if (i % num[0] == 0)
                dp[0][i] = i / num[0];
            else
                dp[0][i] = MAX;
        }
        for (int i = 1; i < n; i++) {
            for (int x1 = 0; x1 <= x; x1++) {
                int notPick = dp[i - 1][x1];
                int pick = Integer.MAX_VALUE;
                if (num[i] <= x1)
                    pick = 1 + dp[i][x1 - num[i]];
                dp[i][x1] = Math.min(pick, notPick);
            }
        }
        if (dp[n - 1][x] >= MAX)
            return -1;
        return dp[n - 1][x];
    }

    // Two arrays of X + 1 length
    private static int spaceOptimized(int[] num, int x) {
        int n = num.length;
        int[] pre = new int[x + 1];
        int[] cur = new int[x + 1];
        for (int i = 0; i <= x; i++) {
            if (i % num[0] == 0)
                pre[i] = i / num[0];
            else
                pre[i] = MAX;
        }
        for (int i = 1; i < n; i++) {
            for (int x1 = 0; x1 <= x; x1++) {
                int notPick = pre[x1];
                int pick = Integer.MAX_VALUE;
                if (num[i] <= x1)
                    pick = 1 + pre[x1 - num[i]];
                cur[x1] = Math.min(pick, notPick);
            }
            pre = cur;
        }
        if (pre[x] >= MAX)
            return -1;
        return pre[x];
    }
}
