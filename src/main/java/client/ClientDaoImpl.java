package client;

import databaseconnectivity.DatabaseConnector;
import others.Offer;
import others.Order;

import java.sql.*;

public class ClientDaoImpl implements ClientDao {
    @Override
    public void makeOrder(Order order) throws SQLException {

    }

    @Override
    public void updateOffer(Offer offer) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE offers SET clientID = ? WHERE id = ?");
            preparedStatement.setInt(1, offer.getClientId());
            preparedStatement.setInt(2, offer.getId());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }

    @Override
    public void showBoughtProducts(Client client) {

    }

    @Override
    public void showOffers(Client client) {


    }
}

