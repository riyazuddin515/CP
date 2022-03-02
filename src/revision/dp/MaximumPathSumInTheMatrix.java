package revision.dp;

import java.lang.reflect.Array;
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
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[n][m];
        for(int[] e: dp)
            Arrays.fill(e, -1);
        for (int i = 0; i < m; i++) {
            max = Math.max(max, helper(n - 1, i, matrix, dp));
        }
        return max;
    }
    private static int helper(int i, int j, int[][] matrix, int[][] dp) {
        if (j < 0 || j == matrix[0].length)
            return -(int) Math.pow(10, 9);
        if (i == 0)
            return matrix[0][j];
        if (dp[i][j] != -1)
            return dp[i][j];
        int up = matrix[i][j] + helper(i - 1, j, matrix, dp);
        int upLeft = matrix[i][j] + helper(i - 1, j - 1, matrix, dp);
        int upRight = matrix[i][j] + helper(i - 1, j + 1, matrix, dp);
        return dp[i][j] = Math.max(up, Math.max(upLeft, upRight));
    }

}
