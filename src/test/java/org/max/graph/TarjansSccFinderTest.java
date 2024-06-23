package org.max.graph;

import org.crypto.graph.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TarjansSccFinderTest {
    private TarjansSccFinder sccFinder;

    @BeforeEach
    public void setUp() {
        sccFinder = new TarjansSccFinder();
    }

    @Test
    void simpleConnectedGraph() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        List<List<Integer>> sccs = sccFinder.find(graph);

        assertEquals(1, sccs.size());
        assertEquals(Arrays.asList(2, 1, 0), sccs.get(0));
    }

    @Test
    void disconnectedGraph() {
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        List<List<Integer>> sccs = sccFinder.find(graph);

        assertEquals(4, sccs.size());
        assertEquals(List.of(1), sccs.get(0));
        assertEquals(List.of(0), sccs.get(1));
        assertEquals(List.of(3), sccs.get(2));
        assertEquals(List.of(2), sccs.get(3));
    }

    @Test
    void graphWithCycles() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 0);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);

        List<List<Integer>> sccs = sccFinder.find(graph);

        assertEquals(2, sccs.size());

        Collections.sort(sccs.get(0));
        Collections.sort(sccs.get(1));

        assertTrue(sccs.containsAll(List.of(List.of(0, 1, 2), List.of(3, 4, 5))));
    }

    @Test
    void singleVertexGraph() {
        Graph graph = new Graph(1);

        List<List<Integer>> sccs = sccFinder.find(graph);

        assertEquals(1, sccs.size());
        assertEquals(List.of(0), sccs.get(0));
    }

    @Test
    void twoVertexGraph() {
        Graph graph = new Graph(2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);

        List<List<Integer>> sccs = sccFinder.find(graph);

        assertEquals(1, sccs.size());
        assertEquals(List.of(1, 0), sccs.get(0));
    }
}
