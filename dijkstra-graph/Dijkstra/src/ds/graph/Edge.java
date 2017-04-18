package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */
import ds.graph.Vertex;

import java.util.Objects;

public class Edge implements Comparable<Edge>{
    private final Vertex source;
    private final Vertex destination;
    private int weight;

    /**
     * The constructer for the class
     * @param source the node from which the edge points from.
     * @param destination the destination node for which the node points to
     */
    public Edge(Vertex source, Vertex destination, Integer weight)
    {
        Objects.requireNonNull(source); Objects.requireNonNull(destination);

        if(source.getLabel() == destination.getLabel())
            throw new IllegalArgumentException("Source and destination of edge the same");
        this.source = source;
        this.destination = destination;

        this.weight = (weight <= 0) ? 1 : weight;
    }

    public Edge(Vertex source, Vertex destination)
    {
        Objects.requireNonNull(source); Objects.requireNonNull(destination);

        if(source.getLabel() == destination.getLabel())
            throw new IllegalArgumentException("Source and destination of edge the same");

        this.source = source;
        this.destination = destination;
        this.weight = 1;
    }

    public int getEdgeWeight()
    {
        return this.weight;
    }

    public Vertex getSource()
    {
        return this.source;
    }

    public Vertex getDestination()
    {
        return this.destination;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int compareTo(Edge edge)
    {
        return this.weight - edge.getEdgeWeight();
    }

    public String toString()
    {
        return String.format("(Source: %s, Destination: %s, Weight: %d)",
                this.source, this.destination, weight);
    }

    public int hashCode()
    {
        return (source.getLabel() + destination.getLabel()).hashCode();
    }

    public boolean compare(Object other)
    {
        if(!(other instanceof Edge))
            return false;

        Edge e = (Edge) other;

        return(e.source.equals(source) && e.destination.equals(destination) && e.weight == weight);
    }
}
