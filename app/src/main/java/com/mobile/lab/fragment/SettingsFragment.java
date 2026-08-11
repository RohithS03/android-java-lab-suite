package com.mobile.lab.fragment;

import com.mobile.lab.db.PreferencesManager;

/**
 * Fragment managing SharedPreferences user settings and preferences.
 */
public class SettingsFragment {
    public String getUserName() {
        return PreferencesManager.getUserName();
    }

    public void updateUserName(String name) {
        PreferencesManager.setUserName(name);
    }

    public boolean isDarkMode() {
        return PreferencesManager.isDarkModeEnabled();
    }

    public void setDarkMode(boolean enabled) {
        PreferencesManager.setDarkModeEnabled(enabled);
    }
}
