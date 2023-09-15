package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

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
        assertTrue(emptyQueueThatHadSomething().isEmpty());
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
        //Queue queue = new Queue();
        //String firstAddedObject = getFirstAddedObject();
        //String secondAddedObject = secondAddedObject();

        //queue.add(firstAddedObject);
        //queue.add(secondAddedObject);

        Queue queue = queueWithFirstAndSecondObject();
        assertEquals(queue.take(), firstAddedObject());
        assertEquals(queue.take(), secondAddedObject());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void test07HeadReturnsFirstAddedObject() {

        /*Queue queue = new Queue();
        String firstAddedObject = firstAddedObject();

        queue.add(firstAddedObject);
        queue.add(secondAddedObject());*/

        assertEquals(queueWithFirstAndSecondObject().head(), firstAddedObject());
    }

    @Test
    public void test08HeadDoesNotRemoveObjectFromQueue() {
        Queue queue = queueWithSomething();
        assertEquals(1, queue.size());
        queue.head();
        assertEquals(1, queue.size());
    }

    @Test
    public void test09SizeRepresentsObjectInTheQueue() {
        assertEquals(2, queueWithFirstAndSecondObject().size());
    }

    @Test
    public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                () -> {new Queue().take();});
    }

    @Test
    public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                         () -> {emptyQueueThatHadSomething().take();});
    }

    @Test
    public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                         () -> {new Queue().head();});
    }

    private static Queue emptyQueueThatHadSomething() {
        Queue queue = queueWithSomething();
        queue.take();
        return queue;
    }

    private static void assertThrowsLike(String message, Executable lambdaFunction) {
        assertEquals(message, assertThrows(Error.class, lambdaFunction).getMessage());
    }

    private static Queue queueWithFirstAndSecondObject() {
        return new Queue().add(firstAddedObject()).add(secondAddedObject());
    }

    private static String secondAddedObject() {
        return "Second";
    }

    private static String firstAddedObject() {
        return "First";
    }

    private static Queue queueWithSomething() {
        return new Queue().add(something());
    }

    private static String something() {
        return "Something";
    }

}