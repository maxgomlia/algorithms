package org.max.graph;

import org.crypto.graph.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TarjansSccFinder implements SccFinder {

    private static final int UNVISITED = -1;

    public List<List<Integer>> find(Graph graph) {
        int numberOfVertices = graph.getNumberOfVertices();

        int[] ids = new int[numberOfVertices];

        int[] lowestReachable = new int[numberOfVertices];

        boolean[] isInStack = new boolean[numberOfVertices];

        Stack<Integer> stack = new Stack<>();

        List<List<Integer>> sccResults = new ArrayList<>();

        initializeInput(ids, lowestReachable);

        int id = 0;
        for (int vertex = 0; vertex < numberOfVertices; vertex++) {
            if (ids[vertex] == UNVISITED) {
                dfs(id, graph.getAdjacencyLists(), vertex, lowestReachable, ids, isInStack, stack, sccResults);
            }
        }

        return sccResults;
    }

    private void initializeInput(int[] ids, int[] lowestReachable) {
        for (int i = 0; i < ids.length; i++) {
            ids[i] = UNVISITED;
            lowestReachable[i] = UNVISITED;
        }
    }

    private void dfs(int id, List<LinkedList<Integer>> adjacencyLists, int vertex, int[] lowestReachable,
                     int[] ids, boolean[] isInStack, Stack<Integer> stack, List<List<Integer>> sccResults) {

        ids[vertex] = lowestReachable[vertex] = id++;
        isInStack[vertex] = true;
        stack.push(vertex);

        for (int adjacentVertex : adjacencyLists.get(vertex)) {
            if (ids[adjacentVertex] == UNVISITED) {
                dfs(id, adjacencyLists, adjacentVertex, lowestReachable, ids, isInStack, stack, sccResults);
            }

            // callback from dfs
            if (isInStack[adjacentVertex]) {
                lowestReachable[vertex] = Math.min(lowestReachable[vertex], lowestReachable[adjacentVertex]);
            }
        }

        if (lowestReachable[vertex] == ids[vertex]) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                isInStack[w] = false;
                scc.add(w);
            } while (w != vertex);
            sccResults.add(scc);
        }
    }
}
