package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class QueueTest {

    @Test
    public void test01QueueShouldBeEmptyWhenCreated() {
        assertTrue(new Queue().isEmpty());
    }

    @Test
    public void test02AddElementsToTheQueue() {
        assertFalse(queueWithSomething().isEmpty());
    }

    @Test
    public void test03AddedElementsIsAtHead() {
        assertEquals(something(), queueWithSomething().head());
    }

    @Test
    public void test04TakeRemovesElementsFromTheQueue() {
        Queue queue = queueWithSomething();
        queue.take();

        assertTrue(queue.isEmpty());
    }

    @Test
    public void test05TakeReturnsLastAddedObject() {

        //Queue queue = new Queue();
        //String addedObject = something();
        //queue.add(addedObject);

        //assertEquals(addedObject, queue.take());

        assertEquals(something(), queueWithSomething().take());

    }

    @Test
    public void test06QueueBehavesFIFO() {
        Queue queue = new Queue();
        String firstAddedObject = getFirstAddedObject();
        String secondAddedObject = secondAddedObject();

        queue.add(firstAddedObject);
        queue.add(secondAddedObject);

        assertEquals(queue.take(), firstAddedObject);
        assertEquals(queue.take(), secondAddedObject);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test07HeadReturnsFirstAddedObject() {
        Queue queue = new Queue();
        String firstAddedObject = getFirstAddedObject();

        queue.add(firstAddedObject);
        queue.add(secondAddedObject());

        assertEquals(queue.head(), firstAddedObject);
    }

    @Test
    public void test08HeadDoesNotRemoveObjectFromQueue() {
        Queue queue = new Queue();
        queue.add(something());
        assertEquals(1, queue.size());
        queue.head();
        assertEquals(1, queue.size());
    }

    @Test
    public void test09SizeRepresentsObjectInTheQueue() {
        assertEquals(2, new Queue().add(getFirstAddedObject()).add(secondAddedObject()).size());
    }

    @Test
    public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
        Queue queue = new Queue();
        try {
            queue.take();
            fail("Expected Error was not thrown.");
        } catch (Error e) {
            assertEquals("Queue is empty", e.getMessage());
        }
    }

    @Test
    public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() { //  tira error porq estoy haciendo size -2
        Queue queue = new Queue();
        queue.add(something());
        queue.take();
        try {
            queue.take();
            fail("Expected Error was not thrown.");
        } catch (Error e) {
            assertEquals("Queue is empty", e.getMessage());
        }
    }

    @Test
    public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
        Queue queue = new Queue();
        try {
            queue.head();
            fail("Expected Error was not thrown.");
        } catch (Error e) {
            assertEquals("Queue is empty", e.getMessage());
        }
    }


    private static String secondAddedObject() {
        return "Second";
    }

    private static String getFirstAddedObject() {
        return "First";
    }

    private static Queue queueWithSomething() {
        return new Queue().add(something());
    }

    private static String something() {
        return "Something";
    }


}