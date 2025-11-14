package co.edu.uptc.model;

public class Product {

    private String id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private String note;


    public Product(String id, String name, String category, double price, int quantity, String note) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
    }

    public Product(String id, String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.note = note;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
