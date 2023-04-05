module br.albatross.open.vnc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.base;
    requires javafx.graphics;
    requires java.desktop;

    opens br.albatross.open.vnc to javafx.fxml;
    exports br.albatross.open.vnc;
}
