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

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class ClientController {

    Client client = new Client();

    @FXML
    private TableView<Offer> tableView;
    @FXML
    private TableColumn<Offer, Integer> idView;
    @FXML
    private  TableColumn<Offer, String> offerNameView;

    public void initializeTableView() throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("name"));
        refreshTableView();
    }

    private ObservableList<Offer> showClientOffers() throws SQLException {
        ObservableList<Offer> data = FXCollections.observableArrayList();

        String sql = "SELECT * FROM offers";

        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("offerName");
            int clientId = resultSet.getInt("clientId");
            data.add(new Offer(id, name, clientId));
        }
        return data;
    }

    public void joinToEvent() throws SQLException {
        try{
            if(tableView.getSelectionModel().getSelectedItem().getClientId() !=0) throw new Exception("Event is already full");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }
        tableView.getSelectionModel().getSelectedItem().setClientId(client.getId());
        ClientDaoImpl clientDao = new ClientDaoImpl();
        clientDao.updateOffer(tableView.getSelectionModel().getSelectedItem());
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

}
