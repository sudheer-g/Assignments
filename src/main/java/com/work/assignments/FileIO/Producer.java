package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private Logger logger = LogManager.getLogger(Producer.class);
    private BlockingQueue<Query> blockingQueue = null;
    public Producer(BlockingQueue<Query> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private Query produce() {
        return new Query("sampleDirectory", "This is", false);
    }
    @Override
    public void run() {
        try {
            while (true) {
                blockingQueue.put(produce());
                break;
            }
            blockingQueue.put(new Query(null, null, false));
        }
        catch (InterruptedException e) {
            logger.error(e);
        }

    }
}
