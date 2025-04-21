package org.example;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TaskMethod {

    private Queue<Task> taskQueue;

    public TaskMethod() {
        taskQueue = new LinkedList<>();
    }

    public void taskInitialize() {
        taskQueue.add(new Task("Clean dishes", 3));
        taskQueue.add(new Task("Do laundry", 2));
        taskQueue.add(new Task("Walk the dog", 1));
        taskQueue.add(new Task("Pay bills", 2));
    }

    public void addTaskToQueue(Task task) {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        taskQueue.add(task);
    }

    public Task processNextTaskFromQueue() {
            return taskQueue.poll();
    }

    public Task peekAtNextTaskInQueue() {
            return taskQueue.peek();
    }

    public int getTaskQueueSize() {
        return taskQueue.size();
    }

    public List<Task> getTasksWithPriority(int priority) {
        return taskQueue.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }

    public void moveTaskToBack(int numberOfPositions) {
        if (numberOfPositions <= 0) {
            throw new IllegalArgumentException();
        }
        Task taskToMove = taskQueue.poll();
        List<Task> tasksToBeReAdded = new ArrayList<>(taskQueue.size()-numberOfPositions);

        for (int i = 1 ; i <= taskQueue.size()-numberOfPositions ; i++) {
            tasksToBeReAdded.add(taskQueue.poll());
        }
        taskQueue.add(taskToMove);

        taskQueue.addAll(tasksToBeReAdded);


    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


    }
}
