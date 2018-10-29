// package
package com.krish.taskapi.restfultaskapi.models;

// user model class
public class Task 
{
    // ...initialize values
    private int id;
    private String title;
    private String description;
    private Boolean completed;

    // ...constructors
    public Task() { }

    public Task(int id,String title, String description, Boolean completed) { this.id = id; this.title = title; this.description = description; this.completed = completed; }

    // ...setters / getters
    // ID
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Title
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    // Description
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Completed
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }

    // ...toString
    @Override
    public String toString() {
        return super.toString();
    }
}
