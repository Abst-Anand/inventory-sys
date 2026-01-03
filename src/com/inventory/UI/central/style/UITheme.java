package com.inventory.UI.central.style;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.prefs.Preferences;


public class UITheme {

    private static final Preferences prefs = Preferences.userRoot().node("inventory-ui");

    public static void applySavedTheme() {
        boolean dark = prefs.getBoolean("dark", false);
        applyTheme(dark);
    }

    public static boolean isDark() {
        return prefs.getBoolean("dark", false);
    }

    public static void applyTheme(boolean dark) {
        try {
            if (dark) {
                UIManager.setLookAndFeel(new FlatMaterialDarkerIJTheme());
            } else {
                UIManager.setLookAndFeel(new FlatLightLaf());
            }
            prefs.putBoolean("dark", dark);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void setGlobalFont(int size) {
        Font font = new Font("Segoe UI", Font.PLAIN, size);

        UIDefaults defaults = UIManager.getLookAndFeelDefaults();

        // COPY keys to avoid ConcurrentModificationException
        Enumeration<Object> keys = defaults.keys();
        List<Object> keyList = Collections.list(keys);

        for (Object key : keyList) {
            Object value = defaults.get(key);
            if (value instanceof Font) {
                UIManager.put(key, font);
            }
        }
    }

}
