package seller;

import databaseconnectivity.DatabaseConnector;
import others.Offer;
import others.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SellerDaoImpl implements SellerDao {
    @Override
    public void addOffer(Offer offer) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS offers(id INTEGER PRIMARY KEY, offer_name TEXT, number_of_seats INT, offer_date TEXT)");
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO offers(offer_name, number_of_seats, offer_date) VALUES (?, ?, ?)");
            preparedStatement.setString(1, offer.getName());
            preparedStatement.setInt(2, offer.getNumberOfSeats());
            preparedStatement.setString(3, offer.getDate());
            preparedStatement.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }

    @Override
    public void confirmOrder(Order order) {

    }

    @Override
    public void updateOrderStatus(Order order) {

    }

}
