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

public class PizzasPanel extends JPanel {

    private RoundedButtonCardLayout btnBack;
    private RoundedButtonProduct btnPepperoni;
    private RoundedButtonProduct btnSupreme;
    private RoundedButtonProduct btnHawaiian;
    private RoundedButtonProduct btnRanch;
    private RoundedButtonProduct btnBbq;
    private RoundedButtonProduct btnMeatLovers;
    private RoundedButtonProduct btnBlt;
    private RoundedButtonProduct btnJalapenoPopper;
    private RoundedButtonProduct btnHaysticks;
    private RoundedButtonProduct btnHaysticksWithCheese;


    public PizzasPanel(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents(subPanelCenter, subPanelRight);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {

        btnBack = new RoundedButtonCardLayout("VOLVER", UIStyle.ACTION_CANCEL, "buttonCategoryPanel", subPanelCenter);
        btnPepperoni = new RoundedButtonProduct("pz-pepperoni", UIStyle.P2,subPanelRight.getShoppingCart());
        btnSupreme = new RoundedButtonProduct("pz-supreme", UIStyle.P3,subPanelRight.getShoppingCart());
        btnHawaiian = new RoundedButtonProduct("pz-hawaiian", UIStyle.P4,subPanelRight.getShoppingCart());
        btnRanch = new RoundedButtonProduct("pz-ranch", UIStyle.P9,subPanelRight.getShoppingCart());
        btnBbq = new RoundedButtonProduct("pz-bbq", UIStyle.P8,subPanelRight.getShoppingCart());
        btnRanch.setForeground(UIStyle.TEXT_DARK);
        btnMeatLovers = new RoundedButtonProduct("pz-meatlovers", UIStyle.P9,subPanelRight.getShoppingCart());
        btnBlt = new RoundedButtonProduct("pz-blt", UIStyle.P5,subPanelRight.getShoppingCart());
        btnJalapenoPopper = new RoundedButtonProduct("pz-jalapeno", UIStyle.P7,subPanelRight.getShoppingCart());
        btnHaysticks = new RoundedButtonProduct("pz-haysticks", UIStyle.P6,subPanelRight.getShoppingCart());
        btnHaysticksWithCheese = new RoundedButtonProduct("pz-haysticks-cheese", UIStyle.P4,subPanelRight.getShoppingCart());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnBack, gbc);

        JLabel label = new JLabel("");
        gbc.gridx = 1;
        add(label, gbc);

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(btnPepperoni, gbc);

        gbc.gridy = 2;
        add(btnSupreme, gbc);

        gbc.gridy = 3;
        add(btnHawaiian, gbc);

        gbc.gridy = 4;
        add(btnJalapenoPopper, gbc);

        gbc.gridy = 5;
        add(btnHaysticks, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(btnBbq, gbc);

        gbc.gridy = 2;
        add(btnRanch, gbc);

        gbc.gridy = 3;
        add(btnMeatLovers, gbc);

        gbc.gridy = 4;
        add(btnBlt, gbc);

        gbc.gridy = 5;
        add(btnHaysticksWithCheese, gbc);
    }
}
