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

public class MexicanPanel extends JPanel{

    private RoundedButtonCardLayout btnBack;
    private RoundedButtonProduct btnNachosSupreme;
    private RoundedButtonProduct btnNachosWithCheese;
    private RoundedButtonProduct btnHotPretzel;
    private RoundedButtonProduct btnCheeseQuesadilla;
    private RoundedButtonProduct btnBltQuesadilla;
    private RoundedButtonProduct btnBuildYourOwn;

    public MexicanPanel(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents(subPanelCenter, subPanelRight);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        btnBack = new RoundedButtonCardLayout("VOLVER", UIStyle.ACTION_CANCEL, "buttonCategoryPanel", subPanelCenter);
        btnNachosSupreme = new RoundedButtonProduct("mx-nachos-supreme", UIStyle.P1,subPanelRight.getShoppingCart());
        btnNachosWithCheese = new RoundedButtonProduct("mx-nachos-cheese", UIStyle.P3,subPanelRight.getShoppingCart());
        btnHotPretzel = new RoundedButtonProduct("mx-hot-pretzel", UIStyle.P7,subPanelRight.getShoppingCart());
        btnCheeseQuesadilla = new RoundedButtonProduct("mx-quesadilla-cheese", UIStyle.P4,subPanelRight.getShoppingCart());
        btnBltQuesadilla = new RoundedButtonProduct("mx-quesadilla-blt", UIStyle.P2,subPanelRight.getShoppingCart());
        btnBuildYourOwn = new RoundedButtonProduct("mx-quesadilla-build", UIStyle.P6,subPanelRight.getShoppingCart());

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
        gbc.gridy = 1; add(btnNachosWithCheese, gbc);
        gbc.gridy = 2; add(btnNachosSupreme, gbc);
        gbc.gridy = 3; add(btnHotPretzel, gbc);
        gbc.gridy = 4; add(btnCheeseQuesadilla, gbc);
        gbc.gridy = 5; add(btnBltQuesadilla, gbc);
        gbc.gridy = 6; add(btnBuildYourOwn, gbc);
    }
}
