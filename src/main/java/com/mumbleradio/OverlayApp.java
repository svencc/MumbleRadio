package com.mumbleradio;

import javax.swing.*;
import java.awt.*;

public class OverlayApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final JWindow window = new JWindow();

            window.setAlwaysOnTop(true);
            window.setBackground(new Color(0, 0, 0, 0)); // komplett transparent
            window.setBounds(0, 0, 1920, 1080); // Bildschirmgröße (anpassen!)

            final JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(final Graphics g) {
                    super.paintComponent(g);

                    final Graphics2D g2d = (Graphics2D) g;
                    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2d.setColor(new Color(255, 255, 255, 200)); // halbtransparent weiß
                    g2d.setFont(new Font("SansSerif", Font.BOLD, 36));
                    g2d.drawString("🛰️ Alpha spricht", 100, 100);
                }
            };

            panel.setOpaque(false);
            window.setContentPane(panel);
            window.setVisible(true);

            OverlayUtils.makeClickThrough(window);
        });
    }

}
