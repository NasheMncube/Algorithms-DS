package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import ds.graph.Edge;

public class Vertex {
    private String label;
    private List<Edge> edgesFromVertex;

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
        return this.edgesFromVertex;
    }


}
