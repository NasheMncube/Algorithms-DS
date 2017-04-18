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

    public String getLabel()
    {
        return this.label;
    }

    public List<Edge> getEdgesFromVertex()
    {
        return Collections.unmodifiableList(this.edgesFromVertex);
    }

    public void addEdge(Edge edge)
    {
        Objects.requireNonNull(edge);
        if(edgesFromVertex.contains(edge))
            throw new IllegalArgumentException("Trying to add already existing edge");
        else if(edge.getSource().getLabel() != label && edge.getDestination().getLabel() != label)
            throw new IllegalArgumentException("Trying to add invalid edge");

        edgesFromVertex.add(edge);
    }

    public void removeEdge(Edge edge)
    {
        Objects.requireNonNull(edge);

    }

    public boolean containsEdge(Edge edge)
    {
        Objects.requireNonNull(edge);

        return edgesFromVertex.contains(edge);
    }

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
