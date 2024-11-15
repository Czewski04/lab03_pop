package organizer;

import others.Order;

import java.sql.SQLException;

public interface OrganizerDao {
    public void setDate(Order order) throws SQLException;
}
