package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import co.edu.uptc.view.components.RoundedButtonCardLayout;
import co.edu.uptc.view.styleConstans.UIStyle;

public class ButtonCategoryPanel extends JPanel{
    private RoundedButtonCardLayout btnPizzas;
    private RoundedButtonCardLayout btnBurguers;
    private RoundedButtonCardLayout btnHotDogs;
    private RoundedButtonCardLayout btnSandwiches;
    private RoundedButtonCardLayout btnSalads;
    private RoundedButtonCardLayout btnMexican;
    private RoundedButtonCardLayout btnBeverages;


    public ButtonCategoryPanel(SubPanelCenter subPanelCenter) {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 5));
        initComponents(subPanelCenter);
        setVisible(true);
    }

    private void initComponents(SubPanelCenter subPanelCenter) {
        btnPizzas = new RoundedButtonCardLayout("PIZZAS", UIStyle.CATEGORY_PIZZA, "pizzasPanel",subPanelCenter);
        btnBurguers = new RoundedButtonCardLayout("HAMBURGUESAS", UIStyle.CATEGORY_BURGUERS,"burguersPanel",subPanelCenter);
        btnHotDogs = new RoundedButtonCardLayout("HOT DOGS", UIStyle.CATEGORY_HOT_DOGS,"hotDogsPanel",subPanelCenter);
        btnSandwiches = new RoundedButtonCardLayout("SANDWICHES", UIStyle.CATEGORY_SANDWICHES,"sandwichesPanel",subPanelCenter);
        btnSalads = new RoundedButtonCardLayout("ENSALADAS", UIStyle.CATEGORY_SALADS,"saladsPanel",subPanelCenter);
        btnMexican = new RoundedButtonCardLayout("COMIDA MEXICANA", UIStyle.CATEGORY_MEXICAN,"mexicanPanel",subPanelCenter);
        btnBeverages = new RoundedButtonCardLayout("BEBIDAS", UIStyle.CATEGORY_BEVERAGES,"beveragesPanel",subPanelCenter);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weighty = 2.0;
        add(btnPizzas, gbc);

        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;
        add(btnBurguers, gbc);

        gbc.gridy = 3;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;
        add(btnHotDogs, gbc);

        gbc.gridx = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;

        gbc.gridy = 0;
        add(btnSalads, gbc);

        gbc.gridy = 1;
        add(btnSandwiches, gbc);

        gbc.gridy = 2;
        add(btnMexican, gbc);

        gbc.gridy = 3;
        add(btnBeverages, gbc);
    }
}
