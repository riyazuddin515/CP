package dp;

import java.util.Arrays;
import java.util.Scanner;

public class TotalUniquePaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            r[i] = spaceOptimized(m, n);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(m - 1, n - 1, dp);
    }

    private static int helper(int m, int n, int[][] dp) {
        if (m == 0 || n == 0)
            return 1;
        if (dp[m][n] != -1)
            return dp[m][m];
        int left = helper(m, n - 1, dp);
        int up = helper(m - 1, n, dp);
        return dp[m][n] = left + up;
    }

    private static int tabulation(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                dp[i][j] = left + up;
            }
        }
        return dp[m - 1][n - 1];
    }

    private static int spaceOptimized(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            int[] temp = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0)
                    temp[j] = 1;
                else{
                    temp[j] = dp[j];
                    if (j > 0)
                        temp[j] += temp[j - 1];
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }
}
