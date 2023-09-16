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
        assertEmptyQueue(new Queue());
    }

    @Test
    public void test02AddElementsToTheQueue() {
        assertFalse(queueWithSomething().isEmpty());
    }

    @Test
    public void test03AddedElementsIsAtHead() {
        assertEqualObjectAndHeadObjectFromQueue(something(), queueWithSomething());
    }

    @Test
    public void test04TakeRemovesElementsFromTheQueue() {
        assertEmptyQueue(emptyQueueThatHadSomething());
    }

    @Test
    public void test05TakeReturnsLastAddedObject() {
        assertEqualObjectAndTakenObjectFromQueue(something(), queueWithSomething());
    }

    @Test
    public void test06QueueBehavesFIFO() {
        Queue queue = queueWithFirstAndSecondObject();
        assertEqualObjectAndTakenObjectFromQueue(firstAddedObject(), queue);
        assertEqualObjectAndTakenObjectFromQueue(secondAddedObject(), queue);
        assertEmptyQueue(queue);
    }

    @Test
    public void test07HeadReturnsFirstAddedObject() {
        assertEqualObjectAndHeadObjectFromQueue(firstAddedObject(), queueWithFirstAndSecondObject());
    }

    @Test
    public void test08HeadDoesNotRemoveObjectFromQueue() {
        Queue queue = queueWithSomething();
        assertQueueSize(1, queue);
        queue.head();
        assertQueueSize(1, queue);
    }

    @Test
    public void test09SizeRepresentsObjectInTheQueue() {
        assertQueueSize(2, queueWithFirstAndSecondObject());
    }

    @Test
    public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                () -> new Queue().take());
    }

    @Test
    public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                () -> emptyQueueThatHadSomething().take());
    }

    @Test
    public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
        assertThrowsLike(EmptyContainer.EMPTY_QUEUE,
                () -> new Queue().head());
    }

    private void assertEqualObjectAndHeadObjectFromQueue(Object expectedHeadObject, Queue queue) {
        assertEquals(expectedHeadObject, queue.head());
    }

    private void assertQueueSize(int size, Queue queue) {
        assertEquals(size, queue.size());
    }

    private void assertEqualObjectAndTakenObjectFromQueue(Object expectedTakenObject, Queue queue) {
        assertEquals(expectedTakenObject, queue.take());
    }

    private void assertEmptyQueue(Queue queue) {
        assertTrue(queue.isEmpty());
    }

    private Queue emptyQueueThatHadSomething() {
        Queue queue = queueWithSomething();
        queue.take();
        return queue;
    }

    private void assertThrowsLike(String expectedErrorMessage, Executable lambdaFunction) {
        assertEquals(expectedErrorMessage, assertThrows(Error.class, lambdaFunction).getMessage());
    }

    private Queue queueWithFirstAndSecondObject() {
        return new Queue().add(firstAddedObject()).add(secondAddedObject());
    }

    private String secondAddedObject() {
        return "Second";
    }

    private String firstAddedObject() {
        return "First";
    }

    private Queue queueWithSomething() {
        return new Queue().add(something());
    }

    private String something() {
        return "Something";
    }

}