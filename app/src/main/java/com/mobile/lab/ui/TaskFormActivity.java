package com.mobile.lab.ui;

import com.mobile.lab.db.DatabaseHelper;
import com.mobile.lab.model.Task;
import com.mobile.lab.service.NotificationService;

/**
 * TaskFormActivity handling task creation/edition, input validation, and date picking.
 */
public class TaskFormActivity {
    public long saveTask(String title, String description, String category, String dueDate, String priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title is required.");
        }

        Task task = new Task(title, description, category, dueDate, priority);
        long id = DatabaseHelper.insertTask(task);

        NotificationService.triggerTaskReminderNotification(title, dueDate);
        return id;
    }
}
