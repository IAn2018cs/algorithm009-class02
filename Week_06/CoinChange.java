package week06;

//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。
//
//
//
// 示例 1:
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3
//解释: 11 = 5 + 5 + 1
//
// 示例 2:
//
// 输入: coins = [2], amount = 3
//输出: -1
//
//
//
// 说明:
//你可以认为每种硬币的数量是无限的。
// Related Topics 动态规划
// https://leetcode-cn.com/problems/coin-change/

import java.util.HashMap;

public class CoinChange {

    private HashMap<Integer, Integer> map = new HashMap<>();

    public int coinChange(int[] coins, int amount) {
        return dp(coins, amount);
    }

    private int dp(int[] coins, int n) {
        if (map.containsKey(n)) return map.get(n);
        if (n == 0) return 0;
        if (n < 0) return -1;
        int result = Integer.MAX_VALUE;
        for (int coin : coins) {
            int sub = dp(coins, n - coin);
            if (sub == -1) continue;
            result = Math.min(result, sub + 1);
        }
        map.put(n, result == Integer.MAX_VALUE ? -1 : result);
        return map.get(n);
    }
}
