package organizer;

import client.ClientDaoImpl;
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
import java.sql.SQLException;
import java.util.Objects;

public class OrganizerEditOrdersController extends OrganizerControllerAbstractClass {

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

    public void initializeTableView() throws SQLException {
        ordersInitialize(idView, offerNameView,organizerIdView,dateView,confirmedView,placedOrderView,clientIdView);
    }

    private ObservableList<Order> showOrganizerOrders() throws SQLException {
        return FXCollections.observableArrayList(showOrganizerOrders(organizer));
    }

    public void setOrganizerLogin(int id){
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

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        switchToOrdersView(actionEvent,stage,scene,root, organizer);
    }

    public void editDate() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setDate(Objects.requireNonNull(DateTxtField.getText()));
        OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
        organizerDao.setDate(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }

    public void deleteOrder() throws SQLException {
        OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
        organizerDao.deleteOrder(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }
}
