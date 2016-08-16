import java.util.*;

/**
 * Created by zhangwenbo on 7/21/16.
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        int rowBegin = matrix.length / 2, rowEnd = matrix.length / 2;
        int colBegin = matrix[0].length / 2, colEnd = matrix[0].length / 2;
        boolean hasMore = true;
        while (hasMore) {
            hasMore = false;
            if (rowBegin >= 0) {
                for (int i = Math.max(0, colBegin); i <= Math.min(matrix[0].length - 1, colEnd); i++) {
                    ret.add(matrix[rowBegin][i]);
                }
                colEnd++;
                hasMore = true;
            }

            if (colEnd < matrix.length) {
                for (int i = Math.max(0,rowBegin); i <= Math.min(matrix.length-1,rowEnd); i++) {
                    ret.add(matrix[i][colEnd]);
                }
                rowEnd++;
                hasMore = true;
            }

            if (rowEnd < matrix.length) {
                for (int i = Math.min(matrix[0].length-1,colEnd); i >= Math.max(0,colBegin); i--) {
                    ret.add(matrix[rowEnd][i]);
                }
                colBegin--;
                hasMore = true;
            }

            if (colBegin >= 0) {
                for (int i = Math.min(matrix.length-1,rowEnd); i >= Math.max(0,rowBegin); i--) {
                    ret.add(matrix[colBegin][i]);
                }
                rowBegin++;
                hasMore = true;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        SpiralMatrix s = new SpiralMatrix();
        int[][] m = new int[][]{{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(s.spiralOrder(m));
    }

}
