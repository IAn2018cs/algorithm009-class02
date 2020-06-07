package week03;

//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
//
//
//
// 示例 1：
//
// 输入：s = "We are happy."
//输出："We%20are%20happy."
//
//
//
// 限制：
//
// 0 <= s 的长度 <= 10000
// https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/

public class ReplaceSpace {

    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public String replaceSpace2(String s) {
        char[] result = new char[s.length() * 3];
        int i = 0;
        for (char chars : s.toCharArray()) {
            if (chars == ' ') {
                result[i++] = '%';
                result[i++] = '2';
                result[i++] = '0';
            } else {
                result[i++] = chars;
            }
        }
        return new String(result, 0, i);
    }
}
