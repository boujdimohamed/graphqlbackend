package com.example.GraphQL.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  // Eindeutige ID der Aufgabe

    private String name;              // Name der Aufgabe
    private String description;       // Beschreibung der Aufgabe
    private String startDate;         // Startdatum der Aufgabe
    private String endDate;           // Enddatum der Aufgabe
    private String startTime;         // Startzeit der Aufgabe
    private String endTime;           // Endzeit der Aufgabe
    private String priority;          // Priorität: hoch, mittel, niedrig
    private Integer progress;         // Fortschritt: 0-100 in 10er-Schritten
    private String status;            // Status: offen, in Arbeit, erledigt
    private boolean taskCompleted;    // Boolean-Status für "erledigt"

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")     // Fremdschlüssel für die Tags in der Task-Tabelle
    private List<Tag> tags;           // Liste von Tags für die Aufgabe

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")     // Fremdschlüssel für die Assignees in der Task-Tabelle
    private List<Assignee> assignees; // Liste von Assignees für die Aufgabe

    // Standardkonstruktor
    public Task() {
        this.tags = new ArrayList<>(); // Initialisierung der Tags
        this.progress = 0;             // Standardfortschritt: 0%
        this.status = "offen";         // Standardstatus: offen
        this.taskCompleted = false;    // Standard: nicht erledigt
        this.assignees = new ArrayList<>(); // Initialisierung der Assignees
    }

    // Getter und Setter

}