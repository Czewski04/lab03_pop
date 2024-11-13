module lab03.pop {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    opens client to javafx.fxml;

    exports client;
}