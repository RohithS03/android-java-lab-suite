package com.mobile.lab;

import com.mobile.lab.db.DatabaseHelper;
import com.mobile.lab.db.PreferencesManager;
import com.mobile.lab.fragment.AnalyticsFragment;
import com.mobile.lab.model.Task;
import com.mobile.lab.receiver.NetworkStatusReceiver;
import com.mobile.lab.service.NotificationService;
import com.mobile.lab.ui.TaskDetailActivity;
import com.mobile.lab.ui.TaskFormActivity;

import java.util.List;

public class AndroidLabTestRunner {
    private static int passCount = 0;
    private static int failCount = 0;

    private static void TEST_ASSERT(boolean condition, String testName) {
        if (condition) {
            System.out.println(" [PASS] " + testName);
            passCount++;
        } else {
            System.out.println(" [FAIL] " + testName);
            failCount++;
        }
    }

    public static void testTaskModelInstantiation() {
        Task t = new Task("Study Android Lifecycle", "Review onCreate, onStart, onResume", "Education", "2026-08-10", "HIGH");
        TEST_ASSERT(t.getTitle().equals("Study Android Lifecycle"), "Task model title getter");
        TEST_ASSERT(t.getPriority().equals("HIGH"), "Task model priority getter");
        TEST_ASSERT(!t.isCompleted(), "Task model initial completion state == false");
    }

    public static void testDatabaseCRUDOperations() {
        DatabaseHelper.resetMockDatabase();
        
        Task t1 = new Task("Implement SQLite Helper", "Write SQLiteOpenHelper CRUD methods", "Code", "2026-08-12", "HIGH");
        long id1 = DatabaseHelper.insertTask(t1);
        TEST_ASSERT(id1 > 0, "DatabaseHelper insertTask returns generated primary key > 0");

        List<Task> all = DatabaseHelper.getAllTasks();
        TEST_ASSERT(all.size() == 1, "DatabaseHelper getAllTasks size == 1");

        Task fetched = DatabaseHelper.getTaskById(id1);
        TEST_ASSERT(fetched != null && fetched.getTitle().equals("Implement SQLite Helper"), "DatabaseHelper getTaskById");

        fetched.setCompleted(true);
        boolean updated = DatabaseHelper.updateTask(fetched);
        TEST_ASSERT(updated && DatabaseHelper.getCompletedCount() == 1, "DatabaseHelper updateTask sets isCompleted == true");

        boolean deleted = DatabaseHelper.deleteTask(id1);
        TEST_ASSERT(deleted && DatabaseHelper.getAllTasks().isEmpty(), "DatabaseHelper deleteTask removes row");
    }

    public static void testTaskFormValidation() {
        TaskFormActivity form = new TaskFormActivity();
        boolean caught = false;
        try {
            form.saveTask("", "Empty title test", "General", "2026-08-10", "LOW");
        } catch (IllegalArgumentException e) {
            caught = true;
        }
        TEST_ASSERT(caught, "TaskFormActivity rejects empty task title with IllegalArgumentException");
    }

    public static void testPreferencesManager() {
        PreferencesManager.setUserName("Android Student");
        TEST_ASSERT(PreferencesManager.getUserName().equals("Android Student"), "SharedPreferences set & get user name");

        PreferencesManager.setDarkModeEnabled(true);
        TEST_ASSERT(PreferencesManager.isDarkModeEnabled(), "SharedPreferences dark mode preference");
    }

    public static void testAnalyticsFragmentComputation() {
        DatabaseHelper.resetMockDatabase();
        DatabaseHelper.insertTask(new Task("T1", "D1", "C1", "2026-08-10", "HIGH"));
        Task t2 = new Task("T2", "D2", "C1", "2026-08-10", "MEDIUM");
        t2.setCompleted(true);
        DatabaseHelper.insertTask(t2);

        AnalyticsFragment analytics = new AnalyticsFragment();
        AnalyticsFragment.TaskStats stats = analytics.computeStats();

        TEST_ASSERT(stats.totalTasks == 2, "AnalyticsFragment totalTasks == 2");
        TEST_ASSERT(stats.completedTasks == 1, "AnalyticsFragment completedTasks == 1");
        TEST_ASSERT(stats.completionPercentage == 50.0, "AnalyticsFragment completionPercentage == 50.0%");
    }

    public static void testNotificationServiceAndBroadcastReceiver() {
        boolean notifSent = NotificationService.triggerTaskReminderNotification("Lab Submission", "2026-08-15");
        TEST_ASSERT(notifSent, "NotificationService pushes task reminder notification");

        NetworkStatusReceiver.setNetworkConnected(true);
        TEST_ASSERT(NetworkStatusReceiver.isNetworkConnected(), "BroadcastReceiver network connectivity state");
    }

    public static void testIntentExtraAndSharePayload() {
        Task t = new Task("Intent Test", "Explicit Intent Payload", "Android", "2026-08-10", "HIGH");
        TaskDetailActivity detail = new TaskDetailActivity();
        String shareText = detail.generateImplicitShareText(t);

        TEST_ASSERT(shareText.contains("Intent Test"), "TaskDetailActivity implicit share intent text generation");
    }

    public static void main(String[] args) {
        System.out.println("\n========================================================");
        System.out.println("   ANDROID MOBILE APP DEVELOPMENT UNIT TEST SUITE       ");
        System.out.println("========================================================");

        testTaskModelInstantiation();
        testDatabaseCRUDOperations();
        testTaskFormValidation();
        testPreferencesManager();
        testAnalyticsFragmentComputation();
        testNotificationServiceAndBroadcastReceiver();
        testIntentExtraAndSharePayload();

        System.out.println("========================================================");
        System.out.println(" TEST RESULTS SUMMARY: " + passCount + " PASSED, " + failCount + " FAILED");
        System.out.println("========================================================\n");

        if (failCount > 0) System.exit(1);
    }
}
