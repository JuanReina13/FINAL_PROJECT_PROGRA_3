package co.edu.uptc.view.cashier;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import co.edu.uptc.controller.ControllerCashier;
import co.edu.uptc.view.MainFrame;
import co.edu.uptc.view.cashier.subPanelsCashier.SubMainPanelViewCashierInferior;
import co.edu.uptc.view.cashier.subPanelsCashier.SubMainPanelViewCashierSuperior;
import co.edu.uptc.view.mainPanels.MainPanel;


public class ViewCashier extends JPanel{

    private ControllerCashier controllerCashier;


    public ViewCashier(ControllerCashier controllerCashier, MainFrame mainFrame, MainPanel mainPanel) {
        this.controllerCashier = controllerCashier;
        setSize(800, 600);
        setLayout(new BorderLayout());
        initComponents(mainFrame, mainPanel);
        setVisible(true);
    }


    private void initComponents(MainFrame mainFrame, MainPanel mainPanel) {
        SubMainPanelViewCashierSuperior subMainPanelViewCashierSuperior = new SubMainPanelViewCashierSuperior(mainFrame,mainPanel);
        SubMainPanelViewCashierInferior subMainPanelViewCashierInferior = new SubMainPanelViewCashierInferior();
        add(subMainPanelViewCashierSuperior, BorderLayout.CENTER);
        add(subMainPanelViewCashierInferior, BorderLayout.SOUTH);
    }
}
