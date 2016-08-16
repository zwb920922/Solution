import java.util.TreeSet;

/**
 * Created by zhangwenbo on 6/24/16.
 */
public class maxSumSubmatrix {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int ret = 0;
        int[] sums = new int[n];
        for (int l = 0; l < m; l++) {
            for (int i = l; i < m; i++) {
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                for (int j = 0; j < n; j++) {
                    sums[i] += matrix[i][j];
                }
                for (int j = 0; j < n; j++) {
                    int target = matrix[i][j]-k;
                    int tmp = set.ceiling(target);
                    if (tmp == target) return k;
                    if (matrix[i][j]-tmp > ret) ret = matrix[i][j]-tmp;
                    set.add(matrix[i][j]);
                }
            }
            for (int i = 0; i < n; i++) {
                sums[i] = 0;
            }
        }
        return ret;
    }

}
