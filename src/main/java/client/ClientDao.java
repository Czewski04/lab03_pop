package client;

import orderoffer.Offer;
import orderoffer.Order;

import java.sql.SQLException;

public interface ClientDao {

    void makeOrder(Order order) throws SQLException;

    void updateOffer(Offer offer) throws SQLException;

    void acceptEventDate(Order order) throws SQLException;

    void deleteOrder(Order order) throws SQLException;

}
