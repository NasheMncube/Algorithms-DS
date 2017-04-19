/**
 * Created by nashe on 17/04/2017.
 *
 * Generic graph implementation.
 * Following below tutorial http://www.dreamincode.net/forums/topic/377473-graph-data-structure-tutorial/
 */

package ds.graph;

import java.util.*;

public class Graph {
    private HashMap<String, Vertex> vertices;
    private HashMap<Integer, Edge> edges;

    public Graph()
    {
        this.vertices = new HashMap<>();
        this.edges    = new HashMap<>();
    }

    public Graph(Vertex...vertices)
    {
        this.vertices = new HashMap<>();
        this.edges    = new HashMap<>();

        for (Vertex vertex : vertices) {
            this.vertices.put(vertex.getLabel(), vertex);
        }
    }

    /**
     * Adds an edge to graph
     * @param v1 source vertex
     * @param v2 destination vertex
     * @param weight the weight of the edge
     * @return true if edge successfully added, false otherwise.
     */
    public boolean addEdge(Vertex v1, Vertex v2, int weight)
    {
        if(v1.equals(v2))
            return false;

        Edge e = new Edge(v1, v2, weight);

        if(edges.containsKey(e.hashCode()))
            return false;

        if(v1.containsEdge(e) || v2.containsEdge(e))
            return false;

        edges.put(e.hashCode(), e);
        v1.addEdge(e);
        v2.addEdge(e);

        return true;
    }

    public boolean addEdge(Vertex v1, Vertex v2)
    {
        return addEdge(v1, v2, 1);
    }

    /**
     * Determines if edge is within graph
     * @param e edge
     * @return true if graph contains edge false otherwise
     */
    public boolean containsEdge(Edge e)
    {
        if(e.getSource() == null && e.getDestination() == null)
            return false;

        return (edges.containsKey(e.hashCode()));
    }

    /**
     * Removes an edge
     * @param e edge
     * @return the edge removed. If it fails, returns null
     */
    public Edge removeEdge(Edge e)
    {
        return edges.remove(e.hashCode());
    }

    /**
     *
     * @param v vertex
     * @return true if graph contains vertex, false otherwise
     */
    public boolean containsVertex(Vertex v)
    {
        return vertices.containsKey(v.getLabel());
    }

    /**
     *
     * @param v vertex
     * @return returns vertex, returns null if vertex not in graph
     */
    public Vertex getVertex(Vertex v)
    {
        Objects.requireNonNull(v);
        return vertices.get(v.getLabel());
    }

    /**
     * Adds a vertex to the graph
     * @param v vertex
     * @param overwrite true if user wants to overwrite existing vertex, false otherwise
     * @return true if vertex successfully added false otherwise
     */
    public boolean addVertex(Vertex v, boolean overwrite)
    {
        Objects.requireNonNull(v);
        if(vertices.containsKey(v.getLabel()) && !overwrite)
            return false;
        else if(vertices.containsKey(v.getLabel()) && overwrite)
        {
            removeVertex(v.getLabel());
            vertices.put(v.getLabel(), v);
            return true;
        }
        else
        {
            vertices.put(v.getLabel(), v);
            return true;
        }
    }

    public boolean addVertex(Vertex v)
    {
        return addVertex(v, false);
    }

    /**
     * removes a vertex
     * @param v vertex
     * @return The vertex removed. Null if not within graph
     */
    public Vertex removeVertex(String v)
    {
        Vertex vertex = vertices.remove(v);
        if(vertex == null)
            throw new IllegalArgumentException("Vertex not within graph");

        vertex.getEdgesFromVertex().forEach(e ->
        {
            removeEdge(e);
        });

        return vertex;
    }

    /**
     *
     * @return {@link Set<String>} set of all vertex labels
     */
    public Set<String> vertexKeys()
    {
        return vertices.keySet();
    }

    /**
     *
     * @return {@link Set<Edge>} of all edges within graph
     */
    public Set<Edge> getEdges()
    {
        return new HashSet<Edge>(edges.values());
    }

}
