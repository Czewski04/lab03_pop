package organizer;

import databaseconnectivity.DatabaseConnector;
import others.Order;

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
}
