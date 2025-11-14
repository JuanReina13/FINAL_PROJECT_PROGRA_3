package co.edu.uptc.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import co.edu.uptc.model.Order;

public class ControllerCashier {

    private static final String HOST = "localhost";
    private static final int PORT = 49045;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;
    private Gson gson = new Gson();
    private Socket socket;
    private boolean connected = false;

    public ControllerCashier() {
        try {
            socket = new Socket(HOST, PORT);
            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(socket.getInputStream());
            connected = true;
        } catch (IOException e) {
            System.out.println("Error conectando la caja: " + e.getMessage());
        }
    }

    public void sendNewOrder(Order order) {
        if (connected) {
            try {
                dataOutput.writeUTF("NEW_ORDER");
                dataOutput.writeUTF(gson.toJson(order));
                dataOutput.flush();
            } catch (IOException e) {
                System.out.println("Error enviando la orden: " + e.getMessage());
            }
        } else {
            System.out.println("No está conectado al servidor.");
        }
    }

    public void requestHistory() {
        if (connected) {
            try {
                dataOutput.writeUTF("GET_HISTORY");
                dataOutput.flush();
                String historyJson = dataInput.readUTF();
                Order[] history = gson.fromJson(historyJson, Order[].class);

            } catch (IOException e) {
                System.out.println("Error solicitando historial: " + e.getMessage());
            }
        } else {
            System.out.println("No está conectado al servidor.");
        }
    }

    public void closeConnection() {
        try {
            if (connected) {
                dataOutput.writeUTF("EXIT");
                socket.close();
                connected = false;
            }
        } catch (IOException e) {
            System.out.println("Error cerrando conexión: " + e.getMessage());
        }
    }

}
