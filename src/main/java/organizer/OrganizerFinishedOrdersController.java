package organizer;

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
import others.Order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class OrganizerFinishedOrdersController {

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
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerOrdersView.fxml")));
        root = fxmlLoader.load();

        OrganizerOrdersController controller = fxmlLoader.getController();
        controller.setOrganizerLogin(organizer.getId());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void refreshTableView() throws SQLException {
        tableView.setItems(showOrganizerOrders());
    }

    public void initializeTableView() throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("offerName"));
        organizerIdView.setCellValueFactory(new PropertyValueFactory<>("organizerId"));
        dateView.setCellValueFactory(new PropertyValueFactory<>("date"));
        confirmedView.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
        placedOrderView.setCellValueFactory(new PropertyValueFactory<>("placedOrder"));
        clientIdView.setCellValueFactory(new PropertyValueFactory<>("clientId"));
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
            data.add(new Order(id, clientId, organizerId, offerName, date, placedOrder, confirmed));
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