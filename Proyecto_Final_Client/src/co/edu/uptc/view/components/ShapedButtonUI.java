package co.edu.uptc.view.components;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class ShapedButtonUI extends BasicButtonUI {

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c.getBackground());
        g2d.fillRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
        g2d.drawRoundRect(0, 0, c.getWidth() - 1, c.getHeight() - 1, 10, 10);
        super.paint(g2d, c);

        g2d.dispose();
    }
}
