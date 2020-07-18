package week09;

//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
//
//
//
// 示例：
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
//
//
//
//
// 提示：你可以假定该字符串只包含小写字母。
// Related Topics 哈希表 字符串
// 👍 233 👎 0
// https://leetcode-cn.com/problems/first-unique-character-in-a-string/

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        int[] letter = new int[26];
        for (char c : s.toCharArray()) {
            letter[c - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (letter[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }
}
