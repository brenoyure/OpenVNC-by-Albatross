package br.albatross.open.vnc.releases.runnables;

import br.albatross.open.vnc.releases.model.Release;
import br.albatross.open.vnc.releases.services.ReleasesService;
import br.albatross.open.vnc.services.Alerts;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.albatross.open.vnc.configurations.AvailableProperties.GITHUB_RELEASE_NAME;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

/**
 * <p>Responsável pela verificação (Manual, através das Configurações) de atualizações.</p>
 *
 * <p>Há um Timeout de 10 segundos caso não haja retorno do Serviço de Releases.</p>
 *
 * @see ReleasesService
 * @author Breno.Brito
 */
public class CheckForUpdatesRunnable implements Runnable {

    private final ExecutorService executorService;
    private final ReleasesService releasesService;
    private static final Logger logger = Logger.getLogger(CheckForUpdatesRunnable.class.getName());
    private static final byte CHECK_FOR_UPDATES_TIMEOUT_SECONDS = 10;
    private final ProgressBar checkForUpdatesProgressBar;
    private final Button manualCheckForUpdatesButton;

    public CheckForUpdatesRunnable(ExecutorService executorService, ReleasesService releasesService, ProgressBar checkForUpdatesProgressBar, Button manualCheckForUpdatesButton) {
        this.executorService = executorService;
        this.releasesService = releasesService;
        this.checkForUpdatesProgressBar = checkForUpdatesProgressBar;
        this.manualCheckForUpdatesButton = manualCheckForUpdatesButton;
    }

    @Override
    public void run() {

        Future<Release> releaseFuture = executorService.submit(() -> releasesService.getTheLatestStable().get());

        /*
         * Exibe a barra de progresso enquanto e desativa o botão de verificação de atualizações, enquanto
         * o executorService aguarda o retorno do ReleaseService.
         */
        Platform.runLater(() -> {
            manualCheckForUpdatesButton.setDisable(true);
            checkForUpdatesProgressBar.setVisible(true);
        });

        try {

            /*
             * Obtendo o retorno do Release, com um timeout, a partir do Future<Release> criado anteriormente.
             */
            Release release = releaseFuture.get(CHECK_FOR_UPDATES_TIMEOUT_SECONDS, TimeUnit.SECONDS);

            /*
             * Lógica para verificar se o release atual está atualizado.
             * Comparado o nome do último release da branch com o release atual.
             */
            boolean isUpdated =
                    release.getName().equals(GITHUB_RELEASE_NAME) && release.getBranch().equals("main");

            /*
             * Pelo fato do Alert ser um componente do JavaFX, utilizamos o Platform.runLater() para
             * que o mesmo seja exibido corretamente, visto que o Runtime do JavaFX exige que seus componentes
             * sejam exibidos e executados no seu próprio pool de threads.
             */
            if (isUpdated) {
                Platform.runLater(() ->
                    Alerts.newInstance(
                            INFORMATION,
                            "O OpenVNC já está atualizado",
                            "acesso-remoto-ok-64px.png",
                            "O OpenVNC já está atualizado, nenhuma ação necessária",
                            null)
                            .show()
                );

                return;
            }

            executorService.submit(new ShowAlertIfUpdateIsAvaliableRunnable(release));

        } catch (TimeoutException | ExecutionException | InterruptedException e) {

            String exceptionMessage = "Há um problema de conexão com à internet ou a API do \nrepositório remoto está temporariamente indisponível.";

            logger.log(Level.SEVERE, exceptionMessage, e);

            if (!releaseFuture.isDone()) {
                releaseFuture.cancel(true);
            }

            Platform.runLater(() -> {
                Alert alert = Alerts.newInstance(
                        ERROR,
                        "Serviço de Atualizações do OpenVNC",
                        "Ocorreu um Erro no Serviço de Atualizações",
                        exceptionMessage);
                alert.show();
            });

        } finally {
            Platform.runLater(() -> {
                manualCheckForUpdatesButton.setDisable(false);
                checkForUpdatesProgressBar.setVisible(false);
            });
        }

    }

}
