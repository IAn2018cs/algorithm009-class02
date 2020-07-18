package week09;

import java.util.Arrays;

/**
 * 爬楼梯 动态规划
 * f(n) = f(n - 1) + f(n - 2)
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        return f(n);
    }

    private int f(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int i = 2; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
