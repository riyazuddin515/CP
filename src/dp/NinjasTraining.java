package dp;

import java.util.Arrays;
import java.util.Scanner;

public class NinjasTraining {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[][] points = new int[n][3];
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    points[j][k] = scanner.nextInt();
                }
            }
            result[i] = tabulation(n, points);
        }
        System.out.println();
        for(int e: result)
            System.out.println(e);
    }
    private static int ninjaTraining(int n, int[][] points) {
        if (n == 0)
            return 0;
        int[][] dp = new int[points.length][points[0].length];
        for(int[] e: dp)
            Arrays.fill(e, -1);
        return helper(-1, 0, points, dp);
    }

    private static int helper(int ind, int day, int[][] points, int[][] dp){
        if (day == points.length) {
            return 0;
        }
        if (ind != -1 && dp[day][ind] != -1)
            return dp[day][ind];
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < points[0].length; i++){
            if (i != ind) {
                int a = helper(i, day + 1, points, dp) + points[day][i];
                max = Math.max(max, a);
            }
        }
        if (ind == -1)
            return dp[day][0] = max;
        return dp[day][ind] = max;
    }

    //Not working
    private static int tabulation(int n, int[][] points) {
        int[][] dp = new int[points.length][points[0].length];
        for(int[] e: dp)
            Arrays.fill(e, 0);
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        for (int i = 1; i < points.length; i++) {
            for(int l = 0; l < 3; l++){
                for (int j = 0; j < 3; j++) {
                    if (l != j) {
                        dp[i][l] = Math.max(points[i][j]+dp[i-1][j], dp[i][l]);
                    }
                }
            }
        }
        return dp[n - 1][2];
    }
}