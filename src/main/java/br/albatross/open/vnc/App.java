package br.albatross.open.vnc;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import br.albatross.open.vnc.runnables.OpenVNCScheduledUpdateThreadPool;
import br.albatross.open.vnc.runnables.OpenVNCThreadPool;
import br.albatross.open.vnc.runnables.StartUpRoutinesRunnable;
import br.albatross.open.vnc.services.configurations.Configuration;
import io.quarkiverse.fx.FxApplication;
import io.quarkiverse.fx.FxPostStartupEvent;
import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * OpenVNC by Albatross JavaFX App
 */
@QuarkusMain
public class App implements QuarkusApplication {

    @Inject
    FXMLLoader fxmlLoader;

    @Inject
    Configuration configuration;

    @Inject
    @OpenVNCThreadPool
    ExecutorService executorService;

    @Inject
    @OpenVNCScheduledUpdateThreadPool
    ScheduledExecutorService scheduledThreadPool;

    @Override
    public int run(String... args) throws Exception {
        Application.launch(FxApplication.class, args);
        stop();
        return 0;
    }

    public void start(@Observes final FxPostStartupEvent event) throws IOException {
        try (InputStream fxmlInputStream = App.class.getResourceAsStream("main.fxml")) {
            Parent fxmlParent = fxmlLoader.load(fxmlInputStream);
            Stage stage = event.getPrimaryStage();
            stage.setScene(new Scene(fxmlParent));
            stage.setResizable(false);
            stage.setTitle(APP_MAIN_WINDOW_TITLE);
            stage.getIcons().add(new Image(APP_ICON_RESOURCE_PATH));
            stage.show();            
        }

        executorService.submit(new StartUpRoutinesRunnable(configuration, executorService, scheduledThreadPool));

   }

    public void stop() throws Exception {
        Log.info("Desligando Thread Pool do ExecutorService...");
        executorService.shutdown();
        if (!scheduledThreadPool.isShutdown()) {
            scheduledThreadPool.shutdown();
        }
        Log.info("Thread Pool do ExecutorService desligado com sucesso");
        Log.info("Encerrando OpenVNC, obrigado por utilizar");
    }

}
