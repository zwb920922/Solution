/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

import java.util.*;

public class SummaryRanges {

    private List<Interval> list;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        list = new ArrayList<>();
    }

    public void addNum(int val) {
        int index = search(val);
        if (index >= 0) return;
        index = -(index+1);
        list.add(index, new Interval(val,val));
        merge(index);
    }

    private int search(int val) {
        int l = 0, r = list.size()-1;
        while (l <= r) {
            int mid = l + (r-l) / 2;
            Interval cur = list.get(mid);
            if (val > cur.end) l = mid+1;
            else if (val < cur.start) r = mid-1;
            else return mid;
        }
        return -l - 1;
    }

    private void merge(int d) {
        if (d < list.size()-1 && list.get(d).end == list.get(d+1).start-1) {
            int s = list.get(d).start;
            int e = list.get(d+1).end;
            list.set(d, new Interval(s,e));
            list.remove(d+1);
        }
        if (d > 0 && list.get(d).start == list.get(d-1).end+1) {
            int s = list.get(d-1).start;
            int e = list.get(d).end;
            list.set(d-1, new Interval(s,e));
            list.remove(d);
        }

    }

    public List<Interval> getIntervals() {
        return list;
    }

     public class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */