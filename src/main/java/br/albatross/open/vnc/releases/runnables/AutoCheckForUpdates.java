package br.albatross.open.vnc.releases.runnables;

import br.albatross.open.vnc.releases.model.Release;
import br.albatross.open.vnc.releases.services.ReleasesService;
import br.albatross.open.vnc.services.configurations.Configuration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.albatross.open.vnc.configurations.AvailableProperties.GITHUB_RELEASE_NAME;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * <p>Responsável pela verificação automática das atualizações ao iniciar o OpenVNC.</p>
 *
 * <p>Há um Timeout de 10 segundos caso não haja retorno do Serviço de Releases.</p>
 *
 * @see ReleasesService
 * @author Breno.Brito
 */
public class AutoCheckForUpdates implements Runnable {

    private final Configuration configuration;
    private final ExecutorService executorService;
    private final ReleasesService releasesService;
    private static final byte CHECK_FOR_UPDATES_TIMEOUT_SECONDS = 10;
    private static final Logger logger = Logger.getLogger(AutoCheckForUpdates.class.getName());

    public AutoCheckForUpdates(Configuration configuration, ExecutorService executorService, ReleasesService releasesService) {
        this.configuration = configuration;
        this.executorService = executorService;
        this.releasesService = releasesService;
    }

    @Override
    public void run() {

        if (configuration.isCheckForUpdatesEnabledAtStartUp()) {

            Future<Release> releaseFuture = executorService.submit(() -> releasesService.getTheLatestStable().get());

            System.out.println("Verificando se há atualizações...");

            try {

                Release release = releaseFuture.get(CHECK_FOR_UPDATES_TIMEOUT_SECONDS, SECONDS);

                if (!(release.getName().equals(GITHUB_RELEASE_NAME)) && release.getBranch().equals("main")) {
                    executorService.submit(new ShowAlertIfUpdateIsAvaliableRunnable(release));
                    System.out.println("Nova Versão do OpenVNC está disponível");
                    System.out.println("Visite " + release.getUrl());
                    return;
                }

                System.out.println("O OpenVNC já está atualizado.");


            } catch (TimeoutException | ExecutionException | InterruptedException e) {

                logger.log(Level.SEVERE, "O Serviço de Atualizações demorou muito para responder, ou há um problema de conexão com à internet ou a API do repositório remoto está temporariamente indisponível.", e);

                if (!releaseFuture.isDone()) {
                    releaseFuture.cancel(true);
                }

            }

        }

    }

}
