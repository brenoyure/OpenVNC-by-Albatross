package br.albatross.open.vnc.releases.runnables;

import java.util.concurrent.ExecutorService;

import br.albatross.open.vnc.releases.model.Release;
import br.albatross.open.vnc.releases.services.ReleasesService;
import br.albatross.open.vnc.services.configurations.Configuration;

/**
 *
 * Automatically checks for updates after startup if enabled in {@link Configuration}
 *
 * @author Breno.Brito
 */
public class AutoCheckForUpdates implements Runnable {

    private final Configuration configuration;
    private final ExecutorService executorService;
    private final ReleasesService releasesService;

    public AutoCheckForUpdates(Configuration configuration, ExecutorService executorService, ReleasesService releasesService) {
        this.configuration = configuration;
        this.executorService = executorService;
        this.releasesService = releasesService;
    }

    @Override
    public void run() {

        if (configuration.isCheckForUpdatesEnabledAtStartUp()) {

            System.out.println("Verificando se há atualizações...");

            if (!releasesService.isTheCurrentReleaseUpdated()) {

                Release release = releasesService.getTheLatestStable().get();
                executorService.submit(new ShowAlertIfUpdateIsAvaliableRunnable(release));
                System.out.println("Nova Versão do OpenVNC está disponível");
                System.out.println("Visite " + release.getUrl());
                return;

            }

            System.out.println("OpenVNC já está atualizado");

        }

    }

}
