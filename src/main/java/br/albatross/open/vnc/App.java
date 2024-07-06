package br.albatross.open.vnc;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;
import static br.albatross.open.vnc.configurations.AvailableProperties.DEV_GITHUB_PAGE_LINK;
import static java.util.concurrent.Executors.newCachedThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static final ExecutorService executorService = newCachedThreadPool();

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(APP_MAIN_WINDOW_TITLE);
        stage.getIcons().add(new Image(APP_ICON_RESOURCE_PATH));
        stage.show();

        StringBuilder sb = new StringBuilder();

        sb.append("\n");
        sb.append("######################################\n");
        sb.append("#                                    #\n");
        sb.append("#        ");
        sb.append(APP_MAIN_WINDOW_TITLE);
        sb.append("        #");
        sb.append("\n");
        sb.append("#    ");
        sb.append(DEV_GITHUB_PAGE_LINK);
        sb.append("    #\n");
        sb.append("#                                    #\n");
        sb.append("######################################\n");

        System.out.println(sb.toString());

   }

    @Override
    public void stop() throws Exception {
        System.out.println("Desligando Thread Pool do ExecutorService...");
        executorService.shutdown();
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
