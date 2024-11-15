package organizer;

import client.Client;
import databaseconnectivity.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import others.Offer;
import others.Order;
import seller.SellerDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class OrganizerOrdersController {

    Organizer organizer = new Organizer();

    @FXML
    private TextField DateTxtField;
    @FXML
    private TableColumn<Order, Integer> organizerIdView;
    @FXML
    private TableColumn<Order, Integer> clientIdView;
    @FXML
    private  TableColumn<Order, String> dateView;
    @FXML
    private  TableColumn<Order, Boolean> placedOrderView;
    @FXML
    private  TableColumn<Order, Boolean> confirmedView;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Integer> idView;
    @FXML
    private  TableColumn<Order, String> offerNameView;

    public void initializeTableView() throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("offerName"));
        organizerIdView.setCellValueFactory(new PropertyValueFactory<>("organizerId"));
        dateView.setCellValueFactory(new PropertyValueFactory<>("date"));
        confirmedView.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
        placedOrderView.setCellValueFactory(new PropertyValueFactory<>("placedOrder"));
        clientIdView.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        refreshTableView();
    }

    private ObservableList<Order> showOrganizerOrders() throws SQLException {
        ObservableList<Order> data = FXCollections.observableArrayList();


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM orders WHERE organizerId = ?");
        preparedStatement.setInt(1, organizer.getId());
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int organizerId = resultSet.getInt("organizerId");
            String offerName = resultSet.getString("offerName");
            int clientId = resultSet.getInt("clientId");
            String date = resultSet.getString("eventDate");
            boolean confirmed = resultSet.getBoolean("confirmed");
            boolean placedOrder = resultSet.getBoolean("placedOrder");
            data.add(new Order(id, clientId, organizerId, offerName, date, placedOrder, confirmed));
        }
        return data;
    }

    public void setClientLogin(int id){
        organizer.setId(id);
        try{
            initializeTableView();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void refreshTableView() throws SQLException {
        tableView.setItems(showOrganizerOrders());
    }

    public void setDate() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setDate(Objects.requireNonNull(DateTxtField.getText()));
        OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
        organizerDao.setDate(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }

}
