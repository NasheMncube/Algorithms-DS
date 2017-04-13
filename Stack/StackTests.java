/**
 * Created by nashe on 13/04/2017.
 */
import org.junit.Assert;
import org.junit.Test;

public class StackTests {

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStackSizeThrows()
    {
        Stack<String> testStack = new Stack<>(-2);
    }

    @Test(expected = IllegalStateException.class)
    public void testPushingOnFullStackThrows()
    {
        Stack<String> testStack = new Stack<>(3);

        String[] stackValues = {"Jenny", "Forest", "Bubba", "Dan"};

        for(String name : stackValues)
            testStack.push(name); //Size of stack is 3. Number of elements 4
    }

    @Test(expected = IllegalStateException.class)
    public void testPoppingEmptyStackThrows()
    {
        Stack<String> testStack = new Stack<>(1);
        testStack.push("first");
        testStack.pop(); testStack.pop(); //Second pop is on an empty list
    }

    @Test(expected = NullPointerException.class)
    public void testPushingNullElementThrows()
    {
        Stack<String> testStack = new Stack<>(3);
        testStack.push(null);
    }

    @Test
    public void testPoppingStackReturnsCorrectObject()
    {
        Stack<String> testStack = new Stack<>(5);
        testStack.push("first");

        Assert.assertEquals("first", testStack.pop());
    }

    @Test
    public void testPushingOnStackIsInOrder() {
        String[] stackValues = {"Jenny", "Forest", "Bubba", "Dan"};
        Stack<String> testStack = new Stack<>(4);

        for (String s : stackValues)
            testStack.push(s);

        Assert.assertEquals("Dan", testStack.pop());
        Assert.assertEquals("Bubba", testStack.pop());
        Assert.assertEquals("Forest", testStack.pop());
        Assert.assertEquals("Jenny", testStack.pop());
    }

    @Test
    public void testIsEmptyCorrectlyIdentifiesEmptyStack()
    {
        String[] stackValues = {"Jenny", "Forest"};
        Stack<String> testStack = new Stack<>(2);

        for (String s : stackValues)
            testStack.push(s);

        Assert.assertEquals(false, testStack.isEmpty());
        testStack.pop(); testStack.pop();
        Assert.assertEquals(true, testStack.isEmpty());
    }

    @Test
    public void testIsStackFullCorrectlyIdentifiesFullStack()
    {
        Stack<String> testStack = new Stack<>(2);

        testStack.push("first");
        Assert.assertEquals(false, testStack.isStackFull());
        testStack.push("second");
        Assert.assertEquals(true, testStack.isStackFull());
    }

    @Test
    public void testPeekReturnsTopStackValueWithoutModification()
    {
        String[] stackValues = {"Jenny", "Forest"};
        Stack<String> testStack = new Stack<>(2);

        for (String s : stackValues)
            testStack.push(s);

        Assert.assertEquals("Forest", testStack.peek());
        Assert.assertEquals(true, testStack.isStackFull());

    }
}
