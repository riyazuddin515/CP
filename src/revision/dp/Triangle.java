package revision.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[][] triangle = new int[n][n];
            for (int j = 0; j < n; j++) {
                int[] e = new int[j + 1];
                for (int k = 0; k <= j; k++) {
                    e[k] = scanner.nextInt();
                }
                triangle[j] = e;
            }
            r[i] = tabulation(triangle, n);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int minimumPathSum(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(0, 0, triangle, dp);
    }

    private static int helper(int i, int j, int[][] triangle, int[][] dp) {
        if (i == triangle.length - 1)
            return triangle[i][j];
        if (dp[i][j] != -1)
            return dp[i][j];
        int down = triangle[i][j] + helper(i + 1, j, triangle, dp);
        int downLeft = triangle[i][j] + helper(i + 1, j + 1, triangle, dp);
        return dp[i][j] = Math.min(down, downLeft);
    }

    private static int tabulation(int[][] triangle, int n) {
        int[][] dp = new int[n][n];
        System.arraycopy(triangle[n - 1], 0, dp[n - 1], 0, triangle[n - 1].length);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int down = triangle[i][j] + dp[i + 1][j];
                int downLeft = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(down, downLeft);
            }
        }
        return dp[0][0];
    }
}
