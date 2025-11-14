package co.edu.uptc.view.cashier.subPanelsCashier.categoryPanels;

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

public class HotDogsPanel extends JPanel{

    private RoundedButtonCardLayout btnBack;
    private RoundedButtonProduct btnBaconCheese;
    private RoundedButtonProduct btnChicago;
    private RoundedButtonProduct btnDetroit;
    private RoundedButtonProduct btnClassic;


    public HotDogsPanel(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents(subPanelCenter, subPanelRight);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        btnBack = new RoundedButtonCardLayout("VOLVER", UIStyle.ACTION_CANCEL, "buttonCategoryPanel", subPanelCenter);
        btnBaconCheese = new RoundedButtonProduct("hd-bacon-cheese", UIStyle.P2,subPanelRight.getShoppingCart());
        btnChicago = new RoundedButtonProduct("hd-chicago", UIStyle.P1,subPanelRight.getShoppingCart());
        btnDetroit = new RoundedButtonProduct("hd-detroit", UIStyle.P3,subPanelRight.getShoppingCart());
        btnClassic = new RoundedButtonProduct("hd-classic", UIStyle.P4,subPanelRight.getShoppingCart());

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
        gbc.gridy = 1; add(btnClassic, gbc);
        gbc.gridy = 2; add(btnDetroit, gbc);
        gbc.gridy = 3; add(btnChicago, gbc);
        gbc.gridy = 4; add(btnBaconCheese, gbc);
    }
}
