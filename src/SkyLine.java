/**
 * Created by zhangwenbo on 6/5/16.
 */

import java.util.*;

public class SkyLine {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> ret = new ArrayList<>();
        int n = buildings.length;
        if (n == 0) return ret;
        PriorityQueue<int[]> q = new PriorityQueue<>(n, (a,b)->{
            int hdif = b[2]-a[2];
            return hdif == 0 ? b[1]-a[1] : hdif;
        });

        for (int[] b : buildings) {
            int[] pre = q.isEmpty() ? null : q.peek();
            while (!q.isEmpty() && q.peek()[1] < b[0]) {
                int[] tmp = q.poll();
                if (tmp[1] > pre[1] && tmp[2] < pre[2]) {
                    ret.add(new int[]{pre[1], tmp[2]});
                    pre = tmp;
                }
            }
            if (q.isEmpty() && pre != null) ret.add(new int[]{pre[1], 0});
            if (q.isEmpty()) {
                ret.add(new int[]{b[0], b[2]});
            } else if (q.peek()[2] < b[2]) {
                int last = ret.size()-1;
                if (ret.get(last)[0] == b[0])
                    ret.set(last, new int[]{b[0], b[2]});
                else
                    ret.add(new int[]{b[0], b[2]});
            }
            q.add(b);
        }

        int[] pre = q.isEmpty() ? null : q.peek();
        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            if (tmp[1] > pre[1] && tmp[2] < pre[2]) {
                ret.add(new int[]{pre[1], tmp[2]});
                pre = tmp;
            }
        }
        if (q.isEmpty() && pre != null) ret.add(new int[]{pre[1], 0});
        return ret;
    }

    public static void main(String[] args) {
        SkyLine s = new SkyLine();
        List<int[]> l = s.getSkyline(new int[][]{{0,2,3},{2,5,3}});
//        List<int[]> l = s.getSkyline(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        for (int[] i : l) {
            System.out.println(Arrays.toString(i));
        }
    }

    private boolean isMatch(String s, int d1, String p, int d2) {
        if (d2 == p.length()) return d1 == s.length();
        char c = p.charAt(d2);
        if (d2 >= p.length()-1 || p.charAt(d2+1) != '*') {
            if (d1 == s.length()) return false;
            else if (c != '.' && c != s.charAt(d1)) return false;
            else return isMatch(s, d1+1, p, d2+1);
        } else {
            while (d1 < s.length() && (c == '.' || c == s.charAt(d1))) {
                if (isMatch(s, d1, p, d2+2)) return true;
                d1++;
            }
            return isMatch(s, d1, p, d2+2);
        }
    }
}
