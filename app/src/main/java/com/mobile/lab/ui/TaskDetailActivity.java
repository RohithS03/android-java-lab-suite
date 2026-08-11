package com.mobile.lab.ui;

import com.mobile.lab.db.DatabaseHelper;
import com.mobile.lab.model.Task;

/**
 * TaskDetailActivity receiving Explicit Intent Extras and displaying task details & implicit sharing.
 */
public class TaskDetailActivity {
    public Task loadTaskFromIntentExtra(long taskId) {
        return DatabaseHelper.getTaskById(taskId);
    }

    public String generateImplicitShareText(Task task) {
        if (task == null) return "";
        return "Task Details: " + task.getTitle() + "\nCategory: " + task.getCategory() + "\nDue: " + task.getDueDate() + "\nStatus: " + (task.isCompleted() ? "Completed" : "Pending");
    }
}
