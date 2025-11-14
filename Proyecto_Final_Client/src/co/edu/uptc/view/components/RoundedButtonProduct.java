package co.edu.uptc.view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import co.edu.uptc.model.Product;
import co.edu.uptc.persistence.ProductRepository;
import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.ShoppingCart;
import co.edu.uptc.view.styleConstans.UIStyle;

public class RoundedButtonProduct extends JButton{

    private Color backgroundColor;
    private Product product;


    public RoundedButtonProduct(String productName, Color backgroundColor, ShoppingCart shoppingCart) {
        this.backgroundColor = backgroundColor;
        this.product = findProduct(productName);
        if (product != null) {
            setText(product.getName() + " - USD " + product.getPrice());
        } else {
            setText(productName + " (No encontrado)");
            System.err.println("[Advertencia] Producto no encontrado: " + productName);
        }
        initComponents(shoppingCart);
    }

    private void initComponents(ShoppingCart shoppingCart){
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(UIStyle.TEXT_DARK);
        setFont(UIStyle.SUBTITLE_FONT);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (product != null) {
                    System.out.println("Seleccionado: " + product.getName());
                    shoppingCart.addProduct(1, product.getName(), product.getPrice(),product.getCategory());
                }
            }
        });
    }

    private Product findProduct(String id) {
        ProductRepository repo = new ProductRepository();
        Product found = repo.findProductById(id);
        if (found == null) {
            System.err.println("[Error] No se encontró el producto con nombre: '" + id + "'");
        }
        return found;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - 4;
        g2.setStroke(new BasicStroke(3f));
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() -1, 30, 30);
        g2.setColor(getForeground());
        g2.drawString(getText(), x, y);
        g2.dispose();
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public Product getProduct() {
        return product;
    }
}

