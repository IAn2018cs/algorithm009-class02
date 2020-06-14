package week04;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1:
//
// 输入:
//11110
//11010
//11000
//00000
//输出: 1
//
//
// 示例 2:
//
// 输入:
//11000
//11000
//00100
//00011
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
//
// Related Topics 深度优先搜索 广度优先搜索 并查集
// https://leetcode-cn.com/problems/number-of-islands/

import java.util.LinkedList;

public class NumIslands {

    private int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private boolean[][] visited;
    private int row, col;

    public int numIslands(char[][] grid) {
        row = grid.length;
        if (row == 0) {
            return 0;
        }
        col = grid[0].length;
        visited = new boolean[row][col];
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 是陆地 并且没有访问过
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    // 深度优先
    private void dfs(char[][] grid, int i, int j) {
        visited[i][j] = true;
        // 递归四个方向
        for (int[] xy : direction) {
            int newX = i + xy[0];
            int newY = j + xy[1];
            // 没有越界 并且是陆地 并且没有访问过
            if (!isCross(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                dfs(grid, newX, newY);
            }
        }
    }

    // 广度优先
    private void bfs(char[][] grid, int i, int j) {
        LinkedList<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.addLast(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] xy = queue.removeFirst();
            for (int[] ints : direction) {
                int newX = xy[0] + ints[0];
                int newY = xy[1] + ints[1];
                if (!isCross(newX, newY) && grid[newX][newY] == '1' && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.addLast(new int[]{newX, newY});
                }
            }
        }
    }

    private boolean isCross(int i, int j) {
        return i < 0 || i >= row || j < 0 || j >= col;
    }

    // 简洁写法
    public int numIslands2(char[][] grid) {
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsMark(grid, row, col, i, j);
                }
            }
        }
        return count;
    }

    private void dfsMark(char[][] grid, int row, int col, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') return;
        if (grid[i][j] == '1') grid[i][j] = '0';
        dfsMark(grid, row, col, i - 1, j);
        dfsMark(grid, row, col, i, j - 1);
        dfsMark(grid, row, col, i + 1, j);
        dfsMark(grid, row, col, i, j + 1);
    }
}
