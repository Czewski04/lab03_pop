package seller;

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
import abstractcontrollerclasses.SellerControllerAbstractClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SellerControllerEditOrdersController extends SellerControllerAbstractClass {

    @FXML
    private TextField OrganizerIdTxtField;
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

    public void initialize() throws SQLException {
        ordersInitialize(idView, offerNameView,organizerIdView,dateView,confirmedView,placedOrderView,clientIdView);
    }

    private ObservableList<Order> showSellerOrders() throws SQLException {
        return FXCollections.observableArrayList(showSellerOrdersAbstract());
    }

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        switchToOrdersView(actionEvent,stage, scene,root);
    }


    public void refreshTableView() throws SQLException {
        tableView.setItems(showSellerOrders());
    }

    public void editOrder() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setOrganizerId(Integer.parseInt(Objects.requireNonNull(OrganizerIdTxtField.getText())));
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.updateOrganizerId(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }

    public void cancelPlacedOrder() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setPlacedOrder(false);
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.cancelPlacedOrders(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }
}
