package co.edu.uptc.view.cashier.subPanelsCashier;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.ActionButtonsPanel;
import co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels.ShoppingCart;
import co.edu.uptc.view.components.ScrollBarUI;
import co.edu.uptc.view.styleConstans.UIStyle;

public class SubPanelRight extends JPanel{

    private ShoppingCart shoppingCart;
    private ActionButtonsPanel actionButtonsPanel;

    JScrollPane scrollPane;


    public SubPanelRight() {
        setBackground(UIStyle.BACKGROUND);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(370,800));
        setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 2));
        setVisible(true);
        initComponents();
    }

    private void initComponents() {
        addUpPanel();
        addDownPanel();
    }

    private void addUpPanel(){
        shoppingCart = new ShoppingCart();
        scrollPane = new JScrollPane(shoppingCart);
        scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addDownPanel(){
        actionButtonsPanel = new ActionButtonsPanel();
        shoppingCart.setActionButtonsPanel(actionButtonsPanel);
        add(actionButtonsPanel, BorderLayout.SOUTH);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public ActionButtonsPanel getActionButtonsPanel() {
        return actionButtonsPanel;
    }



}

