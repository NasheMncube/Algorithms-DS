package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */

import java.util.Collections;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.collections.ImmutableObservableList;
import ds.graph.Edge;

public class Vertex {
    private final String label;
    private final List<Edge> edgesFromVertex;

    /**
     * Constructer of class
     * @param label the label for the vertex.
     */
    public Vertex(String label)
    {
        Objects.requireNonNull(label);
        this.label           = label;
        this.edgesFromVertex = new ArrayList<>();
    }

    /**
     *
     * @return the label of this vertex
     */
    public String getLabel()
    {
        return this.label;
    }

    /**
     *
     * @return all edges connected to this vertex
     */
    public List<Edge> getEdgesFromVertex()
    {
        return Collections.unmodifiableList(this.edgesFromVertex);
    }

    /**
     * Adds en edge to this vertex
     * @param edge edge to add to this vertex
     */
    public void addEdge(Edge edge)
    {
        Objects.requireNonNull(edge);
        if(edgesFromVertex.contains(edge))
            throw new IllegalArgumentException("Trying to add already existing edge");
        else if(edge.getSource().getLabel() != label && edge.getDestination().getLabel() != label)
            throw new IllegalArgumentException("Trying to add invalid edge");

        edgesFromVertex.add(edge);
    }

    /**
     * Determines if the edge given is part of the vertex edges
     * @param edge
     * @return true if vertex contains edge, false otherwise
     */
    public boolean containsEdge(Edge edge)
    {
        Objects.requireNonNull(edge);

        return edgesFromVertex.contains(edge);
    }

    /**
     * Obtains the edge given if within edges
     * @param edge
     * @return the edge
     */
    public Edge getEdge(Edge edge)
    {
        Objects.requireNonNull(edge);
        Edge[] ret = {null};
        if(!edgesFromVertex.contains(edge))
            throw new IllegalArgumentException("Trying to obtain non-existing edge");
        edgesFromVertex.forEach(e ->
        {
            if(e.equals(edge))
                ret[0] = e;
        });

        return ret[0];
    }

    public boolean compareWithObject(Object other)
    {
        Objects.requireNonNull(other);

        if(!(other instanceof Vertex))
            return false;

        Vertex v = (Vertex)other;

        return(label.equals(v.label));
    }

    /**
     *
     * @return returns number of edges
     */
    public int numberOfEdges()
    {
        return edgesFromVertex.size();
    }

    public String toString()
    {
        return "Vertex " + this.label;
    }

    public int HashCode()
    {
        return label.hashCode();
    }

}
