package co.edu.uptc.view.styleConstans;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class UIStyle {

    public static final Color PRIMARY_COLOR = new Color(25, 118, 210);
    public static final Color SECONDARY_COLOR = new Color(13, 71, 161);
    public static final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    public static final Color TEXT_COLOR = Color.DARK_GRAY;

    private static final List<Color> HEADER_COLORS = List.of(
            new Color(139, 0, 0),   // Rojo
            new Color(0, 123, 255),   // Azul
            new Color(46, 125, 50),   // Verde
            new Color(111, 66, 193),  // Morado
            new Color(23, 162, 184)   // Cian
    );

    public static final Color CATEGORY_PIZZA = new Color(251, 140, 0); // #FB8C00
    public static final Color CATEGORY_BURGUERS = new Color(9, 68, 121); // #094479ff
    public static final Color CATEGORY_HOT_DOGS = new Color(204, 0, 0); // #cd1e1eff
    public static final Color CATEGORY_SANDWICHES = new Color(67, 160, 71); // #43A047
    public static final Color CATEGORY_MEXICAN = new Color(28, 127, 173); // #1c7fadff
    public static final Color CATEGORY_SALADS = new Color(0, 105, 16); // #006910ff
    public static final Color CATEGORY_BEVERAGES = new Color(142, 36, 170); // #8E24AA

    public static final Color P1  = new Color(255, 138, 20);  // #FF8A14
    public static final Color P2  = new Color(214,  69, 12);  // #D6450C
    public static final Color P3  = new Color(255, 183, 77);  // #FFB74D
    public static final Color P4  = new Color(255, 220,160);  // #FFDC A0
    public static final Color P5  = new Color(255, 102,102);  // #FF6666
    public static final Color P6  = new Color(185, 102,70);   // #B96646
    public static final Color P7  = new Color( 86,160,168);   // #56A0A8
    public static final Color P8  = new Color( 61,111,151);   // #3D6F97
    public static final Color P9  = new Color(230,150,80);    // #E69650

    public static final Color TEXT_DARK = new Color(33,33,33); // #212121
    public static final Color BACKGROUND = new Color(245, 245, 245); // #F5F5F5
    public static final Color PANEL_BG = new Color(255, 255, 255); // #FFFFFF
    public static final Color SPACING = new Color(224, 224, 224); // #E0E0E0
    public static final Color TEXT_PRIMARY = new Color(33, 33, 33); // #212121
    public static final Color TEXT_SECONDARY = new Color(97, 97, 97); // #616161
    public static final Color BORDER_COLOR = new Color(189, 189, 189); // #BDBDBD
    public static final Color BORDER_COLOR2 = new Color(205, 205,205); // #cdcdcdff
    public static final Color BACKGROUND_INFERIOR_COLOR = new Color(239, 222, 205); // #EFDECD
    public static final Color TOPBAR_BG = new Color(55, 71, 79); // #37474F
    public static final Color TOPBAR_TEXT = new Color(255, 255, 255); // #FFFFFF
    public static final Color ACTION_PAY = new Color(56, 142, 60); // #388E3C
    public static final Color ACTION_CANCEL = new Color(211, 47, 47); // #D32F2F
    public static final Color ACTION_MENU = new Color(69, 90, 100); // #455A64

    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font SUBTITLE_FONT = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font SUBTITLE_FONT2 = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font TEXT_FONT = new Font("Segoe UI", Font.PLAIN, 16);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);
    public static final Font BRAND_FONT = new Font("Rockwell Extra Bold", Font.PLAIN, 20);

    public static final Border BUTTON_BORDER = BorderFactory.createLineBorder(PRIMARY_COLOR, 2, true);
    public static final Border PANEL_BORDER = BorderFactory.createLineBorder(SECONDARY_COLOR, 1, true);

    private static final Random random = new Random();

    public static Color getRandomHeaderColor() {
        return HEADER_COLORS.get(random.nextInt(HEADER_COLORS.size()));
    }

    public static void styleButton(JButton button) {
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFont(BUTTON_FONT);
        button.setBorder(BUTTON_BORDER);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(PANEL_BORDER);
    }

    public static void styleLabel(JLabel label, boolean isTitle) {
        label.setFont(isTitle ? TITLE_FONT : TEXT_FONT);
        label.setForeground(TEXT_COLOR);
    }
}
