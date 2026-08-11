package com.mobile.lab.ui;

import com.mobile.lab.db.PreferencesManager;

/**
 * SplashActivity displaying splash onboarding screen and incrementing launch count.
 */
public class SplashActivity {
    public static final int SPLASH_DELAY_MS = 2000;

    public void onCreate() {
        PreferencesManager.incrementLaunchCount();
        System.out.println("[Android Lifecycle] SplashActivity.onCreate() executed. Launch count: " + PreferencesManager.getLaunchCount());
    }
}
