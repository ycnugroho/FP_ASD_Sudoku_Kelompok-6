package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

public class ThemeManager {
    public enum Theme {
        MEDIEVAL, JAPANESE, MODERN
    }

    // List to store theme change listeners
    private static List<ThemeChangeListener> listeners = new ArrayList<>();

    private static Theme currentTheme = Theme.MEDIEVAL;

    // Interface for theme change listeners
    public interface ThemeChangeListener {
        void onThemeChanged();
    }

    public static void addThemeChangeListener(ThemeChangeListener listener) {
        listeners.add(listener);
    }

    public static void removeThemeChangeListener(ThemeChangeListener listener) {
        listeners.remove(listener);
    }

    public static void setTheme(Theme theme) {
        currentTheme = theme;
        // Notify all listeners that theme has changed
        for (ThemeChangeListener listener : listeners) {
            listener.onThemeChanged();
        }
    }

    public static Theme getTheme() {
        return currentTheme;
    }

    // Color methods remain the same as in your original code
    public static Color getCellForeground() {
        switch (currentTheme) {
            case MEDIEVAL:
                return new Color(50, 50, 50); // Dark gray for medieval
            case JAPANESE:
                return new Color(0, 0, 139); // Dark blue for Japanese
            case MODERN:
                return Color.BLACK; // Black for modern
            default:
                return Color.BLACK;
        }
    }
    // Other color methods remain the same...

    // New method to get theme-specific fonts
    public static Font getThemeFont() {
        switch (currentTheme) {
            case MEDIEVAL:
                return new Font("Papyrus", Font.BOLD, 16);
            case JAPANESE:
                return new Font("Mincho", Font.PLAIN, 16);
            case MODERN:
                return new Font("Arial", Font.PLAIN, 16);
            default:
                return new Font("SansSerif", Font.PLAIN, 16);
        }
    }
    public static Color getCellBackground() {
        switch (currentTheme) {
            case MEDIEVAL:
                return new Color(101, 67, 33); // Darker brown
            case JAPANESE:
                return new Color(188, 143, 143); // Rosy brown
            case MODERN:
                return new Color(200, 200, 200); // Gray
            default:
                return Color.LIGHT_GRAY;
        }
    }
    

    public static Color getStatusBarBackground() {
        switch (currentTheme) {
            case MEDIEVAL:
                return new Color(101, 67, 33); // Darker brown
            case JAPANESE:
                return new Color(188, 143, 143); // Rosy brown
            case MODERN:
                return new Color(200, 200, 200); // Gray
            default:
                return Color.LIGHT_GRAY;
        }
    }
    
    public static Color getStatusBarForeground() {
        switch (currentTheme) {
            case MEDIEVAL:
                return Color.WHITE; // White text on dark brown
            case JAPANESE:
                return Color.BLACK; // Black text on rosy brown
            case MODERN:
                return Color.BLACK; // Black text on gray
            default:
                return Color.BLACK;
        }
    }
}