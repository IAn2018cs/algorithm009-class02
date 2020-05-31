package week02;

//统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
//
// 请注意，你可以假定字符串里不包括任何不可打印的字符。
//
// 示例:
//
// 输入: "Hello, my name is John"
//输出: 5
//解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
//
// Related Topics 字符串
// https://leetcode-cn.com/problems/number-of-segments-in-a-string/

public class CountSegments {
    public int countSegments(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        String[] array = s.split(" ");
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            String str = array[i];
            if (str.isEmpty()) {
                continue;
            }
            count++;
        }
        return count;
    }
}
