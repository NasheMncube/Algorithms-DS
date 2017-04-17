package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */
import ds.graph.Vertex;

public class Edge {
    public String source;
    public String destination;

    /**
     * The constructer for the class
     * @param source the node from which the edge points from.
     * @param destination the destination node for which the node points to
     */
    public Edge(String source, String destination)
    {

        this.source = source;
        this.destination = destination;
    }


}
