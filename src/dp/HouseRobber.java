package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HouseRobber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        long[] result = new long[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] valueInHouse = new int[n];
            for (int j = 0; j < n; j++) {
                valueInHouse[j] = scanner.nextInt();
            }
            result[i] = houseRobber(valueInHouse);
        }
        scanner.close();
        for(long e: result)
            System.out.println(e);
    }
    public static long houseRobber(int[] valueInHouse) {
        int n = valueInHouse.length;
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        for (int i = 0; i < n; i++){
            if (i != n - 1)
                arrayList1.add(valueInHouse[i]);
            if (i != 0)
                arrayList2.add(valueInHouse[i]);
        }
        return Math.max(spaceOptimized(arrayList1), spaceOptimized(arrayList2));
//        return Math.max(tabulation(arrayList1), tabulation(arrayList2));
//        long[] dp1 = new long[arrayList1.size()];
//        long[] dp2 = new long[arrayList1.size()];
//        Arrays.fill(dp1, -1);
//        Arrays.fill(dp2, -1);
//        long a = helper(arrayList1.size() - 1, arrayList1, dp1);
//        long b = helper(arrayList2.size() - 1, arrayList2, dp2);
//        return Math.max(a, b);
    }

    private static long helper(int ind, ArrayList<Integer> valueInHouse, long[] dp) {
        if (ind < 0)
            return 0;
        if (ind == 0)
            return valueInHouse.get(0);
        if (dp[ind] != -1)
            return dp[ind];
        long pick = valueInHouse.get(ind) + helper(ind - 2, valueInHouse, dp);
        long notPick = helper(ind - 1, valueInHouse, dp);
        return dp[ind] = Math.max(pick, notPick);
    }

    private static long tabulation(ArrayList<Integer> valueInHouse){
        int n = valueInHouse.size();
        long[] dp = new long[n];
        dp[0] = valueInHouse.get(0);
        for (int i = 1; i < n; i++) {
            long pick = valueInHouse.get(i);
            if (i > 1)
                pick += dp[i - 2];
            long notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }

    private static long spaceOptimized(ArrayList<Integer> valueInHouse){
        int n = valueInHouse.size();
        if (n == 0)
            return 0;
        int pre1 = valueInHouse.get(0), pre2 = 0, curr = 0;
        for (int i = 0; i < n; i++) {
            int pick = valueInHouse.get(i);
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
