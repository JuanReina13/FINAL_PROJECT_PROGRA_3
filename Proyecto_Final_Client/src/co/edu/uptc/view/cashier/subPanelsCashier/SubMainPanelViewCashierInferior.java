package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import co.edu.uptc.view.styleConstans.UIStyle;

public class SubMainPanelViewCashierInferior extends JPanel{

    public SubMainPanelViewCashierInferior() {
        setPreferredSize(new Dimension(800, 30));
        setLayout(new BorderLayout());
        setBackground(UIStyle.BACKGROUND_INFERIOR_COLOR);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JLabel brandLabel = new JLabel("THE HAYLOFT ®", SwingConstants.CENTER);
        brandLabel.setFont(UIStyle.BRAND_FONT);
        brandLabel.setForeground(UIStyle.TEXT_PRIMARY);
        add(brandLabel, BorderLayout.CENTER);
    }

}
