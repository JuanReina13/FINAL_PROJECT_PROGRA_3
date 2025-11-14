package co.edu.uptc.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import com.google.gson.Gson;

import co.edu.uptc.model.Order;
import co.edu.uptc.model.RestaurantManager;
import co.edu.uptc.model.Station;

public class ClientsThread extends Thread {

    private Socket socket;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private RestaurantManager restaurantManager;
    private Gson gson = new Gson();

    public ClientsThread(Socket socket, RestaurantManager restaurantManager) {
        this.socket = socket;
        this.restaurantManager = restaurantManager;
    }

    @Override
    public void run() {
        try {
            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            boolean running = true;

            while (running) {
                String command = dataInput.readUTF();

                switch (command) {
                    case "NEW_ORDER": {
                        String orderJson = dataInput.readUTF();
                        Order order = gson.fromJson(orderJson, Order.class);

                        restaurantManager.addOrder(order); // 🔥 ya se encarga de enviar la orden
                        break;
                    }

                    case "GET_ORDERS":
                        String stationName2 = dataInput.readUTF();
                        Station requestingStation = restaurantManager.findStationByName(stationName2);
                        List<Order> filteredOrders = restaurantManager.getActiveOrdersForStation(requestingStation);
                        dataOutput.writeUTF("ORDERS");
                        dataOutput.writeUTF(gson.toJson(filteredOrders));
                        // String ordersJson = restaurantManager.getOrdersJson();
                        // dataOutput.writeUTF("ORDERS");
                        // dataOutput.writeUTF(ordersJson);
                        break;

                    case "GET_HISTORY":
                        String historyJson = restaurantManager.getRecordsJson();
                        dataOutput.writeUTF("HISTORY");
                        dataOutput.writeUTF(historyJson);
                        dataOutput.flush();
                        break;

                    case "FINISH_ORDER":
                        String finishOrderJson = dataInput.readUTF();
                        Order finishOrder = gson.fromJson(finishOrderJson, Order.class);
                        restaurantManager.finishOrder(finishOrder);

                        break;

                    case "REGISTER_STATION": {
                        String stationName = dataInput.readUTF();
                        Station station = restaurantManager.findStationByName(stationName);

                        if (station == null) {
                            dataOutput.writeUTF("REGISTER_ERROR");
                            dataOutput.writeUTF("Station not found: " + stationName);
                            dataOutput.flush();
                            break;
                        }

                        station.setClientOutput(dataOutput);

                        System.out.println("Estación registrada: " + stationName);

                        List<Order> filtered = restaurantManager.getActiveOrdersForStation(station);
                        dataOutput.writeUTF("ORDERS");
                        dataOutput.writeUTF(gson.toJson(filtered));
                        dataOutput.flush();
                        break;
                    }

                    case "EXIT":
                        running = false;
                        break;

                    default:
                        dataOutput.writeUTF("Comando no reconocido");
                }
            }

            socket.close();
            dataOutput.close();
            dataInput.close();
            System.out.println("Cliente desconectado correctamente: " + socket.getInetAddress());

        } catch (Exception e) {
            System.out.println("Error en hilo cliente: " + e.getMessage());
        }
    }
}
