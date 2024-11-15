package seller;

import client.ClientDaoImpl;
import client.ClientOrdersController;
import databaseconnectivity.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import others.Offer;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class SellerOffersController {
    @FXML
    private TableView<Offer> tableView;
    @FXML
    private  TableColumn<Offer, Integer> idView;
    @FXML
    private  TableColumn<Offer, String> offerNameView;
    @FXML
    private  TableColumn<Offer, Integer> clientsIdView;
    @FXML
    private TextField nameTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setOffer() throws SQLException {
        Offer offer = new Offer();
        offer.setName(nameTxtField.getText());
        offer.setClientId(0);
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.addOffer(offer);
        refreshData();
    }

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/seller/sellerOrdersView.fxml")));
        root = fxmlLoader.load();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        clientsIdView.setCellValueFactory(new PropertyValueFactory<>("clientId"));
    }

    private ObservableList<Offer> showSellerOffers() throws SQLException {
        ObservableList<Offer> data = FXCollections.observableArrayList();

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM offers WHERE clientId=?");
        preparedStatement.setInt(1, 0);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("offerName");
            int clientId = resultSet.getInt("clientId");
            data.add(new Offer(id, name, clientId));
        }
        return data;
    }

    public void refreshData() throws SQLException {
        tableView.setItems(showSellerOffers());
    }
}
