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

    private void execute(Query q) {
        //logger.info("hit execute");
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        resultList.addAll(fo.getWords(q));
        //logger.info("hit before result log");
        //logger.info(resultList);
        //logger.info("hit after log");
    }

    private synchronized void consume() {
        try {
            while (true) {
                //logger.info("hit consume loop: " + count);
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

    @Override
    public void run() {
        try {
            //logger.debug("Hit Consumer" + this);
            consume();
            //logger.debug("Consumer End");
        }
        catch (Throwable e) {
            logger.error(e);
        }
    }
}
