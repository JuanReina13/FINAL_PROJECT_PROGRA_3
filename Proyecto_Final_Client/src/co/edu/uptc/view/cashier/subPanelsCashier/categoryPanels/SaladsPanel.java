package co.edu.uptc.view.cashier.subPanelsCashier.categoryPanels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.view.cashier.subPanelsCashier.SubPanelCenter;
import co.edu.uptc.view.cashier.subPanelsCashier.SubPanelRight;
import co.edu.uptc.view.components.RoundedButtonProduct;
import co.edu.uptc.view.components.RoundedButtonCardLayout;
import co.edu.uptc.view.styleConstans.UIStyle;

public class SaladsPanel extends JPanel{

   private RoundedButtonCardLayout btnBack;
    private RoundedButtonProduct btnCaesar;
    private RoundedButtonProduct btnChef;
    private RoundedButtonProduct btnCobb;
    private RoundedButtonProduct btnMediterranean;

    public SaladsPanel(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents(subPanelCenter, subPanelRight);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        btnBack = new RoundedButtonCardLayout("VOLVER", UIStyle.ACTION_CANCEL, "buttonCategoryPanel", subPanelCenter);
        btnCaesar = new RoundedButtonProduct("sd-caesar", UIStyle.P7,subPanelRight.getShoppingCart());
        btnChef = new RoundedButtonProduct("sd-chef", UIStyle.P4,subPanelRight.getShoppingCart());
        btnCobb = new RoundedButtonProduct("sd-cobb", UIStyle.P8,subPanelRight.getShoppingCart());
        btnMediterranean = new RoundedButtonProduct("sd-mediterranean", UIStyle.P6,subPanelRight.getShoppingCart());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0; gbc.gridy = 0;
        add(btnBack, gbc);

        JLabel label = new JLabel("");
        gbc.gridx = 1;
        add(label, gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridy = 1; add(btnCaesar, gbc);
        gbc.gridy = 2; add(btnChef, gbc);
        gbc.gridy = 3; add(btnCobb, gbc);
        gbc.gridy = 4; add(btnMediterranean, gbc);
    }

}
