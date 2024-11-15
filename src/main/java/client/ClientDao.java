package client;

import others.Offer;
import others.Order;

import java.sql.SQLException;

public interface ClientDao {

    public void makeOrder(Order order) throws SQLException;

    public void updateOffer(Offer offer) throws SQLException;

    public void acceptEventDate(Order order) throws SQLException;

}
