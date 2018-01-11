package com.work.assignments.FileIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
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
            Iterator<Query> iterator = queryList.iterator();
            while (iterator.hasNext()) {
                logger.info("hit before put " + blockingQueue + queryList);
                blockingQueue.put(iterator.next());
                //logger.info("hit after put " + blockingQueue + queryList);
                iterator.remove();
                //logger.info("hit after remove " + blockingQueue + queryList);
            }
            blockingQueue.put(new Query(null, null, false));
            blockingQueue.put(new Query(null, null, false));
            logger.info("EndLoop : QUEUE: " + blockingQueue + " QUERYLIST: "+ queryList);
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error(e);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            logger.info("hit producer " + this);
            produce();
            logger.debug("Producer end");
        }
        catch (Throwable e) {
            e.printStackTrace();
            logger.error(e);
        }
    }
}
