package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private Logger logger = LogManager.getLogger(Consumer.class);
    private volatile int count = 0;
    private List<Result> resultList = new ArrayList<>();
    private BlockingQueue<Query> blockingQueue;

    public Consumer(BlockingQueue<Query> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    private void execute(Query q) {
        logger.info("hit execute");
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        List<Result> resultList = fo.getDirectoryWordOccurrences(q.directoryName, q.word, q.recursive);
        Collections.sort(resultList);
        //logger.info("hit before result log");
        logger.info(resultList);
        //logger.info("hit after log");
    }

    private synchronized void consume() {
        try {
            while (true) {
                logger.info("hit consume loop: " + count);
                Query q = blockingQueue.take();
                if (q.directoryName == null || q.word == null) {
                    break;
                }
                count++;
                execute(q);
            }
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }

    @Override
    public void run() {
        try {
            logger.debug("Hit Consumer" + this);
            consume();
            logger.debug("Consumer End");
        }
        catch (Throwable e) {
            logger.error(e);
        }
    }
}
