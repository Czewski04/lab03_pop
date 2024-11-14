package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ClientLoggerController {

    @FXML
    private TextField clientLoginTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchClientView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/client/clientView.fxml")));
        root = fxmlLoader.load();

        ClientController controller = fxmlLoader.getController();
        controller.setClientLogin(readLogin());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public int readLogin(){
        Client client = new Client();
        client.setId((Integer.parseInt(clientLoginTxtField.getText())));
        return client.getId();
    }
}
