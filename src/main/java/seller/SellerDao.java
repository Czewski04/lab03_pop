package seller;

import orderoffer.Offer;
import orderoffer.Order;

import java.sql.SQLException;

public interface SellerDao {
    void addOffer(Offer offer) throws SQLException;
    void placeOrder(Order order) throws SQLException;
    void placedOrdersUpdate(Order order) throws SQLException;
    void updateOrganizerId(Order order) throws SQLException;
    void updateOffer(Offer offer) throws SQLException;
    void deleteOffer(Offer offer) throws SQLException;
    void clearDatabase() throws SQLException;
}
