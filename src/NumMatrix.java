
import java.util.*;

public class NumMatrix {

    private int[][] segmentTree;

    private int[][] nums;

    private int rows, cols;

    public NumMatrix(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        nums = new int[rows][cols];
        segmentTree = new int[nextPowerOf2(matrix.length) * 2][nextPowerOf2(matrix[0].length) * 2];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, nums[i], 0, cols);
            constructMinSegmentTree(segmentTree[i], matrix[i], 0, matrix[0].length - 1, 1);
        }

    }

    public void update(int row, int col, int val) {
        int dif = val - nums[row][col];
        nums[row][col] = val;
        update(segmentTree[row], col, dif, 0, cols - 1, 1);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int ret = 0;
        for (int i = row1; i <= row2; i++) {
            ret += sum(segmentTree[i], 0, cols - 1, col1, col2, 1);
        }
        return ret;
    }

    private void update(int[] tree, int col, int dif, int low, int high, int pos) {
        if (col > high || col < low) return;
        tree[pos] += dif;
        if (low == high) return;
        int mid = low + (high - low) / 2;
        update(tree, col ,dif, low, mid, pos * 2);
        update(tree, col ,dif, mid+1, high, pos * 2 + 1);
    }

    private int sum(int[] segmentTree,int low,int high,int qlow,int qhigh,int pos){
        if(qlow <= low && qhigh >= high){
            return segmentTree[pos];
        }
        if(qlow > high || qhigh < low){
            return 0;
        }
        int mid = (low+high)/2;
        return (sum(segmentTree, low, mid, qlow, qhigh, 2 * pos)
                + sum(segmentTree, mid + 1, high, qlow, qhigh, 2 * pos + 1));
    }

    private void constructMinSegmentTree(int[] tree, int[] input, int low, int high, int pos) {
        if (low == high) {
            tree[pos] = input[low];
            return;
        }
        int mid = low + (high - low) / 2;
        constructMinSegmentTree(tree, input, low, mid, 2 * pos);
        constructMinSegmentTree(tree, input, mid + 1, high, 2 * pos + 1);
        tree[pos] = tree[2*pos] + tree[2*pos+1];
    }

    private int nextPowerOf2(int i) {
        int ret = 1;
        while (ret < i) {
            ret *= 2;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix n = new NumMatrix(a);
        System.out.println(n.sumRegion(2,1,3,4));
        n.update(3,2,2);
        System.out.println(n.sumRegion(2,1,3,4));
        NumMatrix n2 = new NumMatrix(new int[][]{{1,2}});
    }

}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);