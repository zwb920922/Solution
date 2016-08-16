import java.util.*;

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
        UndirectedGraphNode ret = clone(node, visited);
        return ret;
    }

    private UndirectedGraphNode clone(UndirectedGraphNode cur, Map<UndirectedGraphNode, UndirectedGraphNode> visited) {
        UndirectedGraphNode copy = visited.get(cur);
        if (copy != null) return copy;
        copy = new UndirectedGraphNode(cur.label);
        visited.put(cur, copy);
        for (UndirectedGraphNode neighbor : cur.neighbors) {
            copy.neighbors.add(clone(neighbor, visited));
        }
        return copy;
    }

    private class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }
}