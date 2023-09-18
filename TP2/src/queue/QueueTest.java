package queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueueTest {

    @Test
    public void test01QueueShouldBeEmptyWhenCreated() {
        assertTrue(new Queue().isEmpty());
    }

    @Test
    public void test02AddElementsToTheQueue() {
        assertFalse(queueWithAString().isEmpty());
    }

    @Test
    public void test03AddedElementsIsAtHead() {
        assertEquals(aString(), queueWithAString().head());

    }

    @Test
    public void test04TakeRemovesElementsFromTheQueue() {
        assertTrue(emptyQueueThatHadAString().isEmpty());
    }

    @Test
    public void test05TakeReturnsLastAddedObject() {
        assertEquals(aString(), queueWithAString().take());

    }

    @Test
    public void test06QueueBehavesFIFO() {
        Queue queue = queueWithFirstAndSecondObject();

        assertEquals(firstObject(), queue.take());
        assertEquals(secondObject(), queue.take());

        assertTrue(queue.isEmpty());
    }

    @Test
    public void test07HeadReturnsFirstAddedObject() {

        assertEquals(firstObject(), queueWithFirstAndSecondObject().head());

    }

    @Test
    public void test08HeadDoesNotRemoveObjectFromQueue() {
        Queue queue = queueWithAString();

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
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE_ERROR_MESSAGE,
                () -> new Queue().take());
    }

    @Test
    public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE_ERROR_MESSAGE,
                () -> emptyQueueThatHadAString().take());
    }

    @Test
    public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE_ERROR_MESSAGE,
                () -> new Queue().head());
    }


    private void assertThrowsLike(String expectedErrorMessage, Executable errorfulAction) {
        assertEquals(expectedErrorMessage, assertThrows(Error.class, errorfulAction).getMessage());
    }

    private Queue queueWithFirstAndSecondObject() {
        return new Queue().add(firstObject()).add(secondObject());
    }

    private String secondObject() {
        return "Second";
    }

    private String firstObject() {
        return "First";
    }

    private Queue emptyQueueThatHadAString() {
        Queue queue = queueWithAString();
        queue.take();
        return queue;
    }

    private Queue queueWithAString() {
        return new Queue().add(aString());
    }

    private String aString() {
        return "Something";
    }

}