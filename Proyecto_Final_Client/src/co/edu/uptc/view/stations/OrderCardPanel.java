package co.edu.uptc.view.stations;

import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.MouseEvent;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

import co.edu.uptc.controller.ControllerStation;
import co.edu.uptc.view.components.RoundedPanelUI;
import co.edu.uptc.view.components.ScrollBarUI;
import co.edu.uptc.view.styleConstans.UIStyle;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.List;

public class OrderCardPanel extends RoundedPanelUI {

    ControllerStation controllerStation;
    private boolean isActive;
    private String orderId;

    public OrderCardPanel(String ordenId, String table, String time, List<String> products, boolean isActive, ControllerStation controllerStation) {
        super(UIStyle.BACKGROUND, 20);
        this.isActive = isActive;
        this.orderId = orderId;
        this.controllerStation = controllerStation;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 400));
        setMaximumSize(new Dimension(260, Integer.MAX_VALUE));

        initComponents(table, formatTime(time), products);
    }

    private void initComponents(String table, String time, List<String> products) {
        add(Box.createHorizontalStrut(20));
        addHeaderPanel(table, time);
        addItemsPanel(products);
    }

    private void addHeaderPanel(String table, String time) {
        Color color = UIStyle.getRandomHeaderColor();
        RoundedPanelUI headerPanel = new RoundedPanelUI(color, 15);
        headerPanel.setBackground(color);
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblCustomer = new JLabel(table);
        lblCustomer.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCustomer.setForeground(Color.WHITE);

        JPanel rightHeader = new JPanel(new GridLayout(2, 1));
        rightHeader.setBackground(color);
        JLabel lblTime = new JLabel(time, SwingConstants.RIGHT);
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTime.setForeground(Color.WHITE);

        rightHeader.add(lblTime);

        headerPanel.add(lblCustomer, BorderLayout.WEST);
        headerPanel.add(rightHeader, BorderLayout.EAST);

        if (isActive) {
            headerPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
            headerPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    headerPanel.setBackground(color.darker());
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    headerPanel.setBackground(color);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    showConfirmationPanel(headerPanel, table, time, color);
                }
            });
        }
        add(headerPanel, BorderLayout.NORTH);
    }

    private void showConfirmationPanel(RoundedPanelUI headerPanel, String table, String time, Color color) {
        headerPanel.removeAll();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnConfirm = addButton("resources/buttons_Images/Check.png", 55, 55);
        JButton btnCancel = addButton("resources/buttons_Images/Cancel.png", 55, 55);
    
        btnConfirm.addActionListener(e -> {
            controllerStation.sendFinishOrderById(orderId);
            restoreHeader(headerPanel, table, time, color);
        });

        btnCancel.addActionListener(e -> restoreHeader(headerPanel, table, time, color));

        headerPanel.add(btnConfirm);
        headerPanel.add(btnCancel);
        headerPanel.revalidate();
        headerPanel.repaint();
    }

    private void restoreHeader(RoundedPanelUI headerPanel, String table, String time, Color color) {
        headerPanel.removeAll();
        headerPanel.setLayout(new BorderLayout());

        JLabel lblCustomer = new JLabel(table);
        lblCustomer.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCustomer.setForeground(Color.WHITE);

        JLabel lblTime = new JLabel(time, SwingConstants.RIGHT);
        lblTime.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblTime.setForeground(Color.WHITE);

        JPanel rightHeader = new JPanel(new GridLayout(2, 1));
        rightHeader.setBackground(color);
        rightHeader.add(lblTime);

        headerPanel.add(lblCustomer, BorderLayout.WEST);
        headerPanel.add(rightHeader, BorderLayout.EAST);
        headerPanel.revalidate();
        headerPanel.repaint();
    }

    private JButton addButton(String text, int width, int height) {
        ImageIcon icon = new ImageIcon(text);
        JButton button = new JButton(
                new ImageIcon(icon.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH)));
        button.setPreferredSize(new Dimension(width, height));
        button.setMaximumSize(new Dimension(width, height));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private void addItemsPanel(List<String> products) {
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        for (String item : products) {
            String[] parts = item.split("\\|");
            String main = parts[0].trim();
            String extra = parts.length > 1 ? parts[1].trim() : null;

            // Texto principal
            JTextArea txtMain = new JTextArea(main);
            txtMain.setFont(new Font("Segoe UI", Font.BOLD, 16));
            txtMain.setForeground(new Color(30, 30, 30));
            txtMain.setOpaque(false);
            txtMain.setEditable(false);
            txtMain.setFocusable(false);
            txtMain.setLineWrap(true);
            txtMain.setWrapStyleWord(true);
            txtMain.setAlignmentX(Component.LEFT_ALIGNMENT);
            txtMain.setBorder(new EmptyBorder(3, 0, 0, 0));

            FontMetrics fm = txtMain.getFontMetrics(txtMain.getFont());
            int lineHeight = fm.getHeight();
            int lines = (int) Math.ceil((double) fm.stringWidth(main) / 210.0);
            int preferredHeight = Math.max(lineHeight * lines + 5, lineHeight + 5);
            txtMain.setMaximumSize(new Dimension(230, preferredHeight));
            txtMain.setPreferredSize(new Dimension(230, preferredHeight));

            itemsPanel.add(txtMain);

            // Subtexto (extras)
            if (extra != null && !extra.isEmpty()) {
                JLabel lblExtra = new JLabel(extra);
                lblExtra.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                lblExtra.setForeground(new Color(120, 120, 120));
                lblExtra.setBorder(new EmptyBorder(0, 15, 5, 0));
                lblExtra.setAlignmentX(Component.LEFT_ALIGNMENT);
                itemsPanel.add(lblExtra);
            }

            itemsPanel.add(Box.createVerticalStrut(10));
        }

        // Solo el panel de productos tiene scroll si se excede la altura
        int estimatedHeight = products.size() * 40 + 40;
        int maxHeight = 250;
        JScrollPane scrollPane = new JScrollPane(itemsPanel, estimatedHeight > maxHeight
                ? JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
                : JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(260, Math.min(estimatedHeight, maxHeight)));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);
    }

    private String formatTime(String timestamp) {
        Instant instant = Instant.ofEpochMilli(Long.parseLong(timestamp));
        LocalTime time = instant.atZone(ZoneId.systemDefault()).toLocalTime();
        return String.format("%02d:%02d", time.getHour(), time.getMinute());
    }
}