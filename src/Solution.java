import java.util.*;


public class Solution {
    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int enemyForRow = 0;
        int[] enemyForCol = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || grid[i-1][j] == 'W') {
                    enemyForCol[j] = calculateCol(grid, i, j);
                }
                if (j == 0 || grid[i][j-1] == 'W') {
                    enemyForRow = calculateRow(grid, i, j);
                }
                if (grid[i][j] == '0') {
                    int tmp = enemyForRow + enemyForCol[j];
                    if (grid[i][j] == 'E') tmp--;
                    if (tmp > max) max = tmp;
                }
            }
        }
        return max;
    }

    private int calculateRow(char[][] grid, int i, int j) {
        int num = 0;
        for (int k = j; k < grid[0].length; k++) {
            if (grid[i][k] == 'E') num++;
            if (grid[i][k] == 'W') break;
        }
        return num;
    }

    private int calculateCol(char[][] grid, int i, int j) {
        int num = 0;
        for (int k = i; k < grid.length; k++) {
            if (grid[k][j] == 'E') num++;
            if (grid[k][j] == 'W') break;
        }
        return num;
    }
}