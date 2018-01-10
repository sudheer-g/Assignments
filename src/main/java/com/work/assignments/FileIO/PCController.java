package com.work.assignments.FileIO;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCController {
    public void startThreads(List<Query> queryList) {
        BlockingQueue<Query> blockingQueue = new ArrayBlockingQueue<>(1);

        Thread producerThread = new Thread(new Producer(blockingQueue, queryList));
        Thread consumerThread = new Thread(new Consumer(blockingQueue));
        Thread consumerThreadTwo = new Thread(new Consumer(blockingQueue));
        producerThread.start();
        consumerThread.start();
        consumerThreadTwo.start();
    }
}
