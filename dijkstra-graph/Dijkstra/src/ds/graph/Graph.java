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

    public boolean containsEdge(Edge e)
    {
        if(e.getSource() == null && e.getDestination() == null)
            return false;

        return (edges.containsKey(e.hashCode()));
    }

    public Edge removeEdge(Edge e)
    {
        e.getSource().removeEdge(e);
        e.getDestination().removeEdge(e);
        return edges.remove(e.hashCode());
    }

    public boolean containsVertex(Vertex v)
    {
        return vertices.containsKey(v.getLabel());
    }

    public Vertex getVertex(Vertex v)
    {
        Objects.requireNonNull(v);
        return vertices.get(v.getLabel());
    }

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

    public Vertex removeVertex(String v)
    {
        Vertex vertex = vertices.remove(v);

        vertex.getEdgesFromVertex().forEach(e ->
        {
            removeEdge(e);
        });

        return vertex;
    }

    public Set<String> vertexKeys()
    {
        return vertices.keySet();
    }

    public Set<Edge> getEdges()
    {
        return new HashSet<Edge>(edges.values());
    }

}
