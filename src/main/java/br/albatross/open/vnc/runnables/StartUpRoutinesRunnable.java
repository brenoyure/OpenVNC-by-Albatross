package br.albatross.open.vnc.runnables;

import br.albatross.open.vnc.releases.runnables.AutoCheckForUpdates;
import br.albatross.open.vnc.releases.services.ReleasesServiceGithubImplementation;
import br.albatross.open.vnc.services.configurations.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartUpRoutinesRunnable implements Runnable {

    private final Configuration configuration;
    private final ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    public StartUpRoutinesRunnable(Configuration configuration, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService) {
        this.configuration = configuration;
        this.executorService = executorService;
        this.scheduledExecutorService = scheduledExecutorService;
    }
    @Override
    public void run() {

        /**
         * Shows OpenVNC Logo at Console after Startup
         */
        executorService.submit(new ShowLogoOnConsoleRunnable());


        /**
         * Auto Check for Updates at Startup if enabled
         */
        scheduledExecutorService
                .schedule(
                        new AutoCheckForUpdates(
                                configuration,
                                executorService,
                                new ReleasesServiceGithubImplementation()),
                        5, TimeUnit.SECONDS);

    }
}
