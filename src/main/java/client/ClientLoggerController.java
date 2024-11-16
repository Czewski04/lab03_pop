package client;

import exception.WrongLoginException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class ClientLoggerController {

    @FXML
    private TextField clientLoginTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToOffersView(ActionEvent actionEvent, int login) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/client/clientOffersView.fxml")));
        root = fxmlLoader.load();

        ClientOffersController controller = fxmlLoader.getController();
        controller.setClientLogin(login);

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void readLogin(ActionEvent actionEvent) throws IOException {
        Client client = new Client();
        try{
            String login = clientLoginTxtField.getText();
            if(login.isEmpty()){
                throw new WrongLoginException("Wrong login");
            }
            client.setId(Integer.parseInt(clientLoginTxtField.getText()));
            switchToOffersView(actionEvent, client.getId());
        }catch (WrongLoginException | NumberFormatException e){
            System.out.println("Wrong login");
            clientLoginTxtField.clear();
        }
    }
}
