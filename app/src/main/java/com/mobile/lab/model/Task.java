package com.mobile.lab.model;

import java.io.Serializable;

public class Task implements Serializable {
    private long id;
    private String title;
    private String description;
    private String category;
    private String dueDate;
    private String priority;
    private boolean isCompleted;

    public Task(long id, String title, String description, String category, String dueDate, String priority, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public String getDueDate() { return dueDate; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return isCompleted; }
}
