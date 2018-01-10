package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private Logger logger = LogManager.getLogger(Consumer.class);

    private BlockingQueue<Query> blockingQueue;

    public Consumer(BlockingQueue<Query> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private synchronized void consume() {
        try {
            while (true) {
                Query q = blockingQueue.take();
                if (q.directoryName == null || q.word == null) {
                    break;
                }
                execute(q);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }
    private void execute(Query q) {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        List<Result> resultList = fo.getDirectoryWordOccurrences(q.directoryName, q.word, q.recursive);
        Collections.sort(resultList);
        logger.info(resultList);
    }

    @Override
    public void run() {
        logger.debug("Hello" + this);
        consume();
    }
}
