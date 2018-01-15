package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private Logger logger = LogManager.getLogger(Producer.class);
    private BlockingQueue<Query> blockingQueue;
    private Iterator<Query> queryIterator;

    Producer(BlockingQueue<Query> blockingQueue, Iterator<Query> queryIterator) {
        this.blockingQueue = blockingQueue;
        this.queryIterator = queryIterator;
    }

    private synchronized boolean nextQueryExists() {
        return queryIterator.hasNext();
    }

    private synchronized Query getQuery() {
        if (nextQueryExists()) {
            return queryIterator.next();
        }
        return null;
    }

    private synchronized void removeQuery() {
        queryIterator.remove();
    }

    private synchronized void produce(Query q) {
        try {
            blockingQueue.put(q);
            logger.debug("Producer " + this + " put: " + q);
            if (q.directoryOrFile != null) {
                removeQuery();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }

    private void execute() {
        try {
            Query q;
            while (nextQueryExists()) {
                q = getQuery();
                if (q != null) {
                    produce(q);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            logger.info("hit producer " + this);
            execute();
            logger.debug("Producer end");
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
