package week01;

//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
//
// 示例 1:
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
//
//
// 示例 2:
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释:
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100]
//
// 说明:
//
//
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
// 要求使用空间复杂度为 O(1) 的 原地 算法。
//
// Related Topics 数组
public class Rotate {

    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 方法1 申请临时空间
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = temp[i];
        }
    }

    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 方法2 分三次反转数组 12345 假设k为2
        int move = k % nums.length;
        // 先全部反转 54321
        reverse(nums, 0, nums.length - 1);
        // 再反转前半部分 45321
        reverse(nums, 0, move - 1);
        // 最后反转后半部分 45123
        reverse(nums, move, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        Rotate r = new Rotate();
        int[] test = new int[]{1, 2, 3, 4, 5, 6};
        r.rotate1(test, 4);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + ",");
        }
    }
}
