package seller;

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


public class SellerControllerOffersController extends SellerControllerAbstractClass {
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
        offer.setName(nameTxtField.getText());
        offer.setClientId(0);
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.addOffer(offer);
        refreshData();
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
    }

    private ObservableList<Offer> showSellerOffers() throws SQLException {
        return FXCollections.observableArrayList(showOffersAbstract());
    }

    public void refreshData() throws SQLException {
        tableView.setItems(showSellerOffers());
    }

    public void deleteOffer() throws SQLException {
        SellerDaoImpl sellerDao = new SellerDaoImpl();
        sellerDao.deleteOffer(tableView.getSelectionModel().getSelectedItem());
        refreshData();
    }
}
