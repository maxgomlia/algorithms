package org.max.graph;

import org.crypto.graph.Graph;

import java.util.List;

public interface SccFinder {
    List<List<Integer>> find(Graph graph);
}
