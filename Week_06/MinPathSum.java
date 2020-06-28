package week06;

//给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
//
// 说明：每次只能向下或者向右移动一步。
//
// 示例:
//
// 输入:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//输出: 7
//解释: 因为路径 1→3→1→1→1 的总和最小。
//
// Related Topics 数组 动态规划
// https://leetcode-cn.com/problems/minimum-path-sum/

public class MinPathSum {
    public int minPathSum(int[][] grid) {
        // 1. i == 0 && j == 0 -> dp[i][j] = dp[i][j]
        // 2. i == 0 && j != 0 -> dp[i][j] = dp[i][j-1] + dp[i][j]
        // 3. i != 0 && j == 0 -> dp[i][j] = dp[i-1][j] + dp[i][j]
        // 4. i != 0 && j != 0 -> dp[i][j] = min(dp[i-1][j],dp[i][j-1]) + dp[i][j]
        int row = grid.length;
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) continue;
                else if (i == 0) grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if (j == 0) grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[row - 1][col - 1];
    }
}
