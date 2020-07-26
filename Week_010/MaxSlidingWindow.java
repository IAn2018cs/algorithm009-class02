package week10;

//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
//
//
// 返回滑动窗口中的最大值。
//
//
//
// 进阶：
//
// 你能在线性时间复杂度内解决此题吗？
//
//
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10^5
// -10^4 <= nums[i] <= 10^4
// 1 <= k <= nums.length
//
// Related Topics 堆 Sliding Window
// 👍 466 👎 0
// https://leetcode-cn.com/problems/sliding-window-maximum/

import java.util.ArrayDeque;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] out = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            clearDeque(deque, nums, i, k);
            int index = i - k + 1;
            // 如果下标小于0 说明队列还没初始化完
            if (index < 0) {
                continue;
            }
            // 最大值下标为队首元素
            out[index] = nums[deque.getFirst()];
        }
        return out;
    }

    private void clearDeque(ArrayDeque<Integer> deque, int[] nums, int i, int k) {
        // 每次移除第一个元素
        if (!deque.isEmpty() && deque.peekFirst() == i - k) {
            deque.pollFirst();
        }
        // 移除比当前小的元素
        while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
            deque.pollLast();
        }
        deque.addLast(i);
    }
}
