package com.mobile.lab.fragment;

import com.mobile.lab.db.DatabaseHelper;

/**
 * Fragment displaying task completion analytics and statistical summary.
 */
public class AnalyticsFragment {
    public static class TaskStats {
        public int totalTasks;
        public int completedTasks;
        public int pendingTasks;
        public double completionPercentage;

        public TaskStats(int total, int completed, int pending) {
            this.totalTasks = total;
            this.completedTasks = completed;
            this.pendingTasks = pending;
            this.completionPercentage = (total > 0) ? ((double) completed / total) * 100.0 : 0.0;
        }
    }

    public TaskStats computeStats() {
        int total = DatabaseHelper.getAllTasks().size();
        int completed = DatabaseHelper.getCompletedCount();
        int pending = DatabaseHelper.getPendingCount();
        return new TaskStats(total, completed, pending);
    }
}
