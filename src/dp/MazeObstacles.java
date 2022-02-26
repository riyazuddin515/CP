package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeObstacles {
    static int mod = (int) (Math.pow(10, 9) + 7);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int k = 0; k < m; k++) {
                    temp.add(scanner.nextInt());
                }
                mat.add(temp);
            }
            r[i] = tabulation(n, m, mat);
        }
        scanner.close();
        for (int e : r)
            System.out.println(e);
    }

    private static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp = new int[n][m];
        for (int[] e : dp)
            Arrays.fill(e, -1);
        return helper(n - 1, m - 1, mat, dp);
    }

    private static int helper(int n, int m, ArrayList<ArrayList<Integer>> mat, int[][] dp) {
        if (n == 0 && m == 0)
            return 1;
        if (n < 0 || m < 0)
            return 0;
        if (dp[n][m] != -1)
            return dp[n][m];
        if (mat.get(n).get(m) == -1)
            return 0;
        int left = helper(n, m - 1, mat, dp);
        int up = helper(n - 1, m, mat, dp);
        return dp[n][m] = left + up;
    }

    private static int tabulation(int n, int m, ArrayList<ArrayList<Integer>> mat) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat.get(i).get(j) == -1)
                    dp[i][j] = 0;
                else if (i == 0 && j == 0)
                    dp[i][j] = 1;
                else{
                    int left = 0, up = 0;
                    if (j > 0)
                        left = dp[i][j - 1];
                    if (i > 0)
                        up = dp[i - 1][j];
                    dp[i][j] = (left + up) % mod;
                }
            }
        }
        return dp[n - 1][m - 1];
    }
}
