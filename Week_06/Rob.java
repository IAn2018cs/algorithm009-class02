package week06;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋
//装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
//
// 示例 1:
//
// 输入: [2,3,2]
//输出: 3
//解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2:
//
// 输入: [1,2,3,1]
//输出: 4
//解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
// Related Topics 动态规划
// https://leetcode-cn.com/problems/house-robber-ii/description/

public class Rob {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        // 偷第一家不偷最后一家 和 不偷第一家偷最后一家
        return Math.max(robRange(nums, 0, n - 2), robRange(nums, 1, n - 1));
    }

    private int robRange(int[] nums, int start, int end) {
        int dpi = 0, dpi1 = 0, dpi2 = 0;
        for (int i = end; i >= start; i--) {
            // 不抢下一家 和 抢下一家
            dpi = Math.max(dpi1, nums[i] + dpi2);
            dpi2 = dpi1;
            dpi1 = dpi;
        }
        return dpi;
    }
}