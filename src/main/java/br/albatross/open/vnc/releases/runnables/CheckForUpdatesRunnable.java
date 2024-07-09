package br.albatross.open.vnc.releases.runnables;

import static br.albatross.open.vnc.configurations.AvailableProperties.GITHUB_RELEASE_NAME;

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
        boolean isUpdated = release.getName().equalsIgnoreCase(GITHUB_RELEASE_NAME);

        if (!isUpdated) {
            executorService.submit(new ShowAlertIfUpdateIsAvaliableRunnable(release));
        }

        if (isUpdated) {
            executorService.submit(new ShowAlertIfNoUpdateIsAvaliableRunnable());
        }

    }

}
