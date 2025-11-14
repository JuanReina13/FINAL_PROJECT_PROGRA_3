package co.edu.uptc.model;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class RestaurantManager {

    private final String ACTIVE_FILE_PATH = "data/orders.json";
    private final String RECORD_FILE_PATH = "data/records.json";
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private List<Station> stations;
    private Queue<Order> orderQueue;
    private Stack<Order> orderStack;

    public RestaurantManager() {
        this.stations = new ArrayList<>();
        this.orderQueue = new LinkedList<>();
        this.orderStack = new Stack<>();
        configureStations();
        loadData();
    }

    private void configureStations() {

        Station mainKitchen = new Station(
                "MainKitchen",
                List.of(ProductCategory.BURGER, ProductCategory.SANDWICH, ProductCategory.HOT_DOG,
                        ProductCategory.MEXICAN, ProductCategory.SALAD, ProductCategory.BEVERAGE));

        Station pizzas = new Station(
                "Pizza",
                List.of(ProductCategory.PIZZA));

        Station expeditor = new Station("Expeditor", List.of(ProductCategory.values()));

        stations.add(mainKitchen);
        stations.add(pizzas);
        stations.add(expeditor);
    }

    public Station findStationByName(String name) {
        for (Station s : stations) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        orderQueue.add(order);
        shareOrder(order);
        saveOrdersToFile();
    }

    private void shareOrder(Order order) {
        for (Station station : stations) {
            for (ProductCategory category : station.getAssignedCategories()) {
                if (order.containsCategory(category)) {
                    station.addOrder(order);
                    break;
                }

            }
        }
    }

    public void finishOrder(Order order) {
        Iterator<Order> iterator = orderQueue.iterator();
        while (iterator.hasNext()) {
            Order o = iterator.next();
            if (o.getIdOrder().equalsIgnoreCase(order.getIdOrder())) {
                iterator.remove();
                orderStack.push(o);
                notifyStations(order, order.getCategoriesInvolved());
                break;
            }
        }

        saveOrdersToFile();
        saveRecordsToFile();
    }

    private void notifyStations(Order order, List<ProductCategory> categories) {
        for (Station station : stations) {
            for (ProductCategory category : categories) {
                if (station.getAssignedCategories().contains(category)) {
                    station.finishOrder(order);
                    sendFinishMessageStation(order, station);
                    break;
                }
            }
        }
    }

    private void sendFinishMessageStation(Order order, Station station) {
        try {
            DataOutputStream output = station.getClientOutput();
            if (output != null) {
                output.writeUTF("ORDER_FINISHED");
                output.writeUTF(order.getIdOrder());
                output.flush();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error notificando finalización a " + station.getName() + ": " + e.getMessage());
        }

    }

    public String getOrdersJson() {
        return gson.toJson(orderQueue);
    }

    public String getRecordsJson() {
        return gson.toJson(orderStack);
    }

    private void saveOrdersToFile() {
        try (FileWriter writer = new FileWriter(ACTIVE_FILE_PATH)) {
            gson.toJson(orderQueue, writer);
        } catch (IOException e) {
            System.out.println("Error guardando órdenes activas: " + e.getMessage());
        }
    }

    private void saveRecordsToFile() {
        try (FileWriter writer = new FileWriter(RECORD_FILE_PATH)) {
            gson.toJson(orderStack, writer);
        } catch (IOException e) {
            System.out.println("Error guardando historial: " + e.getMessage());
        }
    }

    private void loadData() {
        this.orderQueue = loadOrdersFromFile(ACTIVE_FILE_PATH);
        this.orderStack = loadOrdersFromStack(RECORD_FILE_PATH);
    }

    private Queue<Order> loadOrdersFromFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return new LinkedList<>();

        try (Reader reader = new FileReader(file)) {
            Type queueType = new TypeToken<Queue<Order>>() {
            }.getType();
            Queue<Order> loaded = gson.fromJson(reader, queueType);
            return (loaded != null) ? loaded : new LinkedList<>();
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de órdenes: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    private Stack<Order> loadOrdersFromStack(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return new Stack<>();

        try (Reader reader = new FileReader(file)) {
            Type stackType = new TypeToken<Stack<Order>>() {
            }.getType();
            Stack<Order> loaded = gson.fromJson(reader, stackType);
            return (loaded != null) ? loaded : new Stack<>();
        } catch (IOException e) {
            System.out.println("Error leyendo historial: " + e.getMessage());
            return new Stack<>();
        }
    }

    public List<Order> getActiveOrdersForStation(Station station) {
        List<Order> filtered = new ArrayList<>();

        for (Order o : orderQueue) {
            for (ProductCategory category : o.getCategoriesInvolved()) {
                if (station.getAssignedCategories().contains(category)) {
                    filtered.add(o);
                    break;
                }
            }
        }

        return filtered;
    }

    public List<Station> getStations() {
        return stations;
    }
}
