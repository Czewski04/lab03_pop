package organizer;

import exception.NoSelectedItemException;
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

public class OrganizerEditOrdersController extends OrganizerControllerAbstractClass {

    Organizer organizer = new Organizer();

    @FXML
    private TextField dateTxtField;
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
        refreshTableView();
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
        try{
            tableView.setItems(showOrganizerOrders());
        }catch (SQLException e){
            System.out.println("no content to display");
        }
    }

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        switchToOrdersView(actionEvent,stage,scene,root, organizer);
    }

    public void editDate() throws SQLException {
        updateDate(tableView, dateTxtField);
        dateTxtField.clear();
        refreshTableView();
    }

    public void deleteOrder() throws SQLException {
        OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
        try{
            if(tableView.getSelectionModel().getSelectedItem() == null) throw new NoSelectedItemException("No item selected");
            organizerDao.deleteOrder(tableView.getSelectionModel().getSelectedItem());
            refreshTableView();
        }catch (NoSelectedItemException e){
            System.out.println(e.getMessage());
        }
    }
}
