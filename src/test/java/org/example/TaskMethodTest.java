package org.example;
import org.junit.Before;
import org.junit.Test;
import java.util.List; // Import the List interface
import org.junit.Assert.*;

import static org.junit.Assert.*;

public class TaskMethodTest {

    private TaskMethod taskMethod;
    private Task task1 = new Task("Clean dishes", 3);
    private Task task2 = new Task("Do laundry", 2);
    private Task task3 = new Task("Walk the dog", 1);
    private Task task4 = new Task("Pay bills", 2);

    @Before
    public void setUp() {
        taskMethod = new TaskMethod();
        taskMethod.taskInitialize(); // Initialize the queue
    }

    @Test
    public void testAddTaskToQueue_Success() {
        Task newTask = new Task("Write report", 1);
        taskMethod.addTaskToQueue(newTask);
        assertEquals(5, taskMethod.getTaskQueueSize());
        assertEquals(task1.toString(), taskMethod.peekAtNextTaskInQueue().toString()); // FIFO order
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddTaskToQueue_NullTask() {
        taskMethod.addTaskToQueue(null);
    }

    @Test
    public void testProcessNextTaskFromQueue_Success() {
        Task processedTask = taskMethod.processNextTaskFromQueue();
        assertEquals(task1.toString(), processedTask.toString());
        assertEquals(3, taskMethod.getTaskQueueSize());
        assertEquals(task2.toString(), taskMethod.peekAtNextTaskInQueue().toString()); // Next in line
    }

    @Test
    public void testProcessNextTaskFromQueue_EmptyQueue() {
        // Clear the queue
        for (int i = 0; i < 4; i++) {
            taskMethod.processNextTaskFromQueue();
        }
        assertNull(taskMethod.processNextTaskFromQueue());
        assertEquals(0, taskMethod.getTaskQueueSize());
    }

    @Test
    public void testPeekAtNextTaskInQueue() {
        Task nextTask = taskMethod.peekAtNextTaskInQueue();
        assertEquals(task1.toString(), nextTask.toString());
        assertEquals(4, taskMethod.getTaskQueueSize()); // Size unchanged by peek
    }

    @Test
    public void testGetTaskQueueSize() {
        assertEquals(4, taskMethod.getTaskQueueSize());
    }

    @Test
    public void testQueueOrder_FIFO() {
        assertEquals(task1.toString(), taskMethod.processNextTaskFromQueue().toString());
        assertEquals(task2.toString(), taskMethod.processNextTaskFromQueue().toString());
        assertEquals(task3.toString(), taskMethod.processNextTaskFromQueue().toString());
        assertEquals(task4.toString(), taskMethod.processNextTaskFromQueue().toString());
        assertEquals(0, taskMethod.getTaskQueueSize());
    }

    @Test
    public void testGetTasksWithPriority_Found() {
        List<Task> highPriorityTasks = taskMethod.getTasksWithPriority(3);
        assertEquals(1, highPriorityTasks.size());
        assertEquals(task1.toString(), highPriorityTasks.get(0).toString());
    }

    @Test
    public void testGetTasksWithPriority_NotFound() {
        List<Task> lowPriorityTasks = taskMethod.getTasksWithPriority(5);
        assertTrue(lowPriorityTasks.isEmpty());
    }

    @Test
    public void testMoveTaskToBack_ValidPositions() {
        taskMethod.moveTaskToBack(2); // Move the first task two positions back
        assertEquals(task3.toString(), taskMethod.processNextTaskFromQueue().toString()); // "Walk the dog"
        assertEquals(task4.toString(), taskMethod.processNextTaskFromQueue().toString()); // "Pay bills"
        assertEquals(task1.toString(), taskMethod.processNextTaskFromQueue().toString()); // "Clean dishes"
        assertEquals(task2.toString(), taskMethod.processNextTaskFromQueue().toString()); // "Do laundry"
        assertEquals(0, taskMethod.getTaskQueueSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveTaskToBack_InvalidPositions_Zero() {
        taskMethod.moveTaskToBack(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveTaskToBack_InvalidPositions_Negative() {
        taskMethod.moveTaskToBack(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMoveTaskToBack_InvalidPositions_TooLarge() {
        taskMethod.moveTaskToBack(4);
    }

}