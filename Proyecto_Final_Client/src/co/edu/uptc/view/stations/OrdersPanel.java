package co.edu.uptc.view.stations;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.controller.ControllerStation;
import co.edu.uptc.view.MainFrame;
import co.edu.uptc.view.components.OrderViewData;
import co.edu.uptc.view.components.ScrollBarUI;
import co.edu.uptc.view.mainPanels.MainPanel;
import co.edu.uptc.view.styleConstans.UIStyle;

public class OrdersPanel extends JPanel {

    private ControllerStation controllerStation;
    private JPanel ordersContainer;
    private JScrollPane scrollPane;
    private List<OrderCardPanel> orderCards;
    
    private JButton btnReturn;


    public OrdersPanel(ControllerStation controllerStation, MainFrame mainFrame, MainPanel mainPanel) {
        orderCards = new ArrayList<>();
        this.controllerStation = controllerStation;
        controllerStation.requestOrders();
        setLayout(new BorderLayout());
        setBackground(UIStyle.TEXT_COLOR);
        initComponents(mainFrame, mainPanel);
    }

    private void initComponents(MainFrame mainFrame, MainPanel mainPanel) {
        ordersContainer = new JPanel();
        ordersContainer.setLayout(new BoxLayout(ordersContainer, BoxLayout.X_AXIS));
        ordersContainer.setBackground(UIStyle.TEXT_COLOR);
        ordersContainer.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        addHeaderPanel(mainFrame, mainPanel);
        
        addJScrollPane();
    }
    
    private void addHeaderPanel(MainFrame mainFrame, MainPanel mainPanel) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(UIStyle.TEXT_COLOR);
        headerPanel.setBorder(new EmptyBorder(10, 15, 0, 15));
        
        btnReturn = new JButton("Escoger Estación");
        btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnReturn.addActionListener(e -> {
            mainFrame.showPanel(mainPanel);
        });
        
        headerPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
        headerPanel.add(btnReturn, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
    }

    private void addJScrollPane() {
        scrollPane = new JScrollPane(ordersContainer);
        scrollPane.setBackground(UIStyle.TEXT_COLOR);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUI(new ScrollBarUI());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void addOrderCard(OrderCardPanel orderCard) {
        orderCards.add(orderCard);
        orderCard.setAlignmentY(Component.TOP_ALIGNMENT);
        ordersContainer.add(orderCard);
        ordersContainer.add(Box.createHorizontalStrut(20));
        ordersContainer.revalidate();
        ordersContainer.repaint();
    }

    public void removeOrderCard(OrderCardPanel orderCard) {
        orderCards.remove(orderCard);
        ordersContainer.remove(orderCard);
        ordersContainer.revalidate();
        ordersContainer.repaint();
    }

    public void showOrders() {
        ordersContainer.removeAll();
        for (OrderCardPanel orderCard : orderCards) {
            orderCard.setAlignmentY(Component.TOP_ALIGNMENT);
            ordersContainer.add(orderCard);
            ordersContainer.add(Box.createHorizontalStrut(20));
        }
        ordersContainer.revalidate();
        ordersContainer.repaint();
    }

    private List<OrderCardPanel> convertToOrderCards() {
        List<OrderCardPanel> orderCards = new ArrayList<>();
        List<OrderViewData> orders = controllerStation.getOrdersViewData();

        for (OrderViewData data : orders) {
            OrderCardPanel card = new OrderCardPanel(
                    data.idOrder(),
                    data.table(),
                    data.time(),
                    data.products(),
                    true,
                    controllerStation);
            orderCards.add(card);
        }
        return orderCards;
    }

    public void refreshOrders() {
        this.orderCards = convertToOrderCards();
        controllerStation.updateOrderCount(this.orderCards.size());
        showOrders();
    }
}
