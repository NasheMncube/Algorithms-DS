/**
 * The following class implements a generic stack data structure.
 */
import javafx.beans.binding.ObjectExpression;

import java.util.Objects;

public class Stack<E> {
    public int size;
    private int top;

    private E[] elements;

    public Stack(int stackSize)
    {
        if(stackSize>0) this.size = stackSize;
        else throw new IllegalArgumentException("Invalid stack size");

        this.top = -1;

        this.elements = (E[]) new Object[stackSize];
    }

    /**
     * Push onto top of stack
     * @param pushElement value to push onto stack; notNull
     */
    public void push(E pushElement)
    {
        Objects.requireNonNull(pushElement);
        if(top == size-1)
            throw new IllegalStateException("Stack is full. Cannot push.");
        top++;
        elements[top] = pushElement;

    }

    /**
     * Pop off top value on stack and return
     * @return value on top of stack
     */
    public E pop()
    {
        if(top == -1)
            throw new IllegalStateException("Stack is empty. Cannot pop.");

        E ret = elements[top];
        top--;
        return ret;
    }

    /**
     * Look at top element of stack but don't modify stack
     * @return top element on stack
     */
    public E peek()
    {
        return elements[top];
    }

    /**
     * Determines if the stack is empty
     * @return boolean. If stack is empty returns true false otherwise
     */
    public boolean isEmpty() {

        return (top == -1);
    }

    /**
     * Determines if the stack is empty
     * @return boolean. If stack is full returns true, false otherwise.
     */
    public boolean isStackFull()
    {
        return(top == size-1);
    }

}
