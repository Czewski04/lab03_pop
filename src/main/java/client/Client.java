package client;

import java.util.ArrayList;

public class Client {
    private int id;
    private String name;
    private ArrayList<Object> products;

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

    public ArrayList<Object> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Object> products) {
        this.products = products;
    }
}
