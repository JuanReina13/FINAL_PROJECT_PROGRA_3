package co.edu.uptc.view.stations;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.controller.ControllerStation;
import co.edu.uptc.view.components.ShapedButtonUI;
import co.edu.uptc.view.styleConstans.UIStyle;

public class InfoPanel extends JPanel {

    private ControllerStation controllerStation;
    private ViewStation viewStation;
    private String stationName;
    private JButton openButton;
    private JButton recordButton;
    private int orderCount = 0;

    public InfoPanel(String stationName, ControllerStation controllerStation, ViewStation viewStation) {
        this.stationName = stationName;
        this.controllerStation = controllerStation;
        this.viewStation = viewStation;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(UIStyle.BACKGROUND_COLOR);
        setPreferredSize(new Dimension(Integer.MAX_VALUE, 120));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        add(Box.createHorizontalStrut(40));
        addJLabel(stationName);

        add(Box.createHorizontalGlue());
        openButton = createButton(orderCount + "   Ordenes", new Dimension(150, 50));
        openButtonAction();
        openButton.setBackground(UIStyle.SECONDARY_COLOR);
        add(openButton);

        add(Box.createHorizontalStrut(50));
        recordButton = createButton("Historial", new Dimension(150, 50));
        recordButtonAction();
        add(recordButton);

        add(Box.createHorizontalGlue());
        add(Box.createHorizontalStrut(50));
    }

    private void addJLabel(String text) {
        String title;

        if (text.equals("MainKitchen")) {
            title = "Cocina";
        } else if (text.equals("Expeditor")) {
            title = "Expedidor";
        } else {
            title = text;
        }
        JLabel namelabel = new JLabel("Estación: " + title);
        UIStyle.styleLabel(namelabel, true);
        add(namelabel);
    }

    private JButton createButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setBackground(UIStyle.PRIMARY_COLOR);
        button.setForeground(UIStyle.BACKGROUND_COLOR);
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setContentAreaFilled(false);
        button.setUI(new ShapedButtonUI());
        return button;
    }

    private void openButtonAction() {
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(openButton, recordButton);
                viewStation.setDownPanel(new OrdersPanel(controllerStation));
            }
        });
    }

    private void recordButtonAction() {
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setActiveButton(recordButton, openButton);
                controllerStation.requestHistory();
            }
        });
    }

    private void setActiveButton(JButton b1, JButton b2) {
        b1.setBackground(UIStyle.SECONDARY_COLOR);
        b2.setBackground(UIStyle.PRIMARY_COLOR);
    }

    public void setOrderCount(int count) {
        this.orderCount = count;
        openButton.setText(this.orderCount + "   Ordenes");
    }
}
