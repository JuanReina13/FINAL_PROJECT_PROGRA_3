package co.edu.uptc.model;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;

public class Station {

    private String name;
    private List<ProductCategory> assignedCategories;
    private List<Order> orders;
    private DataOutputStream clientOutput;
    private Gson gson = new Gson();

    public Station(String name, List<ProductCategory> assignedCategories) {
        this.name = name;
        this.assignedCategories = assignedCategories;
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
        sendOrderToClient(order);
    }

    private void sendOrderToClient(Order order) {
        if (clientOutput != null) {
            try {
                clientOutput.writeUTF("NEW_ORDER");
                clientOutput.writeUTF(gson.toJson(order));
                clientOutput.flush();
                System.out.println("Orden enviada a estación: " + name);
            } catch (IOException e) {
                System.out.println("Error enviando orden a " + name + ": " + e.getMessage());
            }
        } else {
            System.out.println("Estación " + name + " sin cliente conectado");
        }
    }

    public void finishOrder(Order order) {
        Iterator<Order> iterator = orders.iterator();
        boolean condition = true;
        Order currentOrder;
        while (iterator.hasNext() && condition){
            currentOrder = iterator.next();
            if (currentOrder.getIdOrder().equalsIgnoreCase(order.getIdOrder())){
                iterator.remove();
                condition = false;
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductCategory> getAssignedCategories() {
        return assignedCategories;
    }

    public void setAssignedCategories(List<ProductCategory> assignedCategories) {
        this.assignedCategories = assignedCategories;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public DataOutputStream getClientOutput() {
        return clientOutput;
    }

    public void setClientOutput(DataOutputStream clientOutput) {
        this.clientOutput = clientOutput;
    }
}
