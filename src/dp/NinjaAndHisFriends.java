package dp;

import java.util.Arrays;
import java.util.Scanner;

public class NinjaAndHisFriends {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            int[][] grid = new int[r][c];
            for (int j = 0; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    grid[j][k] = scanner.nextInt();
                }
            }
            result[i] = tabulation(grid, r, c);
        }
        scanner.close();
        for (int e : result)
            System.out.println(e);
    }

    private static int maximumChocolates(int[][] grid, int r, int c) {
        // Write your code here.
        int[][][] dp = new int[r][c][c];
        for (int[][] e : dp)
            for (int[] e1 : e)
                Arrays.fill(e1, -1);
        return helper(0, 0, c - 1, grid, r, c, dp);
    }

    private static int helper(int ab, int ac, int bc, int[][] grid, int r, int c, int[][][] dp) {
        if (ac < 0 || bc < 0 || ac == c || bc == c)
            return -(int) Math.pow(10, 8);
        if (ab == r - 1) {
            //check if both of them on same cell
            if (ac == bc)
                return grid[ab][ac];
            else
                return grid[ab][ac] + grid[ab][bc];
        }
        if (dp[ab][ac][bc] != -1)
            return dp[ab][ac][bc];
        int max = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int value;
                if (ac == bc)
                    value = grid[ab][ac];
                else
                    value = grid[ab][ac] + grid[ab][bc];
                value += helper(ab + 1, ac + i, bc + j, grid, r, c, dp);
                max = Math.max(max, value);
            }
        }
        return dp[ab][ac][bc] = max;
    }

    //Not working
    private static int tabulation(int[][] grid, int r, int c) {
        int[][][] dp = new int[r][c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j)
                    dp[r - 1][i][j] = grid[r - 1][i];
                else
                    dp[r - 1][i][j] = grid[r - 1][i] + grid[r - 1][j];
            }
        }
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int max = -(int) Math.pow(10, 8);
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            int value;
                            if (j1 == j2)
                                value = grid[i][j1];
                            else
                                value = grid[i][j1] + grid[i][j2];
                            if (j1 + k >= 0 && j1 + k < r && j2 + l >= 0 && j2 + l < c)
                                value += dp[i + 1][j1 + k][j2 + l];
                            else
                                value += -(int) Math.pow(10, 8);
                            max = Math.max(max, value);
                        }
                    }
                    dp[i][j1][j2] = max;
                }
            }
        }
        return dp[0][0][c - 1];
    }
}
