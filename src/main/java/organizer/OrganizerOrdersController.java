package organizer;

import client.Client;
import client.ClientOrdersController;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import others.Offer;
import others.Order;
import others.OrganizerControllerAbstractClass;
import seller.SellerDaoImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class OrganizerOrdersController extends OrganizerControllerAbstractClass {

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

    public void setDate() throws SQLException {
        tableView.getSelectionModel().getSelectedItem().setDate(Objects.requireNonNull(DateTxtField.getText()));
        OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
        organizerDao.setDate(tableView.getSelectionModel().getSelectedItem());
        refreshTableView();
    }

    public void switchToFinishedOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerFinishedOrdersView.fxml")));
        root = fxmlLoader.load();

        OrganizerFinishedOrdersController controller = fxmlLoader.getController();
        controller.setOrganizerLogin(organizer.getId());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditOrdersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerEditOrdersView.fxml")));
        root = fxmlLoader.load();

        OrganizerEditOrdersController controller = fxmlLoader.getController();
        controller.setOrganizerLogin(organizer.getId());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
