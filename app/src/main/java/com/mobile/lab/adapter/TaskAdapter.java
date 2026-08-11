package com.mobile.lab.adapter;

import com.mobile.lab.model.Task;
import java.util.List;

/**
 * RecyclerView Adapter demonstrating ViewHolder pattern and click listener callbacks.
 */
public class TaskAdapter {
    public interface OnTaskClickListener {
        void onTaskClick(Task task);
        void onTaskToggleComplete(Task task);
        void onTaskDelete(Task task);
    }

    private List<Task> taskList;
    private OnTaskClickListener listener;

    public TaskAdapter(List<Task> taskList, OnTaskClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    public void updateTasks(List<Task> newTasks) {
        this.taskList = newTasks;
    }

    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    public Task getItem(int position) {
        if (taskList != null && position >= 0 && position < taskList.size()) {
            return taskList.get(position);
        }
        return null;
    }
}
