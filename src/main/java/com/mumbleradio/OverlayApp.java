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
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.setColor(new Color(255, 255, 255, 200)); // halbtransparent weiß
                    g.setFont(new Font("SansSerif", Font.BOLD, 36));
                    g.drawString("🛰️ Alpha spricht", 100, 100);
                }
            };

            panel.setOpaque(false);
            window.setContentPane(panel);
            window.setVisible(true);

            OverlayUtils.makeClickThrough(window);
        });
    }

}
