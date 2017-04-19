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

    /**
     *
     * @return the weight of this edge
     */
    public int getEdgeWeight()
    {
        return this.weight;
    }

    /**
     *
     * @return the source vertex of this edge
     */
    public Vertex getSource()
    {
        return this.source;
    }

    /**
     *
     * @return the destination vertex of this edge
     */
    public Vertex getDestination()
    {
        return this.destination;
    }

    /**
     * Sets the weight of the edge
     * @param weight new weight; nonNull
     */
    public void setWeight(int weight)
    {
        Objects.requireNonNull(weight);
        this.weight = weight;
    }

    /**
     * Compares this edge and arguments' weight
     * @param edge the edge to compare
     * @return the difference in weights
     */
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
