package orderoffer;


public class Order {
    private int id;
    private int clientId;
    private int organizerId;
    private String offerName;
    private String date;
    private boolean placedOrder;
    private boolean confirmed;

    public Order(int id, int clientId, int organizerId, String offerName, String date, boolean placedOrder, boolean confirmed) {
        this.id = id;
        this.clientId = clientId;
        this.organizerId = organizerId;
        this.offerName = offerName;
        this.date = date;
        this.placedOrder = placedOrder;
        this.confirmed = confirmed;
    }

    public Order(){}

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isPlacedOrder() {
        return placedOrder;
    }

    public void setPlacedOrder(boolean placedOrder) {
        this.placedOrder = placedOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
