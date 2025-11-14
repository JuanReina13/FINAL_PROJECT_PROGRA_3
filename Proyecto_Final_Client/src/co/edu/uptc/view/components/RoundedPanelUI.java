package co.edu.uptc.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RoundedPanelUI extends JPanel {

    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;


    public RoundedPanelUI(Color backgroundColor, int arcSize) {
        this.backgroundColor = backgroundColor;
        this.arcWidth = arcSize;
        this.arcHeight = arcSize;
        setOpaque(false);
        setBorder(new EmptyBorder(3,3,3,3));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.dispose();
    }
}