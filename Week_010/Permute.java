package week10;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 795 👎 0
// https://leetcode-cn.com/problems/permutations/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 初始化数值
        List<Integer> out = new ArrayList<>();
        for (int num : nums) {
            out.add(num);
        }
        back(0, nums.length, out, result);
        return result;
    }

    private void back(int first, int n, List<Integer> out, List<List<Integer>> result) {
        if (first == n) {
            result.add(new ArrayList<>(out));
        }
        for (int i = first; i < n; i++) {
            // 交换
            Collections.swap(out, first, i);
            // 递归
            back(first + 1, n, out, result);
            // 回溯
            Collections.swap(out, first, i);
        }
    }
}
