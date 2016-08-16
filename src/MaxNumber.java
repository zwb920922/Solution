import java.util.Arrays;

/**
 * Created by zhangwenbo on 6/1/16.
 */
public class MaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] ans = new int[k];
        int min = Math.min(l1, k);
        for (int i = Math.max(k-l2, 0); i <= min; i++) {
            int j = k - i;
            int[] ret1 = pick(nums1, i);
            int[] ret2 = pick(nums2, j);
            int[] ret = merge(ret1, ret2);
            if (compare(ret, ans) > 0)
                ans = ret;
        }
        return ans;
    }

    private int[] pick(int[] nums, int k) {
        int[] ret = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            while (len > 0 && len + nums.length - i > k && ret[len-1] < nums[i])
                len--;
            if (len < k) ret[len++] = nums[i];
        }
        return ret;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] ret = new int[nums1.length + nums2.length];
        for (int k = 0, i = 0, j = 0; k < ret.length; k++) {
            if (i >= nums1.length) ret[k] = nums2[j++];
            else if (j >= nums2.length) ret[k] = nums1[i++];
            else if (compare(nums1, i, nums2, j) > 0) ret[k] = nums1[i++];
            else ret[k] = nums2[j++];
        }
        return ret;
    }

    private int compare(int[] nums1, int d1, int[] nums2, int d2) {
        while (d1 < nums1.length || d2 < nums2.length) {
            if (d1 >= nums1.length) return -1;
            else if (d2 >= nums2.length) return 1;
            else if (nums1[d1] < nums2[d2]) return -1;
            else if (nums1[d1] > nums2[d2]) return 1;
            d1++;
            d2++;
        }
        return 0;
    }

    private int compare(int[] nums1, int[] nums2) {
        assert(nums1.length == nums2.length);
        int l = nums1.length;
        for (int i = 0; i < l; i++) {
            if (nums1[i] < nums2[i]) return -1;
            if (nums1[i] > nums2[i]) return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        MaxNumber m = new MaxNumber();
        System.out.println(Arrays.toString(m.maxNumber(new int[]{3,4,6,5}, new int[]{9,1,2,5,8,3}, 5)));
    }
}
