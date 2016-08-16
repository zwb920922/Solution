/**
 * Created by zhangwenbo on 6/11/16.
 */
public class ShortestPal {
    public String shortestPalindrome(String s) {
        int len = s.length();
        if (len % 2 == 1) {
            for (int i = len/2; i > 0; i--) {
                if (isPal(s, i, i)) return new StringBuilder(s.substring(len - 2 * (len/2 - i), len)).reverse().toString() + s;
                if (isPal(s, i-1, i)) return new StringBuilder(s.substring(len - 2 * (len/2-i) - 1, len)).reverse().toString() + s;
            }
            return new StringBuilder(s).reverse().toString() + s;
        } else {
            for (int i = len/2-1; i >= 0; i--) {
                if (isPal(s, i, i+1)) return new StringBuilder(s.substring(len - 2 * (len/2-i-1))).reverse().toString() + s;
                if (isPal(s, i, i)) return new StringBuilder(s.substring(len - 2 * (len/2-i-1) - 1, len)).reverse().toString() + s;
            }
            return new StringBuilder(s).reverse().toString() + s;
        }
    }

    private boolean isPal(String s, int i, int j) {
        int len = s.length();
        while (i >= 0) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i--;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        ShortestPal s = new ShortestPal();
        System.out.println(s.shortestPalindrome("a"));
    }

}
