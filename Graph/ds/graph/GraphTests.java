package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */
import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class GraphTests {

    @Test(expected = NullPointerException.class)
    public void addingVertexWithEmptyNullLabelThrows()
    {
        Vertex testVertex = new Vertex(null);
    }

    @Test(expected = NullPointerException.class)
    public void addingEdgeWithNullEdgeThrow()
    {
        Vertex testVertex = new Vertex("A");

        testVertex.addEdge(null);
    }

    @Test(expected = NullPointerException.class)
    public void testingContainsEdgeWithNullThrows()
    {
        Vertex testVertex = new Vertex("A");

        testVertex.containsEdge(null);
    }

    @Test(expected = NullPointerException.class)
    public void getEdgeWithNullArgumentThrows()
    {
        Vertex testVertex = new Vertex("A");

        testVertex.getEdge(null);
    }


    @Test (expected = UnsupportedOperationException.class)
    public void testGetEdgesReturnsUnmodifiableList()
    {
        Vertex testVertex = new Vertex("A");

        List<Edge> edgesFromTestVertex = testVertex.getEdgesFromVertex();

        edgesFromTestVertex.add(new Edge(testVertex, new Vertex("B")));
    }

    @Test
    public void comparingVertexObjectsCorrect()
    {
        Vertex testVertex = new Vertex("A");

        Vertex comparisonVertex1  = new Vertex("A");
        Vertex comparisonVertex2  = new Vertex("B");
        Object[] comparisonObject = {null};

        Assert.assertTrue(testVertex.compareWithObject(comparisonVertex1));
        Assert.assertFalse(testVertex.compareWithObject(comparisonVertex2));
        Assert.assertFalse(testVertex.compareWithObject(comparisonObject));
    }

    @Test
    public void comparingEdgeObjectsCorrect()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        Edge testEdge = new Edge(v1, v2, 1);

        Edge edge1   = new Edge(v1, v2, 1);
        Edge edge2   = new Edge(v1, v2);
        Edge edge3   = new Edge(v1, v2, 5);
        Edge edge4   = new Edge(v1, v3);
        Edge edge5   = new Edge(v1, v2, -42);
        Object[] obj = {null};

        Assert.assertTrue(testEdge.compare(edge1));
        Assert.assertTrue(testEdge.compare(edge2));
        Assert.assertFalse(testEdge.compare(edge3));
        Assert.assertFalse(testEdge.compare(edge4));
        Assert.assertTrue(testEdge.compare(edge5));
        Assert.assertFalse(testEdge.compare(obj));
    }

    @Test
    public void addingEdgesToGraphIsCorrect()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        Graph testGraph = new Graph(v1, v2, v3);

        Boolean b1 = testGraph.addEdge(v1, v2); //True
        Boolean b2 = testGraph.addEdge(v2, v3); //True
        Boolean b3 = testGraph.addEdge(v1, v1); //False
        Boolean b4 = testGraph.addEdge(v1, v3, 5); //True
        Boolean b5 = testGraph.addEdge(v2, v3); //False

        Assert.assertTrue(b1);Assert.assertTrue(b2);Assert.assertFalse(b3);
        Assert.assertTrue(b4);Assert.assertFalse(b5);

    }

    @Test
    public void containsEdgeWorks()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");

        Graph testGraph = new Graph(v1, v2);

        testGraph.addEdge(v1,v2);

        Assert.assertTrue(testGraph.containsEdge(new Edge(v1, v2)));
    }

    @Test
    public void removeEdgeWorks()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");

        Graph testGraph = new Graph(v1, v2);
        Edge testEdge   = new Edge(v1, v2);

        testGraph.addEdge(v1, v2);
        Assert.assertTrue(testGraph.containsEdge(testEdge));

        testGraph.removeEdge(testEdge);
        Assert.assertFalse(testGraph.containsEdge(testEdge));
    }

    @Test
    public void removeVertexWorks()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");

        Graph testGraph = new Graph(v1, v2, v3);

        testGraph.addEdge(v1, v2);
        testGraph.addEdge(v2, v3);
        testGraph.addEdge(v1,v3);
        testGraph.addEdge(v3, v1);

        testGraph.removeVertex(v1.getLabel());

        Assert.assertTrue(testGraph.containsEdge(new Edge(v2,v3)));
        Assert.assertFalse(testGraph.containsEdge(new Edge(v1, v2)));
        Assert.assertFalse(testGraph.containsEdge(new Edge(v3, v1)));
        Assert.assertFalse(testGraph.containsEdge(new Edge(v1, v3)));

    }

    @Test(expected = IllegalArgumentException.class)
    public void removingNonExistingVertexThrows()
    {
        Vertex v1 = new Vertex("A");

        Graph testGraph = new Graph(v1);

        testGraph.removeVertex(v1.getLabel());
        testGraph.removeVertex(v1.getLabel()); //No longer within graph
    }

    @Test
    public void addVertexWorks()
    {
        Vertex v1 = new Vertex("A");
        Vertex v2 = new Vertex("B");
        Vertex v3 = new Vertex("C");
        Vertex v4 = new Vertex("D");

        Graph testGraph = new Graph(v1, v2, v3);

        testGraph.addVertex(v4);

        Assert.assertTrue(testGraph.containsVertex(v4));
    }

}
