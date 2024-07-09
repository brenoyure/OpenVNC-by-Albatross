package br.albatross.open.vnc.services;

import br.albatross.open.vnc.configurations.AvailableProperties;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import static br.albatross.open.vnc.configurations.AvailableProperties.APP_ICON_RESOURCE_PATH;

public abstract class Alerts {

    private Alerts() {

    }

    public static Alert newInstance(Alert.AlertType alertType, String title, String header) {
        return newInstance(alertType, title, header, null);
    }

    public static Alert newInstance(Alert.AlertType alertType, String title, String headerText, String contentText) {

        return newInstance(alertType, title, AvailableProperties.APP_ICON_RESOURCE_PATH, headerText, contentText);

    }

    public static Alert newInstance(Alert.AlertType alertType, String title, String imagePath, String headerText, String contentText) {

        Alert alert = new Alert(alertType);

        ImageView image = new ImageView(imagePath);
        image.setFitHeight(64);
        image.setFitWidth(64);

        if (!(alert.getAlertType().equals(Alert.AlertType.ERROR) || alert.getAlertType().equals(Alert.AlertType.WARNING))) {
            alert.setGraphic(image);
        }

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(image.getImage());

        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);

        return alert;

    }

}
