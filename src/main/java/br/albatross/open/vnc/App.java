package br.albatross.open.vnc;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.APP_MAIN_WINDOW_TITLE;

import java.io.IOException;

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

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(APP_MAIN_WINDOW_TITLE);
        stage.getIcons().add(new Image(APP_ICON_RESOURCE_PATH));
        stage.show();
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
