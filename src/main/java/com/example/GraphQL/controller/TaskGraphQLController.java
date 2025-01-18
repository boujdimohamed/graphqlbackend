package com.example.GraphQL.controller;

import com.example.GraphQL.model.Task;
import com.example.GraphQL.model.TaskFilter;
import com.example.GraphQL.model.TaskStatistics;
import com.example.GraphQL.service.TaskServiceGraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "api/v1/task")
public class TaskGraphQLController {

    private final TaskServiceGraphQL taskServiceGraphQL;

    @Autowired
    public TaskGraphQLController(TaskServiceGraphQL taskServiceGraphQL) {
        this.taskServiceGraphQL = taskServiceGraphQL;
    }

    /**
     * Mutation: Aktualisiert den Fortschritt einer Aufgabe.
     */
    @MutationMapping
    public String updateTaskProgress(@Argument Long id, @Argument Integer progress) {
        try {
            taskServiceGraphQL.updateProgress(id, progress);
            return "Fortschritt erfolgreich aktualisiert.";
        } catch (IllegalArgumentException e) {
            return "Fehler: " + e.getMessage();
        }
    }

    /**
     * Query: Liefert die Statistik über Aufgaben.
     */
    @QueryMapping
    public TaskStatistics getTaskStatistics() {
        return taskServiceGraphQL.getTaskStatistics();
    }

    /**
     * Mutation: Aktualisiert dynamisch Felder einer Aufgabe.
     */
    @MutationMapping
    public String updateTaskField(@Argument Long id, @Argument Map<String, Object> updates) {
        try {
            taskServiceGraphQL.updateTaskField(id, updates);
            return "Task erfolgreich aktualisiert.";
        } catch (IllegalArgumentException e) {
            return "Fehler: " + e.getMessage();
        }
    }

    /**
     * Query: Filtert Aufgaben basierend auf bestimmten Kriterien.
     */
    @QueryMapping
    public List<Task> filterTasks(@Argument TaskFilter filter) {
        return taskServiceGraphQL.filterTasks(filter);
    }

    /**
     * Query: Holt eine Aufgabe basierend auf ihrer ID.
     */
    @QueryMapping
    public Task getTaskById(@Argument Long id) {
        return taskServiceGraphQL.getTaskById(id);
    }
}
