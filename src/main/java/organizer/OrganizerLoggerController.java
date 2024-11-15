package organizer;


import client.Client;
import client.ClientOffersController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class OrganizerLoggerController {

    @FXML
    private TextField organizerLoginTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerOrdersView.fxml")));

        root = fxmlLoader.load();

        OrganizerOrdersController controller = fxmlLoader.getController();
        controller.setClientLogin(readLogin());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public int readLogin(){
        Organizer organizer = new Organizer();
        organizer.setId((Integer.parseInt(organizerLoginTxtField.getText())));
        return organizer.getId();
    }
}
