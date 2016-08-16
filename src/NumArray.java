import java.util.Arrays;

public class NumArray {

    private Node[] st; // segment tree array

    private int n; // length of the original array

    private int[] arrays; // original array

    public NumArray(int[] nums) {
        n = nums.length;
        arrays = Arrays.copyOf(nums, n);
        int height = ((int) (Math.log(n) / Math.log(2))) + 1;
        int size = (int) Math.pow(2, height + 1);
//        int size = n;
        st = new Node[size];
        buildTree(0, n-1, 1);
    }

    private void buildTree(int l, int r, int index) {
        if (l == r) {
            st[index] = new Node();
            st[index].from = l;
            st[index].to = r;
            st[index].sum = arrays[l];
            return;
        }
        int mid = l + (r - l) / 2;
        int lc = index*2; // left child
        buildTree(l, mid, lc);
        buildTree(mid+1, r, lc+1);
        st[index] = new Node();
        st[index].from = l;
        st[index].to = r;
        st[index].sum = st[lc].sum + st[lc+1].sum;
    }

    public void update(int i, int val) {
        update(i, val,1);
    }

    private void update(int i, int val, int cur) {
        Node n = st[cur];
        if (n.from > i || n.to < i) return;
        if (n.from == n.to) { n.sum = val; return; }
        int mid = n.from + (n.to - n.from) / 2;
        int lc = cur * 2;
        update(i, val, lc);
        update(i, val, lc+1);
        n.sum = st[lc].sum + st[lc+1].sum;
    }

    public int sumRange(int i, int j) {
        return sumRange(i, j, 1);
    }

    private int sumRange(int i, int j, int cur) {
        Node n = st[cur];
        if (n.from >= i && n.to <= j) return st[cur].sum;
        if (n.from > j || n.to < i) return 0;
        int mid = n.from + (n.to - n.from) / 2;
        int lc = cur * 2;
        return sumRange(i, j, lc) + sumRange(i, j, lc+1);
    }

    private class Node {
        int from;
        int to;
        int sum;
    }

    public static void main(String[] args) {
        NumArray a = new NumArray(new int[]{1,3,5});
//        a.update(0,3);
        System.out.println(a.sumRange(0,2));
//        System.out.println(a.sumRange(0,1));
        a.update(1,2);
        System.out.println(a.sumRange(0,2));
    }
}