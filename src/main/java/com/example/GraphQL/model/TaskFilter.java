package com.example.GraphQL.model;

public class TaskFilter {
    private String priority;
    private String status;
    private String startDateFrom;
    private String startDateTo;
    private String endDateFrom;
    private String endDateTo;
    private String tag;

    // Felder für Sortierung
    private String sortField;   // Das Feld, nach dem sortiert werden soll (z.B. "startDate", "priority")
    private String sortDirection; // Die Richtung der Sortierung (z.B. "asc" oder "desc")

    // Getter und Setter
    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartDateFrom() {
        return startDateFrom;
    }

    public void setStartDateFrom(String startDateFrom) {
        this.startDateFrom = startDateFrom;
    }

    public String getStartDateTo() {
        return startDateTo;
    }

    public void setStartDateTo(String startDateTo) {
        this.startDateTo = startDateTo;
    }

    public String getEndDateFrom() {
        return endDateFrom;
    }

    public void setEndDateFrom(String endDateFrom) {
        this.endDateFrom = endDateFrom;
    }

    public String getEndDateTo() {
        return endDateTo;
    }

    public void setEndDateTo(String endDateTo) {
        this.endDateTo = endDateTo;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    // Weitere Getter und Setter nach Bedarf
}
