package co.edu.uptc.view.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;

import co.edu.uptc.view.styleConstans.UIStyle;

public class RoundedButton extends JButton{

    private Color backgroundColor;
    private String text;

    public RoundedButton(String text, Color backgroundColor) {
        super(text);
        this.text = text;
        this.backgroundColor = backgroundColor;

        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setForeground(UIStyle.TEXT_DARK);
        setFont(UIStyle.SUBTITLE_FONT2);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("Button clicked: " + getText());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int textX = (getWidth() - fm.stringWidth(text)) / 2;
        int textY = (getHeight() + fm.getAscent()) / 2 - 3;
        g2.setStroke(new BasicStroke(3f));
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight()- 1, 10, 10);
        g2.setColor(getForeground());
        g2.drawString(text, textX, textY);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, 80);
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    public String getButtonText() {
        return text;
    }

}
