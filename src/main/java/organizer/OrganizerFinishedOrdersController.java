package organizer;

import databaseconnectivity.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import orderoffer.Order;
import abstractcontrollerclasses.OrganizerControllerAbstractClass;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizerFinishedOrdersController extends OrganizerControllerAbstractClass {

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        switchToOrdersView(actionEvent,stage,scene,root, organizer);
    }

    public void refreshTableView() throws SQLException {
        try{
            tableView.setItems(showOrganizerOrders());
        }catch (SQLException e){
            System.out.println("no content to display");
        }
    }

    public void initializeTableView() throws SQLException {
        ordersInitialize(idView, offerNameView,organizerIdView,dateView,confirmedView,placedOrderView,clientIdView);
        refreshTableView();
    }

    private ObservableList<Order> showOrganizerOrders() throws SQLException {
        ObservableList<Order> data = FXCollections.observableArrayList();


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM orders WHERE organizerId = ? AND confirmed = TRUE");
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
            int offerId = resultSet.getInt("offerId");
            data.add(new Order(id, clientId, organizerId, offerName, date, placedOrder, confirmed, offerId));
        }
        return data;
    }

    public void setOrganizerLogin(int id){
        organizer.setId(id);
        try{
            initializeTableView();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
