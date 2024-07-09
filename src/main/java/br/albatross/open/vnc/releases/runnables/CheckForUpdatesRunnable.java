package br.albatross.open.vnc.releases.runnables;

import java.util.concurrent.ExecutorService;

import br.albatross.open.vnc.releases.model.Release;
import br.albatross.open.vnc.releases.services.ReleasesService;

public class CheckForUpdatesRunnable implements Runnable {

    private final ExecutorService executorService;
    private final ReleasesService releasesService;

    public CheckForUpdatesRunnable(ExecutorService executorService, ReleasesService releasesService) {
        this.executorService = executorService;
        this.releasesService = releasesService;
    }

    @Override
    public void run() {

        Release release = releasesService.getTheLatestStable().get();
        boolean isUpdated = releasesService.isTheCurrentReleaseUpdated();

        if (!isUpdated) {
            executorService.submit(new ShowAlertIfUpdateIsAvaliableRunnable(release));
        }

        if (isUpdated) {
            executorService.submit(new ShowAlertIfNoUpdateIsAvaliableRunnable());
        }

    }

}
