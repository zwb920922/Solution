import java.util.*;

/**
 * Created by zhangwenbo on 6/2/16.
 */
public class WordSearch2 {

    private Node root;

    private Set<String> ret;

    public List<String> findWords(char[][] board, String[] words) {
        if (board.length == 0 || board[0].length == 0)
            return new ArrayList<>();
        int rows = board.length;
        int cols = board[0].length;
        root = null;
        for (String word : words) {
            root = addWord(word, 0, root);
        }
        ret = new HashSet<>();
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                findAll(board, i, j, root, visited);
            }
        }
        return new ArrayList<>(ret);
    }

    private void findAll(char[][] board, int i, int j, Node cur, boolean[][] visited) {
        if (cur == null) return;
        if (cur.word != null) {
            ret.add(cur.word);
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if (visited[i][j]) return;
        visited[i][j] = true;
        cur = cur.next[board[i][j] - 'a'];
        findAll(board,i+1, j, cur, visited);
        findAll(board,i, j+1, cur, visited);
        findAll(board,i-1, j, cur, visited);
        findAll(board,i, j-1, cur, visited);
        visited[i][j] = false;
    }

    private class Node {
        String word;
        Node[] next = new Node[26];
    }

    private Node addWord(String s, int d, Node cur) {
        if (cur == null) cur = new Node();
        if (d == s.length()) {
            cur.word = s;
            return cur;
        }
        char c = s.charAt(d);
        cur.next[c-'a'] = addWord(s, d+1, cur.next[c-'a']);
        return cur;
    }

}
