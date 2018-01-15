package com.work.assignments.FileIO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCController {
    public List<Result> wordSearch(Query query) {
        BlockingQueue<Query> blockingQueue = new ArrayBlockingQueue<>(10);
        List<Result> resultList = Collections.synchronizedList(new ArrayList<>());
        int consumerCount = 4;
        Thread producerThread = new Thread(new Producer(blockingQueue, query));
        Thread[] consumers = new Thread[consumerCount];
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new Thread(new Consumer(blockingQueue, resultList));
        }
        try {
            producerThread.start();
            for(Thread consumer : consumers) {
                consumer.start();
            }
            producerThread.join();
            blockingQueue.put(new Query(null, null, false));
            for(Thread consumer : consumers) {
                consumer.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
