/**
 * Created by zhangwenbo on 6/3/16.
 */

import java.util.*;

public class MergeCount {
    public List<Integer> countSmaller(int[] nums) {
        int[] aux = new int[nums.length];
        int[] ret = new int[nums.length];
        mergeCount(nums, 0, nums.length, ret, aux);
        List<Integer> r = new ArrayList<>(ret.length);
        for (int i : ret) r.add(i);
        return r;
    }

    private void mergeCount(int[] nums, int start, int end, int[] ret, int[] aux) {
        if (end - start <= 1) return;
        int mid = start + (end - start) / 2;
        mergeCount(nums, start, mid, ret, aux);
        mergeCount(nums, mid, end, ret, aux);
        int index = start;
        for (int i = start, m = mid; i < mid; i++) {
            while (m < end && nums[m] < nums[i]) {
                aux[index++] = nums[m++];
                ret[i]++;
            }
            aux[index++] = nums[i];
        }
        System.arraycopy(aux, start, nums, start, index - start);
    }

    public static void main(String[] args) {
        int[] a = new int[]{5,2,6,1};
        MergeCount s = new MergeCount();
        s.countSmaller(a);
        System.out.println(Arrays.toString(a));
    }
}
