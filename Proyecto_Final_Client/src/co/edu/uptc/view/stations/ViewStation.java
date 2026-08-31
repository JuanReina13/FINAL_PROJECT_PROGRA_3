package co.edu.uptc.view.stations;

import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import co.edu.uptc.controller.ControllerStation;
import co.edu.uptc.view.styleConstans.UIStyle;

public class ViewStation extends JPanel {

    private ControllerStation controllerStation;
    private String stationName;
    private InfoPanel infoPanel;
    private OrdersPanel ordersPanel;
    private JPanel downPanel;

    public ViewStation(String stationName, ControllerStation controllerStation) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.stationName = stationName;
        this.controllerStation = controllerStation;
        setBackground(UIStyle.BACKGROUND);
        setSize(800, 600);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        infoPanel = new InfoPanel(stationName, controllerStation, this);
        add(infoPanel);
        ordersPanel = new OrdersPanel(controllerStation);
        downPanel = ordersPanel;
        add(downPanel);
        SwingUtilities.invokeLater(() -> {
            Window window = SwingUtilities.getWindowAncestor(ViewStation.this);
            if (window instanceof JFrame frame) {
                frame.setExtendedState(JFrame.NORMAL);
                frame.setSize(1200, 700);
                frame.setLocationRelativeTo(null);
            } else if (window != null) {
                window.setSize(1200, 700);
                window.setLocationRelativeTo(null);
            }
        });
    }

    public void showOrdersPanel() {
        setDownPanel(ordersPanel);
        ordersPanel.showOrders();
    }

    public void showHistoryPanel() {
        setDownPanel(new RecordPanel(controllerStation));
    }

    public void setDownPanel(JPanel newPanel) {
        remove(downPanel);
        downPanel = newPanel;
        add(downPanel);
        invalidate();
        validate();
        repaint();
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public OrdersPanel getOrdersPanel() {
        return ordersPanel;
    }

    public JPanel getDownPanel() {
        return downPanel;
    }
}
