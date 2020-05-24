package week01;

//给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
//
//
//
// 说明:
//
//
// 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
// 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
//
//
//
//
// 示例:
//
// 输入:
//nums1 = [1,2,3,0,0,0], m = 3
//nums2 = [2,5,6],       n = 3
//
//输出: [1,2,2,3,5,6]
// Related Topics 数组 双指针

public class MergeTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 从后向前比较
        int index1 = m - 1;
        int index2 = n - 1;
        int index = m + n - 1;
        while (index1 >= 0 && index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                nums1[index] = nums1[index1--];
            } else {
                nums1[index] = nums2[index2--];
            }
            index--;
        }
        // 剩余的都拷贝到最前面
        System.arraycopy(nums2, 0, nums1, 0, index2 + 1);
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{1, 7, 11, 15, 0, 0, 0};
        int[] test2 = new int[]{2, 9, 16};
        MergeTwoArray m = new MergeTwoArray();
        m.merge(test1, 4, test2, 3);
        for (int i = 0; i < test1.length; i++) {
            System.out.print(test1[i] + ",");
        }
    }
}
