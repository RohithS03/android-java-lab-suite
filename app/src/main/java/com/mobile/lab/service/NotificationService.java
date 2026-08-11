package com.mobile.lab.service;

/**
 * Demonstrates Android Background Service and Notification Manager integration.
 */
public class NotificationService {
    public static final String CHANNEL_ID = "task_reminder_channel";
    public static final String CHANNEL_NAME = "Task Reminder Notifications";

    public static boolean triggerTaskReminderNotification(String taskTitle, String dueDate) {
        if (taskTitle == null || taskTitle.trim().isEmpty()) return false;
        System.out.println("[Android NotificationService] Pushed notification for task: '" + taskTitle + "' due on " + dueDate);
        return true;
    }
}
