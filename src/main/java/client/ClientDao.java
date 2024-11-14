package client;

import others.Offer;
import others.Order;

import java.sql.SQLException;

public interface ClientDao {
    public void showBoughtProducts(Client client);

    public void makeOrder(Order order) throws SQLException;

    public void showOffers(Client client);

    public void updateOffer(Offer offer) throws SQLException;

}
