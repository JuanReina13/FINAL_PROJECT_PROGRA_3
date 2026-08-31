package co.edu.uptc.view.cashier;

import javax.swing.JPanel;

import co.edu.uptc.controller.ControllerCashier;
import co.edu.uptc.view.cashier.subPanelsCashier.SubMainPanelViewCashierInferior;
import co.edu.uptc.view.cashier.subPanelsCashier.SubMainPanelViewCashierSuperior;
import java.awt.BorderLayout;


public class ViewCashier extends JPanel{

    private ControllerCashier controllerCashier;


    public ViewCashier(ControllerCashier controllerCashier) {
        this.controllerCashier = controllerCashier;
        setSize(800, 600);
        setLayout(new BorderLayout());
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        SubMainPanelViewCashierSuperior subMainPanelViewCashierSuperior = new SubMainPanelViewCashierSuperior();
        SubMainPanelViewCashierInferior subMainPanelViewCashierInferior = new SubMainPanelViewCashierInferior();
        add(subMainPanelViewCashierSuperior, BorderLayout.CENTER);
        add(subMainPanelViewCashierInferior, BorderLayout.SOUTH);
    }
}
