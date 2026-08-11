package com.mobile.lab.fragment;

import com.mobile.lab.db.DatabaseHelper;
import com.mobile.lab.model.Task;
import java.util.List;

/**
 * Fragment displaying the Task List inside MainActivity's FragmentContainer.
 */
public class TaskListFragment {
    public List<Task> loadTaskList() {
        return DatabaseHelper.getAllTasks();
    }

    public boolean toggleTaskStatus(long taskId) {
        Task t = DatabaseHelper.getTaskById(taskId);
        if (t != null) {
            t.setCompleted(!t.isCompleted());
            DatabaseHelper.updateTask(t);
            return true;
        }
        return false;
    }
}
