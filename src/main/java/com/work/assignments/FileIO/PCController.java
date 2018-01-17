package com.work.assignments.FileIO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class PCController {
    private AtomicBoolean end = new AtomicBoolean(false);

    public List<Result> wordSearch(Query query) throws InterruptedException{
        BlockingQueue<Query> blockingQueue = new ArrayBlockingQueue<>(10);
        List<Result> resultList = Collections.synchronizedList(new ArrayList<>());
        int consumerCount = 4;
        Thread producerThread = new Thread(new Producer(blockingQueue, query));
        Thread[] consumers = new Thread[consumerCount];
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new Consumer(blockingQueue, resultList, end));
        }
        producerThread.start();
        for(Thread consumer : consumers) {
            consumer.start();
        }
        producerThread.join();
        end.set(true);
        //consumers[2].interrupt();
        for(Thread consumer : consumers) {
            consumer.join();
        }
        return resultList;
    }
}
