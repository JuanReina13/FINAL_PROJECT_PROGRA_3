package co.edu.uptc.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {

    private String idOrder;
    private String table;
    private List<Product> products;
    private boolean isReady;
    private String time;
    private List<ProductCategory> categoriesInvolved;

    public Order(String table, List<Product> products) {
        this.idOrder = UUID.randomUUID().toString();
        this.table = table;
        this.products = products;
        this.isReady = false;
        setTime();
        categoriesInvolved();
    }

    public boolean containsCategory(ProductCategory category) {
        for (Product product : products) {
            if (product.getCategory() == category) {
                return true;
            }
        }
        return false;
    }

    private void categoriesInvolved() {
        categoriesInvolved = new ArrayList<>();
        for (Product product : products) {
            if (!categoriesInvolved.contains(product.getCategory())) {
                categoriesInvolved.add(product.getCategory());
            }
        }
    }

    public String getCreationTime(long timemm) {
        Date date = new Date(timemm);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }

    public void setTime(){
        long timemm = System.currentTimeMillis();
        time = getCreationTime(timemm);
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrden) {
        this.idOrder = idOrden;
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

    public Boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(Boolean isReady) {
        this.isReady = isReady;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<ProductCategory> getCategoriesInvolved() {
        return categoriesInvolved;
    }

    public void setCategoriesInvolved(List<ProductCategory> categoriesInvolved) {
        this.categoriesInvolved = categoriesInvolved;
    }
}