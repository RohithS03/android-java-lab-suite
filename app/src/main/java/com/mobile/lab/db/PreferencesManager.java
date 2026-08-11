package com.mobile.lab.db;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages Android SharedPreferences for key-value application settings.
 */
public class PreferencesManager {
    public static final String PREF_NAME = "android_lab_prefs";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_DARK_MODE = "dark_mode_enabled";
    public static final String KEY_NOTIFICATIONS = "notifications_enabled";
    public static final String KEY_LAUNCH_COUNT = "app_launch_count";

    private static final Map<String, Object> preferencesStore = new HashMap<>();

    static {
        preferencesStore.put(KEY_USER_NAME, "Academic User");
        preferencesStore.put(KEY_DARK_MODE, true);
        preferencesStore.put(KEY_NOTIFICATIONS, true);
        preferencesStore.put(KEY_LAUNCH_COUNT, 1);
    }

    public static String getUserName() {
        return (String) preferencesStore.getOrDefault(KEY_USER_NAME, "Academic User");
    }

    public static void setUserName(String name) {
        preferencesStore.put(KEY_USER_NAME, name);
    }

    public static boolean isDarkModeEnabled() {
        return (boolean) preferencesStore.getOrDefault(KEY_DARK_MODE, true);
    }

    public static void setDarkModeEnabled(boolean enabled) {
        preferencesStore.put(KEY_DARK_MODE, enabled);
    }

    public static boolean isNotificationsEnabled() {
        return (boolean) preferencesStore.getOrDefault(KEY_NOTIFICATIONS, true);
    }

    public static void setNotificationsEnabled(boolean enabled) {
        preferencesStore.put(KEY_NOTIFICATIONS, enabled);
    }

    public static int getLaunchCount() {
        return (int) preferencesStore.getOrDefault(KEY_LAUNCH_COUNT, 1);
    }

    public static void incrementLaunchCount() {
        int count = getLaunchCount();
        preferencesStore.put(KEY_LAUNCH_COUNT, count + 1);
    }
}
