package co.edu.uptc.view.cashier.subPanelsCashier.categoryPanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.view.cashier.subPanelsCashier.SubPanelCenter;
import co.edu.uptc.view.cashier.subPanelsCashier.SubPanelRight;
import co.edu.uptc.view.components.RoundedButtonCardLayout;
import co.edu.uptc.view.components.RoundedButtonProduct;
import co.edu.uptc.view.styleConstans.UIStyle;

public class BeveragesPanel extends JPanel{

    private RoundedButtonCardLayout btnBack;
    private RoundedButtonProduct btnColombiana, btnManzanaPostobon, btnCocaCola, btn7up,
            btnHitLulo, btnHitMango, btnHinduLimon, btnHinduDurazno,
            btnMrTeaLimon, btnClubColombia, btnHeineken, btnStellaArtois;


    public BeveragesPanel(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        initComponents(subPanelCenter, subPanelRight);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter, SubPanelRight subPanelRight) {

        btnBack = new RoundedButtonCardLayout("VOLVER", UIStyle.ACTION_CANCEL, "buttonCategoryPanel", subPanelCenter);

        btnColombiana = new RoundedButtonProduct("Bv-colombiana", UIStyle.P2,subPanelRight.getShoppingCart());
        btnManzanaPostobon = new RoundedButtonProduct("Bv-manzana-postobon", UIStyle.P3,subPanelRight.getShoppingCart());
        btnCocaCola = new RoundedButtonProduct("Bv-coca-cola", UIStyle.P4,subPanelRight.getShoppingCart());
        btn7up = new RoundedButtonProduct("Bv-7up", UIStyle.P5,subPanelRight.getShoppingCart());
        btnHitLulo = new RoundedButtonProduct("Bv-hit-lulo", UIStyle.P6,subPanelRight.getShoppingCart());
        btnHitMango = new RoundedButtonProduct("Bv-hit-mango", UIStyle.P7,subPanelRight.getShoppingCart());
        btnHinduLimon = new RoundedButtonProduct("Bv-hindu-te-limon", UIStyle.P8,subPanelRight.getShoppingCart());
        btnHinduDurazno = new RoundedButtonProduct("Bv-hindu-te-durazno", UIStyle.P9,subPanelRight.getShoppingCart());
        btnMrTeaLimon = new RoundedButtonProduct("Bv-limon-mr-tea", UIStyle.P4,subPanelRight.getShoppingCart());
        btnClubColombia = new RoundedButtonProduct("Bv-dorada-colombia-club", UIStyle.P3,subPanelRight.getShoppingCart());
        btnHeineken = new RoundedButtonProduct("Bv-heineken", UIStyle.P2,subPanelRight.getShoppingCart());
        btnStellaArtois = new RoundedButtonProduct("Bv-stella-artois", UIStyle.P9,subPanelRight.getShoppingCart());

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
        add(btnColombiana, gbc);

        gbc.gridy = 2;
        add(btnManzanaPostobon, gbc);

        gbc.gridy = 3;
        add(btnCocaCola, gbc);

        gbc.gridy = 4;
        add(btn7up, gbc);

        gbc.gridy = 5;
        add(btnHitLulo, gbc);

        gbc.gridy = 6;
        add(btnHitMango, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(btnHinduLimon, gbc);

        gbc.gridy = 2;
        add(btnHinduDurazno, gbc);

        gbc.gridy = 3;
        add(btnMrTeaLimon, gbc);

        gbc.gridy = 4;
        add(btnClubColombia, gbc);

        gbc.gridy = 5;
        add(btnHeineken, gbc);

        gbc.gridy = 6;
        add(btnStellaArtois, gbc);
    }
}