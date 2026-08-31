package co.edu.uptc.view;

<<<<<<< HEAD
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

=======
>>>>>>> de0be60915095a10a78983eb6e53cdeb82450f0b
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import co.edu.uptc.config.AppConfig;
import co.edu.uptc.view.mainPanels.MainPanel;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    MainPanel mainPanel;
    public MainFrame() {
        requestAppConfig();
        setTitle("Hayloft Order Management");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (mainPanel.getControllerStation() != null) {
                    mainPanel.getControllerStation().stop();
                }
                dispose();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private void requestAppConfig() {
    String defaultIp = "localHost";
    String ip = "";

    while (ip.isBlank()) {
        ip = (String) JOptionPane.showInputDialog(
                this,
                "Ingrese la IP del servidor:",
                "Configuración del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                defaultIp
        );
        if (ip == null || ip.isBlank()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor ingrese una IP válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            ip = "";
        }
    }
    int defaultPort = 12691;
    int port = -1;

    while (port <= 0 || port > 65535) {
        String portInput = (String) JOptionPane.showInputDialog(
                this,
                "Ingrese el puerto:",
                "Configuración del servidor",
                JOptionPane.QUESTION_MESSAGE,
                null,
                null,
                defaultPort
        );

        try {
            port = Integer.parseInt(portInput);
        } catch (Exception e) {
            port = -1;
        }

        if (port <= 0 || port > 65535) {
            JOptionPane.showMessageDialog(
                    this,
                    "Por favor ingrese un número de puerto válido (1–65535).",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    AppConfig.HOST = ip;
    AppConfig.PORT = port;
}

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void addImageIcon() {
        ImageIcon imageIcon = new ImageIcon("resources/Main_Frame_Images/icon_frame.png");
        setIconImage(imageIcon.getImage());
    }

    private void initComponents() {
<<<<<<< HEAD
        mainPanel = new MainPanel(this);
=======
        MainPanel mainPanel = new MainPanel(this);
>>>>>>> de0be60915095a10a78983eb6e53cdeb82450f0b
        addImageIcon();
        setContentPane(mainPanel);
    }

    private void addImageIcon(){
        ImageIcon icon = new ImageIcon("resources/Main_Frame_Images/icon_frame.png");
        setIconImage(icon.getImage());
    }

    public void showPanel(JPanel newPanel) {
        setContentPane(newPanel);
        invalidate();
        validate();
        repaint();
    }
}
