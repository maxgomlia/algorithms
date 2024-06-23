package org.crypto.graph;

import lombok.Getter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
public class Graph {
    private final int numberOfVertices;

    private final List<LinkedList<Integer>> adjacencyLists;

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;

        adjacencyLists = new ArrayList<LinkedList<Integer>>(numberOfVertices);

        for (int i = 0; i < numberOfVertices; ++i) {
            adjacencyLists.add(new LinkedList<Integer>());
        }
    }

    public void addEdge(int v, int w) {
        adjacencyLists.get(v).add(w);
    }
}
