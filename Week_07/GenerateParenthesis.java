package week07;

//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
//
//
//
// 示例：
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
//
// Related Topics 字符串 回溯算法
// https://leetcode-cn.com/problems/generate-parentheses/

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) return result;
        dfs("", 0, 0, n, result);
        return result;
    }

    private void dfs(String cur, int left, int right, int n, List<String> result) {
        if (left == n && right == n) {
            result.add(cur);
            return;
        }
        if (left < right) return;
        if (left < n) dfs(cur + "(", left + 1, right, n, result);
        if (right < n) dfs(cur + ")", left, right + 1, n, result);
    }
}
