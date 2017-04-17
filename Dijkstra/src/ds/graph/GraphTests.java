package ds.graph;

/**
 * Created by nashe on 17/04/2017.
 */
import org.junit.Assert;
import org.junit.Test;

public class GraphTests {

    @Test(expected = NullPointerException.class)
    public void addingVertexWithEmptyLableThrows()
    {
        Vertex testVertex = new Vertex(null);
    }

}
