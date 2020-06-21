package week05;

//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
//
//
//
// 每次转换只能改变一个字母。
// 转换过程中的中间单词必须是字典中的单词。
//
//
// 说明:
//
//
// 如果不存在这样的转换序列，返回 0。
// 所有单词具有相同的长度。
// 所有单词只由小写字母组成。
// 字典中不存在重复的单词。
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
//
//
// 示例 1:
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
//
//
// 示例 2:
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。
// Related Topics 广度优先搜索
// https://leetcode-cn.com/problems/word-ladder/description/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import javafx.util.Pair;

public class LadderLength {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        int length = beginWord.length();
        // 预处理
        HashMap<String, ArrayList<String>> allWord = new HashMap<>();
        for (String s : wordList) {
            for (int i = 0; i < length; i++) {
                String word = s.substring(0, i) + "*" + s.substring(i + 1, length);
                ArrayList<String> strings = allWord.getOrDefault(word, new ArrayList<>());
                strings.add(s);
                allWord.put(word, strings);
            }
        }
        // 广度优先搜索
        LinkedList<Pair<String, Integer>> queue = new LinkedList<>();
        HashSet<String> visted = new HashSet<>();
        queue.addLast(new Pair<>(beginWord, 1));
        visted.add(beginWord);
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.removeFirst();
            String currWord = node.getKey();
            int level = node.getValue();
            // 比较单词
            for (int i = 0; i < length; i++) {
                String word = currWord.substring(0, i) + "*" + currWord.substring(i + 1, length);
                ArrayList<String> strings = allWord.getOrDefault(word, new ArrayList<>());
                for (String s : strings) {
                    // 匹配到最后单词
                    if (endWord.equals(s)) {
                        return level + 1;
                    }
                    // 没有访问的
                    if (!visted.contains(s)) {
                        queue.addLast(new Pair<>(s, level + 1));
                        visted.add(s);
                    }
                }
            }
        }
        return 0;
    }
}
