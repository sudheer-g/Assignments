package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private Logger logger = LogManager.getLogger(Producer.class);
    private BlockingQueue<Query> blockingQueue;
    private volatile List<Query> queryList;
    Producer(BlockingQueue<Query> blockingQueue, List<Query> queryList) {
        this.blockingQueue = blockingQueue;
        this.queryList = queryList;
    }
    private synchronized void produce() {
        try {
            for (Query query : queryList) {
                blockingQueue.put(query);
                queryList.remove(query);
            }
            //blockingQueue.put(new Query(null, null, false));
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    @Override
    public void run() {
            produce();
    }
}
