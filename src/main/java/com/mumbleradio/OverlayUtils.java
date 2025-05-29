package com.mumbleradio;

import com.sun.jna.platform.unix.X11;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class OverlayUtils {

    public static void makeClickThrough(final Window window) {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            makeClickThroughWindows(window);
        } else if (os.contains("linux")) {
            makeClickThroughLinux(window);
        } else {
            System.out.println("❗ Overlay Click-Through nicht unterstützt für OS: " + os);
        }
    }

    private static void makeClickThroughWindows(final Window window) {
        // Platzhalter: JNA-Code folgt
        System.out.println("Windows: TODO - set WS_EX_TRANSPARENT per JNA");
    }

    private static void makeClickThroughLinux(final Window window) {
        System.out.println("Linux: TODO - set InputShape per Xlib");

        X11.Display display = X11.INSTANCE.XOpenDisplay(null);
        final long windowId = getX11WindowID(window);

        // Create an empty input shape = click-through
        final X11Lib.XRectangle rect = new X11Lib.XRectangle();
        rect.x = 0;
        rect.y = 0;
        rect.width = 0;
        rect.height = 0;

        X11Lib.INSTANCE.XShapeCombineRectangles(
                display, new X11.Window(windowId),
                X11Lib.ShapeInput, 0, 0,
                new X11Lib.XRectangle[]{rect}, 1,
                X11Lib.ShapeSet, 0
        );

        X11.INSTANCE.XFlush(display);
    }

    public static long getX11WindowID(final Window window) {
        try {
            window.setVisible(true);
            final Field peerField = Component.class.getDeclaredField("peer");
            peerField.setAccessible(true);

            final Object peer = peerField.get(window);
            final Class<?> x11WindowPeerClass = Class.forName("sun.awt.X11.XWindowPeer");
            final Method getWindowMethod = x11WindowPeerClass.getMethod("getWindow");

            return (long) getWindowMethod.invoke(peer);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
