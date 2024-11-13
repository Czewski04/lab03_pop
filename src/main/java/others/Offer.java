package others;

import java.util.ArrayList;

public class Offer {
    private int id;
    private String name;
    private int numberOfSeats;
    private String date;
    private ArrayList<Integer> ClientsId;

    public ArrayList<Integer> getClientsId() {
        return ClientsId;
    }

    public void setClientsId(ArrayList<Integer> clientsId) {
        ClientsId = clientsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
