package assignmentOne;

import static org.junit.Assert.*;

public class QueueTest {

    private ProcessObject first;
    private ProcessObject second;
    private ProcessObject third;
    private Queue queue;


    @org.junit.Before
    public void setUp() throws Exception {
        first = new ProcessObject("First", "Joel", 12345, 1, 5, 120);
        second = new ProcessObject("Second", "Nathan", 34567, 4, 2, 45);
        third = new ProcessObject("Third", "Bradley", 56789, 2, 10, 60);
        queue = new Queue();
    }

    @org.junit.Test
    public void add() {
        // test that empty queue has a capacity of 2 to begin with
        assertTrue(queue.getSize() == 0);
        assertTrue(queue.getCapacity() == 2);
        // test that data is added to queue
        queue.add(first);
        assertTrue(queue.getSize() == 1);
        assertTrue(queue.getCapacity() == 2);
        queue.add(second);
        assertTrue(queue.getSize() == 2);
        assertTrue(queue.getCapacity() == 2);
        // test that queue capacity doubles when needed
        queue.add(third);
        assertTrue(queue.getSize() == 3);
        assertTrue(queue.getCapacity() == 4);
        queue.add(first);
        assertTrue(queue.getSize() == 4);
        assertTrue(queue.getCapacity() == 4);
        queue.add(second);
        assertTrue(queue.getSize() == 5);
        assertTrue(queue.getCapacity() == 8);
    }

    @org.junit.Test
    public void remove() {
        ProcessObject testObject;
        queue.add(first);
        queue.add(second);
        queue.add(third);
        assertTrue(queue.getSize() == 3);
        assertTrue(queue.getCapacity() == 4);
        // test that correct data is removed from queue (FIFO)
        testObject = queue.remove();
        assertTrue(queue.getSize() == 2);
        assertTrue(queue.getCapacity() == 4);
        assertTrue(testObject.getName().equals("First"));
        testObject = queue.remove();
        assertTrue(queue.getSize() == 1);
        assertTrue(queue.getCapacity() == 4);
        assertTrue(testObject.getName().equals("Second"));
        testObject = queue.remove();
        assertTrue(queue.getSize() == 0);
        assertTrue(queue.getCapacity() == 4);
        assertTrue(testObject.getName().equals("Third"));
        // test that nothing is returned if queue is empty and that queue size and capacity remain the same
        testObject = queue.remove();
        assertTrue(testObject == null);
        assertTrue(queue.getSize() == 0);
        assertTrue(queue.getCapacity() == 4);
    }

    @org.junit.Test
    public void peek() {
        // test that nothing is returned if queue is empty
        ProcessObject testObject;
        testObject = queue.peek();
        assertTrue(testObject == null);
        // test that data is returned, but not removed from queue
        queue.add(first);
        testObject = queue.peek();
        assertTrue(testObject.getName().equals("First"));
        assertTrue(queue.getSize() == 1);
        assertTrue(queue.getCapacity() == 2);
        // test that data is returned following rules of FIFO, and that data is not removed from queue
        queue.add(second);
        testObject = queue.peek();
        assertTrue(testObject.getName().equals("First"));
        assertTrue(queue.getSize() == 2);
        assertTrue(queue.getCapacity() == 2);
        // remove data, call peek, and test that correct data is returned following FIFO
        queue.remove();
        testObject = queue.peek();
        assertTrue(testObject.getName().equals("Second"));
        assertTrue(queue.getSize() == 1);
        assertTrue(queue.getCapacity() == 2);

    }

    @org.junit.Test
    public void print() {
        // print out data in queue, and ensure that order in queue is not altered even though printed not in FIFO order
        ProcessObject testObject;
        queue.add(first);
        queue.add(second);
        queue.add(third);
        queue.print(2);
        assertTrue(queue.peek().getName().equals("First"));
    }
}