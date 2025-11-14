package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import co.edu.uptc.view.styleConstans.UIStyle;

public class SubMainPanelViewCashierSuperior extends JPanel{

    public SubMainPanelViewCashierSuperior() {
        setBackground(UIStyle.BACKGROUND);
        setLayout(new BorderLayout());
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        SubPanelRight subPanelRight = new SubPanelRight();
        SubPanelCenter subPanelCenter = new SubPanelCenter(subPanelRight);
        add(subPanelCenter, BorderLayout.CENTER);
        add(subPanelRight, BorderLayout.EAST);
    }

}
