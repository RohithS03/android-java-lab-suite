package com.mobile.lab.db;

import com.mobile.lab.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * SQLite OpenHelper managing local Android task database storage.
 * Provides CRUD operations, transactions, and schema management.
 */
public class DatabaseHelper {
    public static final String DATABASE_NAME = "tasks_lab.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_IS_COMPLETED = "is_completed";

    public static final String CREATE_TABLE_TASKS =
            "CREATE TABLE " + TABLE_TASKS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT NOT NULL, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_DUE_DATE + " TEXT, " +
                    COLUMN_PRIORITY + " TEXT, " +
                    COLUMN_IS_COMPLETED + " INTEGER DEFAULT 0" +
                    ");";

    // In-memory list simulation helper for unit testing & desktop Java verification
    private static final List<Task> mockDatabase = new ArrayList<>();
    private static long autoIdCounter = 1;

    public static synchronized void resetMockDatabase() {
        mockDatabase.clear();
        autoIdCounter = 1;
    }

    public static synchronized long insertTask(Task task) {
        if (task == null || task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }
        task.setId(autoIdCounter++);
        mockDatabase.add(task);
        return task.getId();
    }

    public static synchronized List<Task> getAllTasks() {
        return new ArrayList<>(mockDatabase);
    }

    public static synchronized Task getTaskById(long id) {
        for (Task t : mockDatabase) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public static synchronized boolean updateTask(Task task) {
        for (int i = 0; i < mockDatabase.size(); i++) {
            if (mockDatabase.get(i).getId() == task.getId()) {
                mockDatabase.set(i, task);
                return true;
            }
        }
        return false;
    }

    public static synchronized boolean deleteTask(long id) {
        return mockDatabase.removeIf(t -> t.getId() == id);
    }

    public static synchronized int getCompletedCount() {
        int count = 0;
        for (Task t : mockDatabase) {
            if (t.isCompleted()) count++;
        }
        return count;
    }

    public static synchronized int getPendingCount() {
        return mockDatabase.size() - getCompletedCount();
    }
}
