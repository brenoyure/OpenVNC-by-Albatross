package br.albatross.open.vnc.runnables;

import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;

@Singleton
public class OpenVNCThreadPoolsProducer {

    private final ExecutorService executorService = newCachedThreadPool();
    private final ScheduledExecutorService scheduledThreadPool = newSingleThreadScheduledExecutor();

    @Produces
    @OpenVNCThreadPool
    public ExecutorService getOpenVNCThreadPool() {
        return this.executorService;
    }

    @Produces
    @OpenVNCScheduledUpdateThreadPool
    public ScheduledExecutorService getOpenVNCScheduledUpdatesThreadPool() {
        return this.scheduledThreadPool;
    }

}
