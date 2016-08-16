/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.*;

public class HouseRobber {

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return recursiveRob(root, map);
    }

    // find the max value that can be robbed below the current node (included)
    private int recursiveRob(TreeNode cur, HashMap<TreeNode, Integer> map) {
        if (cur == null) return 0;
        Integer ret = map.get(cur);
        if (ret != null) return ret;
        int left = recursiveRob(cur.left, map);
        int right = recursiveRob(cur.right, map);
        int ll = cur.left == null ? 0 : recursiveRob(cur.left.left, map);
        int lr = cur.left == null ? 0 : recursiveRob(cur.left.right, map);
        int rl = cur.left == null ? 0 : recursiveRob(cur.left.left, map);
        int rr = cur.left == null ? 0 : recursiveRob(cur.left.left, map);
        int max = Math.max(left + right, ll + lr + rl + rr + cur.val);
        map.put(cur, max);
        return max;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}