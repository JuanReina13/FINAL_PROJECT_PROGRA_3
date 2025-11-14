package co.edu.uptc.view.cashier.subPanelsCashier.OrderPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import co.edu.uptc.view.components.RoundedButton;
import co.edu.uptc.view.styleConstans.UIStyle;

public class ActionButtonsPanel extends JPanel{

    private JButton btnLeft;
    private JButton btnRight;
    private JLabel labelTotal;


    public ActionButtonsPanel() {
        setBackground(UIStyle.BACKGROUND_COLOR);
        setPreferredSize(new Dimension(WIDTH,200));
        setLayout(new GridBagLayout());
        initComponents();
    }

    private void initComponents(){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        labelTotal = new JLabel("Total: $0.00");
        labelTotal.setFont(UIStyle.TITLE_FONT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(labelTotal, gbc);

        btnLeft = new RoundedButton("BORRAR ORDEN", UIStyle.ACTION_CANCEL);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(btnLeft, gbc);

        btnRight = new RoundedButton("ENVIAR ORDEN", UIStyle.ACTION_PAY);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(btnRight, gbc);

    }

    public void setTotal(double total) {
        labelTotal.setText(String.format("Total: $ %.2f", total));
    }


}
