package week07;

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

import java.util.*;

public class LadderLength {

    public class Pair<K, V> {
        K k;
        V v;

        Pair(K k, V v) {
            this.k = k;
            this.v = v;
        }

        K getKey() {
            return k;
        }

        V getValue() {
            return v;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        // 罗列所有可能
        HashMap<String, ArrayList<String>> allWord = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                // *ot h*t ho*
                String wildcard = word.substring(0, i) + "*" + word.substring(i + 1);
                ArrayList<String> wildcards = allWord.getOrDefault(wildcard, new ArrayList<>());
                wildcards.add(word);
                allWord.put(wildcard, wildcards);
            }
        }
        // 广度优先搜索
        LinkedList<Pair<String, Integer>> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.addLast(new Pair<>(beginWord, 1));
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.removeFirst();
            String currWord = node.getKey();
            int level = node.getValue();
            // 对比单词
            for (int i = 0; i < currWord.length(); i++) {
                String wildcard = currWord.substring(0, i) + "*" + currWord.substring(i + 1);
                ArrayList<String> wildcards = allWord.getOrDefault(wildcard, new ArrayList<>());
                for (String word : wildcards) {
                    if (endWord.equals(word)) {
                        return level + 1;
                    }
                    if (!visited.contains(word)) {
                        queue.addLast(new Pair<>(word, level + 1));
                        visited.add(word);
                    }
                }
            }
        }
        return 0;
    }
}
