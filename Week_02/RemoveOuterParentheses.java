package week02;

import java.util.Stack;

//有效括号字符串为空 ("")、"(" + A + ")" 或 A + B，其中 A 和 B 都是有效的括号字符串，+ 代表字符串的连接。例如，""，"()"
//，"(())()" 和 "(()(()))" 都是有效的括号字符串。
//
// 如果有效字符串 S 非空，且不存在将其拆分为 S = A+B 的方法，我们称其为原语（primitive），其中 A 和 B 都是非空有效括号字符串。
//
// 给出一个非空有效字符串 S，考虑将其进行原语化分解，使得：S = P_1 + P_2 + ... + P_k，其中 P_i 是有效括号字符串原语。
//
// 对 S 进行原语化分解，删除分解中每个原语字符串的最外层括号，返回 S 。
//
//
//
// 示例 1：
//
// 输入："(()())(())"
//输出："()()()"
//解释：
//输入字符串为 "(()())(())"，原语化分解得到 "(()())" + "(())"，
//删除每个部分中的最外层括号后得到 "()()" + "()" = "()()()"。
//
// 示例 2：
//
// 输入："(()())(())(()(()))"
//输出："()()()()(())"
//解释：
//输入字符串为 "(()())(())(()(()))"，原语化分解得到 "(()())" + "(())" + "(()(()))"，
//删除每个部分中的最外层括号后得到 "()()" + "()" + "()(())" = "()()()()(())"。
//
//
// 示例 3：
//
// 输入："()()"
//输出：""
//解释：
//输入字符串为 "()()"，原语化分解得到 "()" + "()"，
//删除每个部分中的最外层括号后得到 "" + "" = ""。
//
//
//
//
// 提示：
//
//
// S.length <= 10000
// S[i] 为 "(" 或 ")"
// S 是一个有效括号字符串
//
// Related Topics 栈
// https://leetcode-cn.com/problems/remove-outermost-parentheses/

public class RemoveOuterParentheses {
    public String removeOuterParentheses(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                if (stack.isEmpty()) {
                    start = i;
                }
                stack.push(S.charAt(i));
            }
            if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    builder.append(S, start + 1, i);
                }
            }
        }
        return builder.toString();
    }

    public String removeOuterParentheses2(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int count = 0;
        int start = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                if (count++ == 0) {
                    start = i;
                }
            }
            if (c == ')') {
                if (--count == 0) {
                    builder.append(S, start + 1, i);
                }
            }
        }
        return builder.toString();
    }

    public String removeOuterParentheses3(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int level = 0;
        for (char c : S.toCharArray()) {
            if (c == ')') --level;
            if (level > 0) builder.append(c);
            if (c == '(') ++level;
        }
        return builder.toString();
    }

    public String removeOuterParentheses4(String S) {
        if (S == null || S.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        int opened = 0;
        for (char c : S.toCharArray()) {
            if (c == '(' && opened++ > 0) builder.append(c);
            if (c == ')' && opened-- > 1) builder.append(c);
        }
        return builder.toString();
    }
}