package client;

import databaseconnectivity.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import others.Offer;
import others.Order;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class ClientOffersController {

    Client client = new Client();

    @FXML
    private TableView<Offer> tableView;
    @FXML
    private TableColumn<Offer, Integer> idView;
    @FXML
    private  TableColumn<Offer, String> offerNameView;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initializeTableView() throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        refreshTableView();
    }

    private ObservableList<Offer> showClientOffers() throws SQLException {
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

    public void joinToEvent() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setClientId(client.getId());
        ClientDaoImpl clientDao = new ClientDaoImpl();
        clientDao.updateOffer(tableView.getSelectionModel().getSelectedItem());
        setOrder(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }

    public void setClientLogin(int id){
        client.setId(id);
        try{
            initializeTableView();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refreshTableView() throws SQLException {
        tableView.setItems(showClientOffers());
    }

    public void setOrder(Offer offer) throws SQLException {
        Order order = new Order();
        order.setOfferName(offer.getName());
        order.setClientId(client.getId());
        order.setDate("");
        ClientDaoImpl clientDao = new ClientDaoImpl();
        clientDao.makeOrder(order);
    }

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/client/clientOrdersView.fxml")));
        root = fxmlLoader.load();

        ClientOrdersController controller = fxmlLoader.getController();
        controller.setClientLogin(client.getId());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
