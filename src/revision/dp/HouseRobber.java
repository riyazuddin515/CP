package revision.dp;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseRobber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        long[] r = new long[t];
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            int[] valueInHouse = new int[n];
            for (int j = 0; j < n; j++) {
                valueInHouse[j] = scanner.nextInt();
            }
            r[i] = houseRobber(valueInHouse);
        }
        scanner.close();
        for (long e : r)
            System.out.println(e);
    }
    public static long houseRobber(int[] valueInHouse) {
        // Write your code here
        int n = valueInHouse.length;
        if (n == 1)
            return valueInHouse[0];
        long first = spaceOptimized(valueInHouse, 0, n - 1);
        long second = spaceOptimized(valueInHouse, 1, n);
        return Math.max(first, second);
    }
    private static long spaceOptimized(int[] valueInHouse, int s, int e) {
        if (e - s == 1) {
            if (s == 0)
                return valueInHouse[0];
            else
                return valueInHouse[1];
        }
        long pre1 = valueInHouse[s], pre2 = 0, cur = 0;
        for (int ind = s + 1; ind < e; ind++) {
            long pick = valueInHouse[ind] + pre2;
            long notPick = pre1;
            cur = Math.max(pick, notPick);
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }
}
