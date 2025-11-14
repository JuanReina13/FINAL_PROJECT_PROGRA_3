package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.edu.uptc.controller.ControllerCashier;
import co.edu.uptc.model.Order;
import co.edu.uptc.model.Product;
import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.ActionButtonsPanel;
import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.FieldProductPanel;
import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.ShoppingCart;
import co.edu.uptc.view.components.ScrollBarUI;
import co.edu.uptc.view.styleConstans.UIStyle;

public class SubPanelRight extends JPanel {

    private ShoppingCart shoppingCart;
    private ActionButtonsPanel actionButtonsPanel;
    private JTextField commentField;
    JScrollPane scrollPane;


    public SubPanelRight() {
        setBackground(UIStyle.BACKGROUND);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(370, 800));
        setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 4));
        setBorder(BorderFactory.createMatteBorder(3, 3, 0, 3, UIStyle.TEXT_DARK));
        setVisible(true);
        shoppingCart = new ShoppingCart();
        initComponents();
    }

    private void initComponents() {
        addUpPanel();
        addDownPanel();
    }

    private void addUpPanel() {
        scrollPane = new JScrollPane(shoppingCart);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addDownPanel() {
        actionButtonsPanel = new ActionButtonsPanel();
        actionButtonsPanel.addDeleteListener(e->{
            if (shoppingCart != null) {
                shoppingCart.clearProducts();
                shoppingCart.clearCartUI();
                actionButtonsPanel.setTotal(0.00);
                commentField.setText("");
            }
        });
        actionButtonsPanel.addSendListener(e -> {
            ControllerCashier controller = new ControllerCashier();
            Order order = new Order();
            order.setIdOrder(UUID.randomUUID().toString());
            order.setTime(System.currentTimeMillis() + "");
            order.setTable(commentField.getText());
            List<Product> productList = new ArrayList<>();
            Set<String> categorySet = new HashSet<>();
            for (Component c : shoppingCart.getComponents()) {
                if (c instanceof FieldProductPanel panel) {

                    Product product = new Product(
                            null,
                            panel.getProductName() + "|" + panel.getTextPane().getText(),
                            panel.getCategory(),
                            panel.getUnitPrice(),
                            panel.getQuantity());
                    productList.add(product);
                    categorySet.add(panel.getCategory());
                }
            }
            order.setProducts(productList);
            order.setCategoriesInvolved(new ArrayList<>(categorySet));
            saveOrderAsJson(order);
            controller.sendNewOrder(order);
            System.out.println("Orden enviada");
            if (shoppingCart != null) {
                shoppingCart.clearProducts();
                shoppingCart.clearCartUI();
                actionButtonsPanel.setTotal(0.00);
                commentField.setText("");
            }
        });
        shoppingCart.setActionButtonsPanel(actionButtonsPanel);
        add(createJTextFieldPanel(), BorderLayout.SOUTH);
    }

    private void saveOrderAsJson(Order order) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);
        try (FileWriter file = new FileWriter("data/order.json")) {
            file.write(json);
            System.out.println("Archivo JSON guardado correctamente.");
        } catch (IOException e) {
            System.out.println("Error guardando JSON: " + e.getMessage());
        }
    }

    private JPanel createJTextFieldPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(UIStyle.BACKGROUND);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        commentField = new JTextField();
        commentField.setPreferredSize(new Dimension(350, 40));
        commentField.setFont(UIStyle.TEXT_FONT);
        commentField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIStyle.TEXT_DARK, 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        commentField.setBackground(UIStyle.BORDER_COLOR2);
        JLabel tableLabel = new JLabel("Mesa:");
        tableLabel.setFont(UIStyle.SUBTITLE_FONT);
        bottomPanel.add(tableLabel, BorderLayout.NORTH);
        bottomPanel.add(commentField, BorderLayout.CENTER);
        bottomPanel.add(actionButtonsPanel, BorderLayout.SOUTH);
        return bottomPanel;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public ActionButtonsPanel getActionButtonsPanel() {
        return actionButtonsPanel;
    }

    public String getComment() {
        return commentField.getText();
    }
}
