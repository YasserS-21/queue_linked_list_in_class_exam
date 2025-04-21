package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class QueueMethod {

    private Queue<String> fruitsQueue; 

    public QueueMethod() {
        fruitsQueue = new LinkedList<>();
    }

    public void queueDemo() {
        fruitsQueue.add("apple");
        fruitsQueue.add("lemon");
        fruitsQueue.add("banana");
        fruitsQueue.add("orange");
    }

    public void addFruitToQueue(String fruit) {
        fruitsQueue.add(fruit);
    }

    public String removeFruitFromQueue() {
        return fruitsQueue.remove();
    }

    public String peekAtHeadOfQueue() {
        return fruitsQueue.peek();
    }

    public int getQueueSize() {
        return fruitsQueue.size();
    }

}
