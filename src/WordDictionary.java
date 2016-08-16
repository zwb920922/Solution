public class WordDictionary {

    private static int R = 256;

    private Node root;

    // Adds a word into the data structure.
    public void addWord(String word) {
        root = addWord(word, 0, root);
    }

    private Node addWord(String word, int d, Node cur) {
        if (cur == null)
            cur = new Node();
        if (d == word.length()) {
            cur.value = true;
            return cur;
        }
        char c = word.charAt(d);
        cur.next[c] = addWord(word, d+1, cur.next[c]);
        return cur;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int d, Node cur) {
        if (d == word.length()) {
            return cur != null && cur.value;
        }
        char c = word.charAt(d);
        if (c == '.') {
            for (int i = 0; i < R; i++) {
                if (search(word, d+1, cur.next[i]))
                return true;
            }
            return false;
        } else if(c == 'a' && word.length() - d > 2 && word.substring(d+1, d+3).equals("-z")) {
            for (int i = 'a'; i <= 'z'; i++) {
                if (search(word, d+1, cur.next[i]))
                return true;
            }
            return false;
        }
        return search(word, d+1, cur.next[c]);
    }

    private class Node {
        private boolean value;
        private Node[] next = new Node[R];
    }

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("ABC");
        wd.addWord("CDA");
        System.out.println(wd.search("ABC"));
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");