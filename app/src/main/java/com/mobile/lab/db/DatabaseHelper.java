package com.mobile.lab.db;

import com.mobile.lab.model.Task;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public static final String DATABASE_NAME = "tasks_lab.db";
    public static final int DATABASE_VERSION = 1;
    private static final List<Task> mockDatabase = new ArrayList<>();
    private static long autoIdCounter = 1;

    public static synchronized long insertTask(Task task) {
        task.setId(autoIdCounter++);
        mockDatabase.add(task);
        return task.getId();
    }

    public static synchronized List<Task> getAllTasks() {
        return new ArrayList<>(mockDatabase);
    }
}
