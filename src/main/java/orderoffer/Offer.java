package orderoffer;

public class Offer {
    private int id;
    private String name;
    private int clientId;

    public Offer(int id, String name, int clientId) {
        this.id = id;
        this.name = name;
        this.clientId = clientId;
    }

    public Offer(){}

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
