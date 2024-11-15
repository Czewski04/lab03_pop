package seller;

import others.Offer;
import others.Order;

import java.sql.SQLException;

public interface SellerDao {
    public void addOffer(Offer offer) throws SQLException;
    public void placeOrder(Order order) throws SQLException;
}
