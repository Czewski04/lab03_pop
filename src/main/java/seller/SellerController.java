package seller;

import databaseconnectivity.DatabaseConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import others.Offer;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class SellerController {
    @FXML
    private TableView tableView;
    @FXML
    private  TableColumn idView;
    @FXML
    private  TableColumn offerNameView;
    @FXML
    private  TableColumn seatsView;
    @FXML
    private  TableColumn dateView;
    @FXML
    private TextField seatsTxtField;
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField dateTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;



    public void setOffer() throws SQLException {
        Offer offer = new Offer();
        offer.setName(nameTxtField.getText());
        offer.setDate(dateTxtField.getText());
        offer.setNumberOfSeats(Integer.parseInt(seatsTxtField.getText()));
        System.out.println(offer.getName());
        System.out.println(offer.getDate());
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.addOffer(offer);
    }

    public void switchToSellerOffers(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/seller/sellerViewOffers.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showSellerOffers() throws SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from offers");
        tableView.getItems().clear();
        while (resultSet.next()) {

        }
    }

}
