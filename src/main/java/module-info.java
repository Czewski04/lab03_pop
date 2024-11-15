module lab03.pop {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;
    requires java.desktop;

    opens client to javafx.fxml;
    opens seller to javafx.fxml;
    opens organizer to javafx.fxml;

    exports client;
    exports seller;
    exports others;
    exports organizer;
}