# Week07 学习笔记

## 1. 字典树和并查集

### 1.1 字典树 Trie

#### 1.1.1 基本结构

字典树又称为单词查找树或键树，是一种树形结构。

常应用于统计和排序大量的字符串（但不仅限于字符串），经常被搜索引擎系统用于文本词频统计。

字典树的优点是：最大限度地减少无谓的字符串比较，查询效率比hash表高。

![Trie的结构](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/trie-结构.png)

#### 1.1.2 基本性质

* 结点本身不存完整的单词。
* 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串。

* 每个结点的所有子结点路径，代表的字符串都不相同。

![Trie的结点内部实现](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/trie-内部实现.png)

#### 1.1.3 核心思想

* Trie树的核心思想是空间换时间。
* 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。

#### 1.1.4 实现一个Trie

https://leetcode-cn.com/problems/implement-trie-prefix-tree/

```java
class Trie {
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
```

### 2. 并查集 Disjoint Set

并查集解决的场景就是组团和配对问题。

比如在有些现实的问题中，需要很快地判断这两个个体是不是在一个集合当中，如微信的朋友圈功能。

#### 2.1 基本操作

* makeSet(s)：建立一个新的并查集，其中包含s个单元素集合。
* unionSet(x, y)：把元素x和元素y所在的集合合并，要求x和y所在的集合不相交，如果相交则不合并。
* find(x)：找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。

#### 2.2 初始化

![并查集的初始结构](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/并查集结构.png)

如上图，一开始每一个元素都有一个parent数组指向自己，表示自己就是自己的集合。

#### 2.3 查询、合并

![并查集的查询、合并](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/并查集合并查询.png)

* 查询就是一直向上看它的parent，直到它的parent等于它自己的时候，就找到了它的集合代表元素。
* 合并就是先找出一个集合的代表元素a，和另外一个集合的代表元素e，然后将parent[e]指向a，或者parent[a]指向e。

#### 2.4 路径压缩

![并查集的路径压缩](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/并查集路径压缩.png)

如上所示，就是把路径上的所有元素都指向a。

#### 2.5 并查集代码模版

```java
class UnionFind {
    private int count = 0;
    private int[] parent;
    
    // 初始化
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    // 查找
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // 合并
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
}
```

## 2. 高级搜索

初级搜索就是朴素搜索，简单暴力。

高级搜索就是在朴素搜索的基础上，进行优化，如 剪枝、双向搜索、启发式搜索。

### 2.1 剪枝

一般处理是会有一个缓存，将已经处理的结果存起来，如果遇到相同的问题，就直接取缓存中的值，不再进行重复计算。

### 2.2 双向搜索（BFS）

![单向BFS](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/双向搜索1.png)

正常的单向BFS，就是从A向外扩散，一直到L，得到它的最短路径。

![双向BFS](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/双向搜索2.png)

双向BFS，就是同时从A和L向外扩散，第一次重合的地方就是A和L之间的最短路径。

### 2.3 启发式搜索

根据某一条件，不断优化搜索的方向。本质上就是通过优先级去搜索。

#### 2.3.1 基于BFS的 A*搜索，就是使用优先队列。

```
void astarSearch(int[] graph, int start, int end) {
    HashSet<Integer> visited = new HashSet<>();
    // 优先级队列
    PriorityQueue<Integer> pd = new PriorityQueue<>();
    pd.add(graph[start]);
    visited.add(graph[start]);
    // BFS
    while (!pd.isEmpty()) {
        // 将优先级高的出队
        int node = pd.poll();
        visited.add(node);
        // 处理结点
        // process(node);
        // 根据子结点是否访问过，继续将去加入到优先队列中
        // for (int node : nodes) pd.add(node);
    }
}
```

#### 2.3.2 估价函数

启发式函数：h(n)，它用来评价哪些结点是最优希望是我们想要找的结点。h(n)会返回一个非负实数，也可以认为是从结点n到目标结点路径的估计成本。

启发式函数是一种告知搜索方向的方法。它提供了一种明智的方法来猜测哪个相邻结点会最终找到目标结点。

## 3. AVL树和红黑树

普通的树有时候会出现一种极端的情况，如下所示，从而退化成一个链表。

![树的极端情况](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/树极端情况.png)

所有就有了平衡树的结构，就是保证树的左右子树不会出现相差高度过大的问题。

### 3.1 AVL树

1. 是一棵平衡二叉搜索树

2. 平衡因子（Balance Factor）：是它的左子树高度减去它的右子树高度，或者右子树高度减去左子树高度。

   balance factor = {-1, 0, 1}，即左右子树高度相差不能大于1。

3. 通过旋转操作来进行平衡。

4. 缺点是需要存储额外的信息，且调整次数频繁。

![AVL树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/AVL.png)

#### 3.1.1 旋转操作 - 左旋

![AVL树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/avl左旋.png)

#### 3.1.2 旋转操作 - 右旋

![AVL树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/avl右旋.png)

#### 3.1.3 旋转操作 - 左右旋

![AVL树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/avl左右旋.png)

就是先将子结点都旋转到左侧，然后在进行右旋操作。

#### 3.1.4 旋转操作 - 右左旋

![AVL树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/avl右左旋.png)

就是先将子结点都旋转到右侧，然后在进行左旋操作。

### 3.2 红黑树

红黑树是一种近似平衡的二叉搜索树，它能够保证左右子树的高度差小于两倍（高度大的最多是小的两倍）。

满足红黑树的条件如下：

* 每个结点要么是红色，要么是黑色。
* 根结点是黑色。
* 每个叶结点是黑色的。
* 不能有相邻的两个红色结点（是指上下相邻，不是左右）。
* 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点。
* 最关键的是：从根到叶子的最长路径不多于最短路径的两倍。

![红黑树](https://github.com/IAn2018cs/algorithm009-class02/blob/master/Week_07/pic/红黑树.png)

红黑树和AVL树对比：

* AVL提供了更快的查找操作，因为AVL是更加严格的平衡。
* 红黑树提供了更快的插入删除操作，因为红黑树是近似平衡，所以它的旋转操作会少很多。
* AVL要存额外的信息，就是平衡因子和高度，红黑树只需要存0或1（黑或红）。
* 在读操作比较多的时候就使用AVL树，修改操作比较多的时候就使用红黑树。

