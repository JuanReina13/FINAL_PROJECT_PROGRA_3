package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import co.edu.uptc.view.MainFrame;
import co.edu.uptc.view.mainPanels.MainPanel;
import co.edu.uptc.view.styleConstans.UIStyle;

public class SubMainPanelViewCashierSuperior extends JPanel{

    public SubMainPanelViewCashierSuperior(MainFrame mainFrame, MainPanel mainPanel) {
        setBackground(UIStyle.BACKGROUND);
        setLayout(new BorderLayout());
        initComponents(mainFrame, mainPanel);
        setVisible(true);
    }

    private void initComponents(MainFrame mainFrame, MainPanel mainPanel) {
        SubPanelRight subPanelRight = new SubPanelRight();
        SubPanelCenter subPanelCenter = new SubPanelCenter(subPanelRight, mainFrame, mainPanel);
        add(subPanelCenter, BorderLayout.CENTER);
        add(subPanelRight, BorderLayout.EAST);
    }

}
