package week07;

//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
//
// 示例:
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");
//trie.search("app");     // 返回 true
//
// 说明:
//
//
// 你可以假设所有的输入都是由小写字母 a-z 构成的。
// 保证所有输入均为非空字符串。
//
// Related Topics 设计 字典树
// https://leetcode-cn.com/problems/implement-trie-prefix-tree/

import week06.FindWords;

import java.util.HashMap;

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

public class Trie {

    String word = null;
    HashMap<Character, Trie> child = new HashMap<>();

    private Trie root;

    /** Initialize your data structure here. */
    public Trie() {
        root = this;
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (node.child.containsKey(c)) {
                node = node.child.get(c);
            } else {
                Trie newNode = new Trie();
                node.child.put(c, newNode);
                node = newNode;
            }
        }
        node.word = word;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = root;
        for (char c : word.toCharArray()) {
            if (!node.child.containsKey(c)) {
                return false;
            }
            node = node.child.get(c);
        }
        return node.word != null;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.child.containsKey(c)) {
                return false;
            }
            node = node.child.get(c);
        }
        return true;
    }
}
