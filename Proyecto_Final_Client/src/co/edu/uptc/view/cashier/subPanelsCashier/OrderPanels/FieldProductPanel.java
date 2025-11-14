package co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Container;

import co.edu.uptc.view.components.ScrollBarUI;
import co.edu.uptc.view.styleConstans.UIStyle;

public class FieldProductPanel extends JPanel {

    private JLabel numberLabel;
    private JLabel nameLabel;
    private JButton closeButton;
    private JTextPane textPane;
    private JScrollPane scrollPane;
    private String productName;
    public double unitPrice;
    private JLabel priceLabel;
    private String category;


    public FieldProductPanel(String number, String name, double unitPrice, String category) {
    this.productName = name;
    this.unitPrice = unitPrice;
    this.category = category;
    setLayout(null);
    setOpaque(false);
    setPreferredSize(new Dimension(330, 120));
    initComponents(number, name);
}


    private void initComponents(String number, String name) {
        addLabels(number, name);
        addCloseButton();
        addScrollTextPane();
    }

    private void addLabels(String number, String name) {
        numberLabel = new JLabel(number);
        numberLabel.setFont(UIStyle.TITLE_FONT);
        numberLabel.setBounds(10, 15, 30, 25);
        add(numberLabel);

        nameLabel = new JLabel(name);
        nameLabel.setFont(UIStyle.SUBTITLE_FONT2);
        nameLabel.setBounds(40, 15, 250, 25);
        add(nameLabel);

        priceLabel = new JLabel();
        priceLabel.setFont(UIStyle.SUBTITLE_FONT2);
        priceLabel.setBounds(50, 45, 120, 25);
        updatePriceLabel();
        add(priceLabel);
    }

    private void addCloseButton() {
        closeButton = new JButton("✕");
        closeButton.setFocusPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFont(UIStyle.BUTTON_FONT);
        closeButton.setBounds(290, -12, 50, 50);
        closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int currentQuantity = getQuantity();
                if (currentQuantity > 1) {
                    setQuantity(String.valueOf(currentQuantity - 1));
                } else {
                    Container parent = FieldProductPanel.this.getParent();
                    if (parent != null) {
                        parent.remove(FieldProductPanel.this);
                        parent.revalidate();
                        parent.repaint();
                    }
                if (parent instanceof ShoppingCart) {
                    ((ShoppingCart) parent).updateCartTotal();
                }
                }
                Container parent = FieldProductPanel.this.getParent();
                if (parent instanceof ShoppingCart) {
                    ((ShoppingCart) parent).updateCartTotal();
                }
            }
        });
        add(closeButton);
    }

    private void addScrollTextPane() {
        textPane = new JTextPane();
        textPane.setFont(UIStyle.TEXT_FONT);
        textPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(20, 75, 290, 40);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        add(scrollPane);
    }

    private void updatePriceLabel() {
        int qty = getQuantity();
        double total = qty * unitPrice;
        priceLabel.setText(String.format("$ %.2f", total));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(240, 240, 240));
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.dispose();
    }

    public String getProductName() {
        return productName;
    }

    public void setQuantity(String number) {
        numberLabel.setText(number);
        updatePriceLabel();
    }

    public int getQuantity() {
        return Integer.parseInt(numberLabel.getText());
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public JButton getCloseButton() {
        return closeButton;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }
}