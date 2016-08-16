
import java.util.*;

public class FindSubstring {
    public List<Integer> findSubstring(String s, String[] words) {
        if (s == null || s.length() == 0 || words == null || words.length == 0) return new ArrayList<>();
        List<Integer> ret = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String str : words) {
            map.put(str, map.containsKey(str) ? map.get(str) + 1 : 1);
        }
        int len = words[0].length();
        int i = 0, start = 0, num = 0;
        Map<String, Integer> tmpMap = new HashMap<>(map);
        while (i < s.length() - len + 1) {
            String str = s.substring(i, i + len);
            if (tmpMap.containsKey(str)) {
                int count = tmpMap.get(str);
                if (count == 0) {
                    String pre = s.substring(start, start + len);
                    while (!pre.equals(str)) {
                        tmpMap.put(pre, tmpMap.get(pre) + 1);
                        start += len;
                        num--;
                        pre = s.substring(start, start + len);
                    }
                    start += len;
                } else if (count == 1) {
                    num++;
                    tmpMap.put(str, 0);
                    if (num == words.length) {
                        ret.add(start);
                        num--;
                        tmpMap.put(s.substring(start, start + len), 1);
                        start += len;
                    }
                } else {
                    num++;
                    tmpMap.put(str, count - 1);
                }
                i += len;
            } else {
                start = ++i;
                tmpMap = new HashMap<>(map);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        FindSubstring f = new FindSubstring();
        List<Integer> l = f.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"});
        System.out.println(l.toString());
    }

}