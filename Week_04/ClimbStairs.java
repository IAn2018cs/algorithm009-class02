package week04;

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// https://leetcode-cn.com/problems/climbing-stairs/

import java.util.HashMap;

public class ClimbStairs {

    private HashMap<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int step1;
        int step2;
        if (map.containsKey(n - 1)) {
            step1 = map.get(n - 1);
        } else {
            step1 = climbStairs(n - 1);
            map.put(n - 1, step1);
        }
        if (map.containsKey(n - 2)) {
            step2 = map.get(n - 2);
        } else {
            step2 = climbStairs(n - 2);
            map.put(n - 2, step2);
        }
        return step1 + step2;
    }

    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int f = 1;
        int s = 2;
        int step = 0;
        for (int i = 3; i <= n; i++) {
            step = f + s;
            f = s;
            s = step;
        }
        return step;
    }
}
