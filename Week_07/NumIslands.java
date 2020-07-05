package week07;

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

public class NumIslands {

    public int numIslands(char[][] grid) {
        int count = 0;
        int row = grid.length;
        if (row == 0) return count;
        int col = grid[0].length;
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

    // 标记深度优先
    private void dfsMark(char[][] grid, int row, int col, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0') return;
        if (grid[i][j] == '1') grid[i][j] = '0';
        int[][] dir = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int[] ints : dir) dfsMark(grid, row, col, i + ints[0], j + ints[1]);
    }
}
