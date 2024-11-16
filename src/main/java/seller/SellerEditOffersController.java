package seller;

import exception.EmptyFieldException;
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
import orderoffer.Offer;
import abstractcontrollerclasses.SellerControllerAbstractClass;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SellerEditOffersController extends SellerControllerAbstractClass {

    @FXML
    private TableView<Offer> tableView;
    @FXML
    private TableColumn<Offer, Integer> idView;
    @FXML
    private  TableColumn<Offer, String> offerNameView;
    @FXML
    private  TableColumn<Offer, Integer> clientsIdView;
    @FXML
    private TextField nameTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize() throws SQLException {
        offersInitialize(idView, offerNameView, clientsIdView);
        refreshData();
    }

    private ObservableList<Offer> showSellerOffers() throws SQLException {
        return FXCollections.observableArrayList(showOffersAbstract());
    }

    public void refreshData() throws SQLException {
        try{
            tableView.setItems(showSellerOffers());
        }catch (SQLException e){
            System.out.println("no content to display");
        }
    }

    public void switchToOffersView(ActionEvent actionEvent) throws IOException {
        switchToOffersView(actionEvent,stage,scene,root);
    }

    public void editOffer() throws SQLException {
        try{
            if(tableView.getSelectionModel().getSelectedItem() == null) throw new NoSelectedItemException("No selected item");
            if(nameTxtField.getText().isEmpty()) throw new EmptyFieldException("Empty field");
            tableView.getSelectionModel().getSelectedItem().setName(Objects.requireNonNull(nameTxtField.getText()));
            SellerDaoImpl sellerDao = new SellerDaoImpl();
            sellerDao.updateOffer(tableView.getSelectionModel().getSelectedItem());
            nameTxtField.clear();
            refreshData();
        }catch (EmptyFieldException | NoSelectedItemException e){
            System.out.println(e.getMessage());
        }

    }
}
