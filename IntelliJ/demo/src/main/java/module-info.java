module dk.easv.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens dk.easv.demo to javafx.fxml;
    exports dk.easv.demo;
}