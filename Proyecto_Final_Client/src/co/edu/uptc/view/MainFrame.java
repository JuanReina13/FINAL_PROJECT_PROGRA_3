package co.edu.uptc.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import co.edu.uptc.view.mainPanels.MainPanel;

public class MainFrame extends JFrame {

    private static MainFrame instance;


    public MainFrame() {
        setTitle("Hayloft Order Management");
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setVisible(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }

    private void initComponents() {
        MainPanel mainPanel = new MainPanel(this);
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
