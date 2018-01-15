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

    private Query consume() throws InterruptedException {
        Query q = blockingQueue.take();
        logger.debug("Consumer " + this + " took: " + q);
        return q;
    }

    private void addPoisonPill() {
        try {
            blockingQueue.put(new Query(null, null, false));
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void execute() throws InterruptedException {
        DirectoryWordOccurrences fo = new DirectoryWordOccurrences();
        Query q;
        List<Result> results;
        while (true) {
            q = consume();
            if (q.directoryOrFile == null || q.word == null) {
                addPoisonPill();
                break;
            }
            results = fo.search(q);
            resultList.addAll(results);
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
