package br.albatross.open.vnc.releases.runnables;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;
import static br.albatross.open.vnc.configurations.AvailableProperties.IS_WINDOWS_OS;
import static java.awt.Desktop.getDesktop;
import static java.lang.Runtime.getRuntime;

import java.io.IOException;
import java.net.URI;

import br.albatross.open.vnc.releases.model.Release;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class ShowAlertIfUpdateIsAvaliableRunnable implements Runnable {

    private final Release release;

    public ShowAlertIfUpdateIsAvaliableRunnable(Release release) {
        this.release = release;
    }

    @Override
    public void run() {

        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            ImageView image = new ImageView(APP_ICON_RESOURCE_PATH);
            image.setFitHeight(64);
            image.setFitWidth(64);

            alert.setGraphic(image);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(image.getImage());

            alert.setTitle("Nova Versão do OpenVNC está disponível");
            alert.setHeaderText("Nova Versão do OpenVNC está disponível");

            TextArea textArea = new TextArea(release.getDescription());
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait().ifPresent(b -> {
                if (b.equals(ButtonType.OK)) {
                    try {

                        if (IS_WINDOWS_OS) {
                            getDesktop().browse(URI.create(release.getUrl()));
                            return;
                        }

                        String[] cmdArray = { "browse", release.getUrl() };
                        getRuntime().exec(cmdArray);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        });

    }

}
