package organizer;


import client.Client;
import exception.WrongLoginException;
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

    public void switchToOrdersView(ActionEvent actionEvent, int login) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerOrdersView.fxml")));

        root = fxmlLoader.load();

        OrganizerOrdersController controller = fxmlLoader.getController();
        controller.setOrganizerLogin(login);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void readLogin(ActionEvent actionEvent) throws IOException {
        Client client = new Client();
        try{
            String login = organizerLoginTxtField.getText();
            if(login.isEmpty()){
                throw new WrongLoginException("Wrong login");
            }
            client.setId(Integer.parseInt(organizerLoginTxtField.getText()));
            switchToOrdersView(actionEvent, client.getId());
        }catch (WrongLoginException | NumberFormatException e){
            System.out.println("Wrong login");
            organizerLoginTxtField.clear();
        }
    }
}
