package organizer;

import orderoffer.Order;

import java.sql.SQLException;

public interface OrganizerDao {
    void setDate(Order order) throws SQLException;
    void deleteOrder(Order order) throws SQLException;
}
