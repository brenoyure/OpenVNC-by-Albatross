package br.albatross.open.vnc.releases.runnables;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ShowAlertIfNoUpdateIsAvaliableRunnable implements Runnable {

    @Override
    public void run() {

        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            ImageView image = new ImageView("acesso-remoto-ok-64px.png");
            image.setFitHeight(64);
            image.setFitWidth(64);

            alert.setGraphic(image);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new ImageView(APP_ICON_RESOURCE_PATH).getImage());

            alert.setTitle("OpenVNC está atualizado");
            alert.setHeaderText("O OpenVNC está atualizado, nenhuma ação necessária");
            alert.show();
        });

    }

}
