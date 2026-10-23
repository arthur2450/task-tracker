package com.arthur.tasktracker.model;

public class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("O título da tarefa não pode ser vazio.");
        }

        this.title = title;
        this.description = description;
        this.completed = false;
    }
    public String getTitle() {
        return title;

    }
    public String getDescription() {
        return description;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void markAsCompleted() {
        this.completed = true;
    }
}
