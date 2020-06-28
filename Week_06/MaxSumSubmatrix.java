package week06;

//给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
//
// 示例:
//
// 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
//输出: 2
//解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
//
//
// 说明：
//
//
// 矩阵内的矩形区域面积必须大于 0。
// 如果行数远大于列数，你将如何解答呢？
//
// Related Topics 队列 二分查找 动态规划
// https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/

public class MaxSumSubmatrix {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < col; l++) {
            int[] rowSum = new int[row];
            for (int r = l; r < col; r++) {
                for (int i = 0; i < row; i++) {
                    rowSum[i] += matrix[i][r];
                }
                max = Math.max(max, dpMax(rowSum, k));
                if (max == k) {
                    return k;
                }
            }
        }
        return max;
    }

    private int dpMax(int[] arr, int k) {
        int rollSum = arr[0];
        int rollMax = rollSum;
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0) {
                rollSum += arr[i];
            } else {
                rollSum = arr[i];
            }
            if (rollSum > rollMax) {
                rollMax = rollSum;
            }
        }
        if (rollMax <= k) {
            return rollMax;
        }
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k) {
                    max = sum;
                }
                if (max == k) {
                    return k;
                }
            }
        }
        return max;
    }
}
