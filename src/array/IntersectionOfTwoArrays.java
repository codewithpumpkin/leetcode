package array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * @author Arman
 */
public class IntersectionOfTwoArrays {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] intersect = intersect2(nums1, nums2);
        System.out.println("Arrays.toString(intersect) = " + Arrays.toString(intersect));
    }

    /**
     * 第一种思路：
     *  1. 先遍历第一个数组，将每个元素放入 map 的 key 中，把出现的次数放入 value
     *  2. 在遍历第二个数组，将每个元素与 map 中的对比，如果 map 中有，就放入返回的数组中，再将 map 的 value - 1
     *      如果不执行 map.put(item, map.get(item) + 1); ，那么遇到一个数组里面的重复元素，就会多次计算
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>(nums1.length);
        for (int item : nums1) {
            if (map.containsKey(item)) {
                map.put(item, map.get(item) + 1);
            } else {
                map.put(item, 1);
            }
        }
        int k = 0;
        Integer count;
        int[] resArr = new int[nums1.length];
        for (int item : nums2) {
            if (!map.containsKey(item)) {
                continue;
            }
            count = map.get(item);
            if (count > 0) {
                resArr[k++] = item;
                map.put(item, count - 1);
            }
        }
        return Arrays.copyOfRange(resArr, 0, k);
    }

    /**
     * 第二种思路
     *  第二种思路主要是用的双指针的方法
     *      1. 先对两个数组进行排序
     *      2. 在用两个指针循环两个数组，如果两个元素相等就存到返回的数组中，如果第一个数组的元素大，就把第二个数组的指针向后移动一位，反之同理；直到任意一个数组遍历完
     */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] resArr = new int[Math.min(nums1.length, nums2.length)];
        int k = 0;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length; ) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                resArr[k++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(resArr, 0, k);
    }


}
