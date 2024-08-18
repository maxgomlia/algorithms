package org.max.graph;

//Given a reference of a node in a connected undirected graph.
//
//        Return a deep copy (clone) of the graph.
//
//        Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.
//
//class Node {
//    public int val;
//    public List<Node> neighbors;
//}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
//        Output: [[2,4],[1,3],[2,4],[1,3]]
//        Explanation: There are 4 nodes in the graph.
//        1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//        2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
//        3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
//        4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
public class CloneGraph {
    public Node cloneGraph(Node root) {
        if (root == null) {
            return null;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);

        HashMap<Node, Node> origToClonedMap = new HashMap<>();
        origToClonedMap.put(root, new Node(root.val));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Node neighbor : node.neighbors) {
                if (!origToClonedMap.containsKey(neighbor)) {
                    Node cloned = new Node(neighbor.val);

                    origToClonedMap.put(neighbor, cloned);

                    queue.offer(neighbor);
                }

                origToClonedMap.get(node).neighbors.add(origToClonedMap.get(neighbor));
            }
        }

        return origToClonedMap.get(root);
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<Node>();
        }
    }
}
