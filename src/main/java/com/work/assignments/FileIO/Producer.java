package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private Logger logger = LogManager.getLogger(Producer.class);
    private BlockingQueue<Query> blockingQueue;
    private Query query;

    Producer(BlockingQueue<Query> blockingQueue, Query query) {
        this.blockingQueue = blockingQueue;
        this.query = query;
    }

    private void addToQueue(Query q) {
        try {
            blockingQueue.put(q);
            logger.debug("Adding query {} by producer {}", q, this);
        } catch (InterruptedException e) {
            logger.error(e);
        }
    }


    private void execute(Query q) {
        File folder = new File(q.directoryOrFile);
        File[] listOfFiles = folder.listFiles();
        if(listOfFiles!= null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    addToQueue(new Query(q.directoryOrFile + '/' + file.getName(), q.word, false));
                } else {
                    execute(new Query(q.directoryOrFile + '/' + file.getName(), q.word, q.recursive));
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            logger.info("hit producer " + this);
            execute(query);
            logger.debug("Producer end");
        } catch (Throwable e) {
            logger.error(e);
        }
    }
}
