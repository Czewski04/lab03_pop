package seller;

import exception.EmptyFieldException;
import exception.NoSelectedItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import orderoffer.Offer;
import abstractcontrollerclasses.SellerControllerAbstractClass;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;


public class SellerOffersController extends SellerControllerAbstractClass {
    @FXML
    private TableView<Offer> tableView;
    @FXML
    private  TableColumn<Offer, Integer> idView;
    @FXML
    private  TableColumn<Offer, String> offerNameView;
    @FXML
    private  TableColumn<Offer, Integer> clientsIdView;
    @FXML
    private TextField nameTxtField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void setOffer() throws SQLException {
        Offer offer = new Offer();
        try{
            if(nameTxtField.getText().isEmpty()){
                throw new EmptyFieldException("Empty field");
            }
            offer.setName(nameTxtField.getText());
            offer.setClientId(0);
            SellerDaoImpl sellerDao = new SellerDaoImpl();
            sellerDao.addOffer(offer);
            nameTxtField.clear();
            refreshData();
        }catch (EmptyFieldException e){
            System.out.println(e.getMessage());
        }

    }

    public void switchToOrdersView(ActionEvent actionEvent) throws IOException {
        switchToOrdersView(actionEvent,stage, scene,root);
    }

    public void switchToEditOffersView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/seller/sellerEditOffersView.fxml")));
        root = fxmlLoader.load();

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

    public void deleteOffer() throws SQLException {
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        try{
            if(tableView.getSelectionModel().getSelectedItem() == null) throw new NoSelectedItemException("No selected item");
            sellerDao.deleteOffer(tableView.getSelectionModel().getSelectedItem());
            refreshData();
        }catch (NoSelectedItemException e){
            System.out.println(e.getMessage());
        }

    }
}
