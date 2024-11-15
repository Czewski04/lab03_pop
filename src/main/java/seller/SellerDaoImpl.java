package seller;

import databaseconnectivity.DatabaseConnector;
import others.Offer;
import others.Order;
import java.sql.*;

public class SellerDaoImpl implements SellerDao {
    @Override
    public void addOffer(Offer offer) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS offers(id INTEGER PRIMARY KEY, offerName TEXT, clientId INT)");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO offers(offerName, clientId) VALUES (?, ?)");
            preparedStatement.setString(1, offer.getName());
            preparedStatement.setInt(2, offer.getClientId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                offer.setId(generatedKeys.getInt(1));
            }

            statement.close();
            db.closeConnection();
        }
    }

    @Override
    public void placeOrder(Order order) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE orders SET placedOrder = ?, organizerId = ? WHERE id = ?");
            preparedStatement.setBoolean(1, order.isPlacedOrder());
            preparedStatement.setInt(2, order.getOrganizerId());
            preparedStatement.setInt(3, order.getId());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }

    public void updateOffer(Offer offer) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE offers SET offerName = ? WHERE id = ?");
            preparedStatement.setString(1, offer.getName());
            preparedStatement.setInt(2, offer.getId());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }

}
