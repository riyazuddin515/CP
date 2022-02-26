package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOfNonAdjacentElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        int[] result = new int[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            ArrayList<Integer> nums = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                nums.add(scanner.nextInt());
            }
            result[i] = spaceOptimised(nums);
        }
        scanner.close();
        for(int e: result)
            System.out.println(e);
    }
    private static int maximumNonAdjacentSum(ArrayList<Integer> nums){
        int n = nums.size();
        if (n == 0)
            return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return helper(n - 1, nums, dp);
    }

    private static int helper(int ind, ArrayList<Integer> nums, int[] dp){
        if (ind < 0)
            return 0;
        if (ind == 0)
            return nums.get(0);
        if (dp[ind] != -1)
            return dp[ind];
        int pick = nums.get(ind) + helper(ind - 2, nums, dp);
        int notPick = helper(ind - 1, nums, dp);
        return dp[ind] = Math.max(pick, notPick);
    }

    private static int tabulation(ArrayList<Integer> nums) {
        int n = nums.size();
        int[] dp = new int[n];
        dp[0] = nums.get(0);
        for (int i = 1; i < n; i++){
            int pick = nums.get(i);
            if (i > 1)
                pick += dp[i - 2];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }

    private static int spaceOptimised(ArrayList<Integer> nums) {
        int pre1 = nums.get(0), pre2 = 0, curr = 0;
        for (int i = 1; i < nums.size(); i++) {
            int pick = nums.get(i);
            if (i > 1)
                pick += pre2;
            int notPick = pre1;
            curr = Math.max(pick, notPick);
            pre2 = pre1;
            pre1 = curr;
        }
        return curr;
    }
}
