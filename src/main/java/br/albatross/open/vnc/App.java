package br.albatross.open.vnc;

import br.albatross.open.vnc.runnables.StartUpRoutinesRunnable;
import br.albatross.open.vnc.services.configurations.Configurations;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;
import static java.util.concurrent.Executors.newCachedThreadPool;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

/**
 * JavaFX App
 */
public class App extends Application {

    public static final ExecutorService executorService = newCachedThreadPool();
    private static final ScheduledExecutorService scheduledThreadPool = newSingleThreadScheduledExecutor();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(APP_MAIN_WINDOW_TITLE);
        stage.getIcons().add(new Image(APP_ICON_RESOURCE_PATH));
        stage.show();

        executorService.submit(new StartUpRoutinesRunnable(Configurations.getInstance(), executorService, scheduledThreadPool));

   }

    @Override
    public void stop() throws Exception {
        System.out.println("Desligando Thread Pool do ExecutorService...");
        executorService.shutdown();
        if (!scheduledThreadPool.isShutdown()) {
            scheduledThreadPool.shutdown();
        }
        System.out.println("Thread Pool do ExecutorService desligado com sucesso");
        System.out.println("Encerrando OpenVNC, obrigado por utilizar");
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
