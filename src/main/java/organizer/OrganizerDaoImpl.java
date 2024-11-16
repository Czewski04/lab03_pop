package organizer;

import databaseconnectivity.DatabaseConnector;
import orderoffer.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizerDaoImpl implements OrganizerDao {

    @Override
    public void setDate(Order order) throws SQLException {
        DatabaseConnector db = new DatabaseConnector();
        Connection conn = db.getConnection();
        if(conn != null) {
            Statement statement = conn.createStatement();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE orders SET eventDate = ? WHERE id = ?");
            preparedStatement.setString(1, order.getDate());
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

            PreparedStatement preparedStatement1 = conn.prepareStatement("UPDATE offers SET clientID = 0 WHERE id = ?");
            preparedStatement1.setInt(1, order.getOfferId());
            preparedStatement1.executeUpdate();

            PreparedStatement preparedStatement2 = conn.prepareStatement("DELETE FROM orders WHERE id = ?");
            preparedStatement2.setInt(1, order.getId());
            preparedStatement2.executeUpdate();
            statement.close();
            db.closeConnection();
        }
    }
}
