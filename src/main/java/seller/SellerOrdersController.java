package seller;

import exception.EmptyFieldException;
import exception.NoSelectedItemException;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import orderoffer.Order;
import abstractcontrollerclasses.SellerControllerAbstractClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SellerOrdersController extends SellerControllerAbstractClass {
    @FXML
    private TextField organizerIdTxtField;
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
        refreshTableView();
    }

    private ObservableList<Order> showSellerOrders() throws SQLException {
        return FXCollections.observableArrayList(showSellerOrdersAbstract());
    }

    public void switchToOffersView(ActionEvent actionEvent) throws IOException {
        switchToOffersView(actionEvent,stage,scene,root);
    }

    public void switchToEditOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/seller/sellerEditOrdersView.fxml")));
        root = fxmlLoader.load();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshTableView() throws SQLException {
        try{
            tableView.setItems(showSellerOrders());
        }catch (SQLException e){
            System.out.println("no content to display");
        }
    }

    public void placeOrder() throws SQLException {
        try{
            if(organizerIdTxtField.getText().isEmpty()) throw new EmptyFieldException("Empty field");
            if(tableView.getSelectionModel().getSelectedItem() == null) throw new NoSelectedItemException("No item selected");
            tableView.getSelectionModel().getSelectedItem().setPlacedOrder(true);
            tableView.getSelectionModel().getSelectedItem().setOrganizerId(Integer.parseInt(Objects.requireNonNull(organizerIdTxtField.getText())));
            SellerDaoImpl sellerDao = new SellerDaoImpl();
            sellerDao.placeOrder(tableView.getSelectionModel().getSelectedItem());
            organizerIdTxtField.clear();
            refreshTableView();
        }catch (EmptyFieldException | NoSelectedItemException | NumberFormatException e){
            if(e.getMessage().equals("Empty field")) System.out.println(e.getMessage());
            else if(e.getMessage().equals("No item selected")) System.out.println(e.getMessage());
            else System.out.println("Wrong id format");
        }

    }
}
