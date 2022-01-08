module ru.wsr.wsr_connect {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens ru.wsr.wsr_connect to javafx.fxml;
    opens ru.wsr.wsr_connect.signComponents to javafx.fxml;

    exports ru.wsr.wsr_connect;
    exports ru.wsr.wsr_connect.signComponents;
}