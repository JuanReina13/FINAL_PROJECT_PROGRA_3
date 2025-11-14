package co.edu.uptc.model;

import java.util.List;
import java.util.UUID;

public class Order {

    private String idOrder;
    private String table;
    private List<Product> products;
    private String time;
    private boolean isReady;
    private List<String> categoriesInvolved;

    public Order(String table, List<Product> products, String time) {
        this.idOrder = UUID.randomUUID().toString();;
        this.table = table;
        this.products = products;
        this.time = time;
    }

    public Order() {
    }

    public void setIdOrder(String idOrder){
        this.idOrder = idOrder;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean isReady) {
        this.isReady = isReady;
    }
    
    public List<String> getCategoriesInvolved() {
        return categoriesInvolved;
    }

    public void setCategoriesInvolved(List<String> categoriesInvolved) {
        this.categoriesInvolved = categoriesInvolved;
    }
}
