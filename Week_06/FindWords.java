package week06;

//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
//
//
// 示例:
//
// 输入:
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"]
//
// 说明:
//你可以假设所有输入都由小写字母 a-z 组成。
//
// 提示:
//
//
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
//
// Related Topics 字典树 回溯算法
// https://leetcode-cn.com/problems/word-search-ii/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindWords {

    public static class TrieNode {
        HashMap<Character, TrieNode> child = new HashMap<>();
        String word = null;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        // 构建前缀树
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.child.containsKey(c)) {
                    node = node.child.get(c);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.child.put(c, newNode);
                    node = newNode;
                }
            }
            node.word = word;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (root.child.containsKey(board[i][j])) {
                    dfs(board, i, j, root, result);
                }
            }
        }

        return result;
    }

    private void dfs(char[][] board, int x, int y, TrieNode root, List<String> result) {
        Character c = board[x][y];
        TrieNode curr = root.child.get(c);
        if (curr.word != null) {
            result.add(curr.word);
            curr.word = null;
        }
        board[x][y] = '#';
        int[] xDir = {-1, 0, 1, 0};
        int[] yDir = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int newX = x + xDir[i];
            int newY = y + yDir[i];
            if (newX < 0 || newX >= board.length || newY < 0 || newY >= board[0].length) {
                continue;
            }
            if (curr.child.containsKey(board[newX][newY])) {
                dfs(board, newX, newY, curr, result);
            }
        }
        board[x][y] = c;
        if (curr.child.isEmpty()) {
            root.child.remove(c);
        }
    }
}
