package dp;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumPathSumInTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[][] matrix = new int[n][m];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    matrix[j][k] = scanner.nextInt();
                }
            }
            r[i] = getMaxPathSum(matrix);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int getMaxPathSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        for(int[] e: dp)
            Arrays.fill(e, -1);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, helper(matrix, 0, i, dp));
        }
        return max;
    }

    private static int helper(int[][] matrix, int n, int m, int[][] dp) {
        if (m < 0 || m == matrix[0].length)
            return - (int) Math.pow(10, 9);
        if (n == matrix.length - 1)
            return matrix[n][m];
        if (dp[n][m] != -1)
            return dp[n][m];
        int down = matrix[n][m] + helper(matrix, n + 1, m, dp);
        int leftDig = matrix[n][m] + helper(matrix, n + 1, m - 1, dp);
        int rightDig = matrix[n][m] + helper(matrix, n + 1, m + 1, dp);
        return dp[n][m] = Math.max(down, Math.max(leftDig, rightDig));
    }
}
