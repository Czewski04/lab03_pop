package seller;

import java.util.ArrayList;

public class Seller {
    private int id;
    private String name;
    private ArrayList<Integer> offertsId;

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

    public ArrayList<Integer> getOffertsId() {
        return offertsId;
    }

    public void setOffertsId(ArrayList<Integer> offertsId) {
        this.offertsId = offertsId;
    }
}
