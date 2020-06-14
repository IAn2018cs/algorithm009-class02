package week04;

//给定一个大小为 n 的数组，找到其 中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
//
//
// 示例 1:
//
// 输入: [3,2,3]
//输出: 3
//
// 示例 2:
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
//
// Related Topics 位运算 数组 分治算法
// https://leetcode-cn.com/problems/majority-element/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    // 暴力求解
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        Map.Entry<Integer, Integer> max = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (max == null || entry.getValue() > max.getValue()) {
                max = entry;
            }
        }
        assert max != null;
        return max.getKey();
    }

    // 找规律
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    // 投票法
    public int majorityElement3(int[] nums) {
        int count = 0;
        Integer result = null;
        for (int num : nums) {
            if (count == 0) {
                result = num;
            }
            count += (num == result) ? 1 : -1;
        }
        assert result != null;
        return result;
    }

    private int getNumCount(int[] nums, int num) {
        int count = 0;
        for (int i : nums) if (i == num) count++;
        return count;
    }

    private int divideConquer(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = (right - left) / 2 + left;
        int resultLeft = divideConquer(nums, left, mid);
        int resultRight = divideConquer(nums, mid + 1, right);

        if (resultLeft == resultRight) {
            return resultLeft;
        }

        int countLeft = getNumCount(nums, resultLeft);
        int countRight = getNumCount(nums, resultRight);

        return countLeft > countRight ? resultLeft : resultRight;
    }

    // 分治
    public int majorityElement4(int[] nums) {
        return divideConquer(nums, 0, nums.length - 1);
    }
}
