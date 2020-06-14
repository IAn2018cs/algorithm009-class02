package week04;

//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
//
// 说明：不要使用任何内置的库函数，如 sqrt。
//
// 示例 1：
//
// 输入：16
//输出：True
//
// 示例 2：
//
// 输入：14
//输出：False
//
// Related Topics 数学 二分查找
// https://leetcode-cn.com/problems/valid-perfect-square/

public class IsPerfectSquare {

    // 二分查找
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;
        int mid = (right - left) / 2 + left;
        while (left <= right) {
            int square = mid * mid;
            if (square > num) {
                right = mid - 1;
            } else if (square < num) {
                left = mid + 1;
            } else {
                return true;
            }
            mid = (right - left) / 2 + left;
        }
        return false;
    }

    public boolean isPerfectSquare2(int num) {
        int n = 1;
        while (num > 0) {
            num -= n;
            n += 2;
        }
        return num == 0;
    }
}
