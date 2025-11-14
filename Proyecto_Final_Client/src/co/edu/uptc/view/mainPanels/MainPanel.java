package co.edu.uptc.view.mainPanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import co.edu.uptc.controller.ControllerCashier;
import co.edu.uptc.controller.ControllerStation;
import co.edu.uptc.view.MainFrame;
import co.edu.uptc.view.cashier.ViewCashier;
import co.edu.uptc.view.stations.ViewStation;

public class MainPanel extends JPanel {

    private MainFrame mainFrame;
    private JButton btnCashier;
    private JButton btnPizza;
    private JButton btnKitchen;
    private JButton btnExpeditor;


    public MainPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));
        add(createButtonsPanel(), BorderLayout.CENTER);
        add(createSuperiorLabel("Seleccione la estación a la que desea ingresar"), BorderLayout.NORTH);
        addActionListenersToButtons();
    }

    private JPanel createButtonsPanel(){
        JPanel panel = new JPanel(new GridLayout(1, 4, 20, 10));
        panel.setBackground(new Color(245, 247, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 10, 40));
        btnCashier = createButtonWithImage("resources/Main_Frame_Images/cash_register.png", "Caja");
        btnPizza = createButtonWithImage("resources/Main_Frame_Images/pizza.png", "Pizzas");
        btnKitchen = createButtonWithImage("resources/Main_Frame_Images/fast_food.png", "Cocina Principal");
        btnExpeditor = createButtonWithImage("resources/Main_Frame_Images/waiter.png", "Expedidor");
        panel.add(btnCashier);
        panel.add(btnPizza);
        panel.add(btnKitchen);
        panel.add(btnExpeditor);
        return panel;
    }

    private JLabel createSuperiorLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 26));
        label.setForeground(new Color(80, 80, 80));
        label.setBorder(BorderFactory.createEmptyBorder(40, 10, 0, 10));
        return label;
    }

    private void openCashier() {
        ViewCashier viewCashier = new ViewCashier(new ControllerCashier());
        viewCashier.setVisible(true);
        mainFrame.showPanel(viewCashier);
    }

    private void openStation(String name) {
        ControllerStation controller = new ControllerStation(name);
        controller.start();
        ViewStation viewStation = new ViewStation(name, controller);
        controller.setViewStation(viewStation);
        viewStation.setVisible(true);
        mainFrame.showPanel(viewStation);
    }

    private void addActionListenersToButtons() {
        btnCashier.addActionListener(e -> openCashier());
        btnPizza.addActionListener(e -> openStation("Pizza"));
        btnKitchen.addActionListener(e -> openStation("MainKitchen"));
        btnExpeditor.addActionListener(e -> openStation("Expeditor"));
    }

    private JButton createButtonWithImage(String ImagePath, String text) {
        Image img;
        try {
            img = ImageIO.read(new File(ImagePath));
        } catch (Exception e) {
            img = new BufferedImage(80, 80, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = ((BufferedImage) img).createGraphics();
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, 80, 80);
            g.dispose();
        }
        Image imgEscalada = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        JButton button = new JButton(text, new ImageIcon(imgEscalada));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setForeground(new Color(60, 60, 60));
        button.setFocusPainted(false);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setBackground(new Color(245, 247, 250));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(225, 235, 255)); // tono más azul
                button.setBorder(BorderFactory.createLineBorder(new Color(100, 150, 255), 2, true));
                button.setOpaque(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(245, 247, 250));
                button.setBorder(null);
                button.setOpaque(true);
            }
        });
        return button;
    }

}
