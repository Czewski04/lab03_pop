package abstractcontrollerclasses;

import databaseconnectivity.DatabaseConnector;
import exception.EmptyFieldException;
import exception.InvalidDate;
import exception.NoSelectedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import orderoffer.Order;
import organizer.Organizer;
import organizer.OrganizerDaoImpl;
import organizer.OrganizerOrdersController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public abstract class OrganizerControllerAbstractClass {

    public ObservableList<Order> showOrganizerOrders(Organizer organizer) throws SQLException {
        ObservableList<Order> data = FXCollections.observableArrayList();


        DatabaseConnector databaseConnector = new DatabaseConnector();
        Connection conn = databaseConnector.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM orders WHERE organizerId = ? AND confirmed = false AND placedOrder = true");
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

    public void switchToOrdersView(ActionEvent actionEvent, Stage stage, Scene scene, Parent root, Organizer organizer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/organizer/organizerOrdersView.fxml")));
        root = fxmlLoader.load();

        OrganizerOrdersController controller = fxmlLoader.getController();
        controller.setOrganizerLogin(organizer.getId());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ordersInitialize(TableColumn<Order, Integer> idView, TableColumn<Order, String> offerNameView, TableColumn<Order, Integer> organizerIdView, TableColumn<Order, String> dateView, TableColumn<Order, Boolean> confirmedView, TableColumn<Order, Boolean> placedOrderView, TableColumn<Order, Integer> clientIdView)  throws SQLException {
        idView.setCellValueFactory(new PropertyValueFactory<>("id"));
        offerNameView.setCellValueFactory(new PropertyValueFactory<>("offerName"));
        organizerIdView.setCellValueFactory(new PropertyValueFactory<>("organizerId"));
        dateView.setCellValueFactory(new PropertyValueFactory<>("date"));
        confirmedView.setCellValueFactory(new PropertyValueFactory<>("confirmed"));
        placedOrderView.setCellValueFactory(new PropertyValueFactory<>("placedOrder"));
        clientIdView.setCellValueFactory(new PropertyValueFactory<>("clientId"));
    }

    public void updateDate(TableView<Order> tableView, TextField dateTxtField) throws SQLException {
        try{
            if(tableView.getSelectionModel().getSelectedItem() == null) throw new NoSelectedItemException("No item selected");
            if(dateTxtField.getText().isEmpty()) throw new EmptyFieldException("Empty field");
            if(!isValidDate(dateTxtField.getText())) throw new InvalidDate("Invalid Date");
            tableView.getSelectionModel().getSelectedItem().setDate(Objects.requireNonNull(dateTxtField.getText()));
            OrganizerDaoImpl organizerDao = new OrganizerDaoImpl();
            organizerDao.setDate(tableView.getSelectionModel().getSelectedItem());
        }catch (NoSelectedItemException | EmptyFieldException | InvalidDate e){
            System.out.println(e.getMessage());
        }
    }

    private static boolean isValidDate(String stringDate) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (stringDate.length() != 10 || stringDate.charAt(2) != '.' || stringDate.charAt(5) != '.') {
            return false;
        }

        try {
            int day = Integer.parseInt(stringDate.substring(0, 2));
            int month = Integer.parseInt(stringDate.substring(3, 5));
            int year = Integer.parseInt(stringDate.substring(6, 10));

            if(year<2024 || year>2050){
                return false;
            }
            if (month < 1 || month > 12) {
                return false;
            }

            if (month == 2 && isLeapYear(year)) {
                daysInMonth[1] = 29;
            }

            return day >= 1 && day <= daysInMonth[month - 1];
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
