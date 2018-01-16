package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private Logger logger = LogManager.getLogger(Consumer.class);
    private List<Result> resultList;
    private BlockingQueue<Query> blockingQueue;
    private AtomicBoolean end;

    public Consumer(BlockingQueue<Query> blockingQueue, List<Result> resultList, AtomicBoolean end) {
        this.blockingQueue = blockingQueue;
        this.resultList = resultList;
        this.end = end;
    }

    private Query consume() throws InterruptedException {
        Query q = blockingQueue.poll(100,TimeUnit.MILLISECONDS);
        logger.debug("Consumer " + this + " took: " + q);
        return q;
    }

    private void execute() throws InterruptedException {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        Query q;
        List<Result> results;
        while (true) {
            if (blockingQueue.isEmpty() && end.get()) {
                break;
            }
            q = consume();
            if (q != null) {
                results = fo.search(q);
                resultList.addAll(results);
            }
        }
    }

    @Override
    public void run() {
        try {
            execute();
            logger.debug("Consumer End");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
