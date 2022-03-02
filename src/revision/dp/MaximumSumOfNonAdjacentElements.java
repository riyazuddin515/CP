package revision.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOfNonAdjacentElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] r = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            ArrayList<Integer> nums = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                nums.add(scanner.nextInt());
            }
            r[i] = spaceOptimized(nums);
        }
        scanner.close();
        for(int e: r)
            System.out.println(e);
    }

    public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
        int n = nums.size();
        if (nums.size() == 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(n - 1, nums, dp);
    }

    private static int helper(int ind, ArrayList<Integer> nums, int[] dp) {
        if (ind == 0)
            return nums.get(0);
        if (dp[ind] != -1)
            return dp[ind];
        int pick = nums.get(ind);
        if (ind > 1)
            pick += helper(ind - 2, nums, dp);
        int notPick = helper(ind - 1, nums, dp);
        return dp[ind] = Math.max(pick, notPick);
    }

    private static int tabulation(ArrayList<Integer> nums) {
        int n = nums.size();
        int[] dp = new int[n];
        dp[0] = nums.get(0);
        for (int ind = 1; ind < n; ind++) {
            int pick = nums.get(ind);
            if (ind > 1)
                pick += dp[ind - 2];
            int notPick = dp[ind - 1];
            dp[ind] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }

    private static int spaceOptimized(ArrayList<Integer> nums) {
        int n = nums.size();
        if (n == 1)
            return nums.get(0);
        int pre1 = nums.get(0), pre2 = 0, cur = 0;
        for (int ind = 1; ind < n; ind++) {
            int pick = nums.get(ind) + pre2;
            int notPick = pre1;
            cur = Math.max(pick, notPick);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
