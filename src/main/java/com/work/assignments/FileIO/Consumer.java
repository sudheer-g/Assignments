package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private Logger logger = LogManager.getLogger(Consumer.class);
    private List<Result> resultList;
    private BlockingQueue<Query> blockingQueue;

    public Consumer(BlockingQueue<Query> blockingQueue, List<Result> resultList) {
        this.blockingQueue = blockingQueue;
        this.resultList = resultList;
    }

    private synchronized Query consume() {
        Query q = new Query();
        try {
            q = blockingQueue.take();
        } catch (InterruptedException e) {
            logger.error(e);
        }
        return q;
    }

    private synchronized void addResults(List<Result> results) {
        resultList.addAll(results);
    }

    private void execute() {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        Query q;
        try {
            while (true) {
                q = consume();
                if (q.directoryName == null || q.word == null) {
                    break;
                }
                addResults(fo.getWords(q));
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            execute();
            logger.debug("Consumer End");
        } catch (Throwable e) {
            logger.error(e);
        }
    }
}
