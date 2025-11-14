package co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import co.edu.uptc.model.Product;
import co.edu.uptc.view.styleConstans.UIStyle;

public class ShoppingCart extends JPanel {

    private ActionButtonsPanel actionButtonsPanel;
    private List<Product> cartProducts = new ArrayList<>();


    public ShoppingCart() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(UIStyle.BACKGROUND_INFERIOR_COLOR);
        setVisible(true);
    }

    public void addProduct(int quantity, String name, double price, String category) {
        for (Component c : getComponents()) {
            if (c instanceof FieldProductPanel panel) {
                if (panel.getProductName().equalsIgnoreCase(name)) {
                    int currentQty = panel.getQuantity();
                    panel.setQuantity(String.valueOf(currentQty + quantity));
                    updateCartTotal();
                    return;
                }
            }
        }

        FieldProductPanel newPanel = new FieldProductPanel(
                String.valueOf(quantity), name, price, category);
        add(newPanel);
        updateCartTotal();
        revalidate();
        repaint();
    }

    public void updateCartTotal() {
        double total = 0;
        for (Component c : getComponents()) {
            if (c instanceof FieldProductPanel panel) {
                total += panel.getQuantity() * panel.getUnitPrice();
            }
        }
        if (actionButtonsPanel != null) {
            actionButtonsPanel.setTotal(total);
        }
    }

    public void clearCartUI() {
        removeAll();
        revalidate();
        repaint();
    }

    public void clearProducts() {
        cartProducts.clear();
    }

    @Override
    public Dimension getPreferredSize() {
        int height = getComponentCount() * 130;
        return new Dimension(330, height);
    }

    public void setActionButtonsPanel(ActionButtonsPanel panel) {
        this.actionButtonsPanel = panel;
    }
}
