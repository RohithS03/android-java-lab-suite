package com.mobile.lab.ui;

import com.mobile.lab.fragment.AnalyticsFragment;
import com.mobile.lab.fragment.SettingsFragment;
import com.mobile.lab.fragment.TaskListFragment;

/**
 * MainActivity hosting BottomNavigationView and Fragment container.
 */
public class MainActivity {
    private final TaskListFragment taskListFragment = new TaskListFragment();
    private final AnalyticsFragment analyticsFragment = new AnalyticsFragment();
    private final SettingsFragment settingsFragment = new SettingsFragment();
    private String currentTab = "TASKS";

    public void onCreate() {
        System.out.println("[Android Lifecycle] MainActivity.onCreate() executed.");
    }

    public void switchTab(String tabName) {
        this.currentTab = tabName;
        System.out.println("[Android FragmentTransaction] Switched view to Fragment: " + tabName);
    }

    public String getCurrentTab() { return currentTab; }
    public TaskListFragment getTaskListFragment() { return taskListFragment; }
    public AnalyticsFragment getAnalyticsFragment() { return analyticsFragment; }
    public SettingsFragment getSettingsFragment() { return settingsFragment; }
}
