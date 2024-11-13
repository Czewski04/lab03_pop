package others;

public class Order {
    private int id;
    private int ClientId;
    private int OrganizerId;
    private int EventId;
    private boolean salesStared;
    private boolean soldOut;
    private boolean ended;

    public int getClientId() {
        return ClientId;
    }

    public void setClientId(int clientId) {
        ClientId = clientId;
    }

    public boolean isEnded() {
        return ended;
    }

    public void setEnded(boolean ended) {
        this.ended = ended;
    }

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrganizerId() {
        return OrganizerId;
    }

    public void setOrganizerId(int organizerId) {
        OrganizerId = organizerId;
    }

    public boolean isSalesStared() {
        return salesStared;
    }

    public void setSalesStared(boolean salesStared) {
        this.salesStared = salesStared;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }
}
