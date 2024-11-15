package client;

import databaseconnectivity.DatabaseConnector;
import orderoffer.Offer;
import orderoffer.Order;

import java.sql.*;

public class ClientDaoImpl implements ClientDao {
    @Override
    public void makeOrder(Order order) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS orders(id INTEGER PRIMARY KEY, offerName TEXT, clientId INT, organizerId INT, eventDate TEXT, placedOrder BOOLEAN, confirmed BOOLEAN)");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO orders(offerName, clientId, organizerId, eventDate, placedOrder, confirmed) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, order.getOfferName());
            preparedStatement.setInt(2, order.getClientId());
            preparedStatement.setInt(3, order.getOrganizerId());
            preparedStatement.setString(4,order.getDate());
            preparedStatement.setBoolean(5, order.isPlacedOrder());
            preparedStatement.setBoolean(6, order.isConfirmed());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                order.setId(generatedKeys.getInt(1));
            }

            statement.close();
            db.closeConnection();
        }
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
    public void acceptEventDate(Order order) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE orders SET confirmed = ? WHERE id = ?");
            preparedStatement.setBoolean(1, order.isConfirmed());
            preparedStatement.setInt(2, order.getId());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }

    @Override
    public void deleteOrder(Order order) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM orders WHERE id = ?");
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }
}

