package com.example.GraphQL.model;

/**
 * Modellklasse für die Statistik von Aufgaben.
 */
public class TaskStatistics {

    private long open;
    private long inProgress;
    private long completed;

    
    public TaskStatistics(long open, long inProgress, long completed) {
        this.open = open;
        this.inProgress = inProgress;
        this.completed = completed;
    }

    // Getter und Setter
    public long getOpen() {
        return open;
    }

    public void setOpen(long open) {
        this.open = open;
    }

    public long getInProgress() {
        return inProgress;
    }

    public void setInProgress(long inProgress) {
        this.inProgress = inProgress;
    }

    public long getCompleted() {
        return completed;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    // toString-Methode für Debugging und Ausgabe
    @Override
    public String toString() {
        return "TaskStatistics{" +
                "open=" + open +
                ", inProgress=" + inProgress +
                ", completed=" + completed +
                '}';
    }
}
