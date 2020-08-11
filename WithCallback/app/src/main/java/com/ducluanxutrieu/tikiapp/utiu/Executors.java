package com.ducluanxutrieu.tikiapp.utiu;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class Executors {
    static final int DEFAULT_THREAD_POOL_SIZE = 4;

    static int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    static int KEEP_ALIVE_TIME = 1;
    static TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    public static ExecutorService executorFixed = newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

    public static ExecutorService executorNewCached = newCachedThreadPool();

    public static ExecutorService executorNewSingle = newSingleThreadExecutor();

    static BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();
    public static ExecutorService executorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES * 2, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, taskQueue, new BackgroundThreadFactory());
}