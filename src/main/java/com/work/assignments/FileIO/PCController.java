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
        Thread consumerThread = new Thread(new Consumer(blockingQueue, resultList));
        Thread consumerThreadTwo = new Thread(new Consumer(blockingQueue, resultList));
        Thread consumerThreadThree = new Thread(new Consumer(blockingQueue, resultList));
        Thread consumerThreadFour = new Thread(new Consumer(blockingQueue, resultList));
        try {
            producerThread.start();
            consumerThread.start();
            consumerThreadTwo.start();
            consumerThreadThree.start();
            consumerThreadFour.start();
            producerThread.join();
            blockingQueue.put(new Query(null, null, false));
            consumerThread.join();
            consumerThreadTwo.join();
            consumerThreadThree.join();
            consumerThreadFour.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
