package com.work.assignments.FileIO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PCController {
    public List<Result> wordSearch(List<Query> queryList) {
        BlockingQueue<Query> blockingQueue = new ArrayBlockingQueue<>(3);
        List<Result> resultList = new ArrayList<>();
        Thread producerThread = new Thread(new Producer(blockingQueue, queryList.iterator()));
        Thread producerThread2 = new Thread(new Producer(blockingQueue, queryList.iterator()));
        Thread consumerThread = new Thread(new Consumer(blockingQueue, resultList));
        Thread consumerThreadTwo = new Thread(new Consumer(blockingQueue, resultList));
        try {
            producerThread.start();
            //producerThread2.start();
            consumerThread.start();
            consumerThreadTwo.start();
            producerThread.join();
            //producerThread2.join();
            blockingQueue.put(new Query(null, null, false));
            consumerThread.join();
            consumerThreadTwo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
