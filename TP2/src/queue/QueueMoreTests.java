package queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueMoreTests {

    @Test
    public void test11QueueWithOneElement() {
        Queue queue = new Queue();
        queue.add("Something");
        assertTrue(new Queue().isEmpty());
    }

    @Test
    public void test06QueueBehavesFIFO() {
        Queue queue = new Queue();
        String firstAddedObject = "First";
        String secondAddedObject = "Second";

        queue.add(firstAddedObject);
        queue.add(secondAddedObject);

        assertEquals(firstAddedObject, queue.take());
        assertEquals(secondAddedObject, queue.take());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test13AddedElementsIsAtHead() {
        assertEquals("Something", new Queue().add("Something").head());
    }

    @Test
    public void test14TakeRemovesElementsFromTheQueue() {
        Queue queue = new Queue().add("Something");
        queue.take();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void test15TakeReturnsLastAddedObject() {
        Queue queue = new Queue();
        String addedObject = "Something";
        queue.add(addedObject);

        assertEquals(addedObject, queue.take());
    }

    @Test
    public void test16QueueBehavesFIFO() {
        Queue queue = new Queue();
        String firstAddedObject = "First";
        String secondAddedObject = "Second";

        queue.add(firstAddedObject);
        queue.add(secondAddedObject);

        assertEquals(queue.take(), firstAddedObject);
        assertEquals(queue.take(), secondAddedObject);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test17HeadReturnsFirstAddedObject() {
        Queue queue = new Queue();
        String firstAddedObject = "First";

        queue.add(firstAddedObject);
        queue.add("Second");

        assertEquals(queue.head(), firstAddedObject);
    }
}
