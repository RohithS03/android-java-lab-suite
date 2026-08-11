package com.mobile.lab.model;

import java.io.Serializable;

/**
 * Task Model representing a user task in the Android Smart Task Manager.
 * Implements Serializable for Intent Bundle passing.
 */
public class Task implements Serializable {
    private long id;
    private String title;
    private String description;
    private String category;
    private String dueDate;
    private String priority; // HIGH, MEDIUM, LOW
    private boolean isCompleted;

    public Task() {
        this.id = -1;
        this.title = "";
        this.description = "";
        this.category = "General";
        this.dueDate = "";
        this.priority = "MEDIUM";
        this.isCompleted = false;
    }

    public Task(long id, String title, String description, String category, String dueDate, String priority, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = isCompleted;
    }

    public Task(String title, String description, String category, String dueDate, String priority) {
        this(-1, title, description, category, dueDate, priority, false);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
