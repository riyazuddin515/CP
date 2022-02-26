package dp;

import java.util.Arrays;
import java.util.Scanner;

public class MinimumPathSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] grid = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    grid[j][k] = scanner.nextInt();
                }
            }
            r[i] = tabulation(grid);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int minSumPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, m - 1, grid, dp);
    }

    private static int helper(int n, int m, int[][] grid, int[][] dp) {
        if (n == 0 && m == 0)
            return grid[n][m];
        if (n < 0 || m < 0)
            return (int) Math.pow(10, 9);
        if (dp[n][m] != -1)
            return dp[n][m];
        int left = grid[n][m] + helper(n, m - 1, grid, dp);
        int up = grid[n][m] + helper(n - 1, m, grid, dp);
        return dp[n][m] = Math.min(left, up);
    }

    private static int tabulation(int[][] grid){
        int n = grid.length;
        int m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0)
                    dp[i][j] = grid[i][j];
                else {
                    int left = (int) Math.pow(10, 9), up = (int) Math.pow(10, 9);
                    if (j > 0)
                        left = grid[i][j] + dp[i][j - 1];
                    if (i > 0)
                        up =grid[i][j] + dp[i - 1][j];
                    dp[i][j] = Math.min(left, up);
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
