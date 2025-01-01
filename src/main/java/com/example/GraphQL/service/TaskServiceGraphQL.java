package com.example.GraphQL.service;

import com.example.GraphQL.model.Task;
import com.example.GraphQL.model.TaskFilter;
import com.example.GraphQL.model.TaskStatistics;
import com.example.GraphQL.repository.TaskRepository;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceGraphQL {

    private final TaskRepository taskRepository;

    public TaskServiceGraphQL(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskStatistics getTaskStatistics() {
        List<Task> tasks = taskRepository.findAll(); // Holt alle Aufgaben aus der DB

        long open = tasks.stream()
                .filter(task -> "offen".equalsIgnoreCase(task.getStatus()))
                .count();

        long inProgress = tasks.stream()
                .filter(task -> "in Arbeit".equalsIgnoreCase(task.getStatus()))
                .count();

        long completed = tasks.stream()
                .filter(task -> "erledigt".equalsIgnoreCase(task.getStatus()))
                .count();

        return new TaskStatistics(open, inProgress, completed);
    }

    public void updateTaskField(Long id, Map<String, Object> updates) {
        Task task = taskRepository.findTaskById(id)
        ;

        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    task.setName((String) value);
                    break;
                case "startDate":
                    task.setStartDate((String) value);
                    break;
                case "endDate":
                    task.setEndDate((String) value);
                    break;
                case "priority":
                    task.setPriority((String) value);
                    break;
                case "progress":
                    task.setProgress((Integer) value);
                    break;
                case "status":
                    task.setStatus((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Das Feld \"" + key + "\" kann nicht aktualisiert werden.");
            }
        });

        taskRepository.save(task);
    }

    public List<Task> filterTasks(TaskFilter filter) {
        // Aufruf der Repository-Methode, um Aufgaben mit dem Filter zu finden
        return taskRepository.findTasksByFilter(filter);
    }
    

    public Task getTaskById(Long id) {
        return taskRepository.findTaskById(id)
        ;
    }

    public void updateProgress(Long id, Integer progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("Fortschritt muss zwischen 0 und 100 liegen.");
        }

        Task task = taskRepository.findTaskById(id)
        ;

        task.setProgress(progress);

        if (progress == 100) {
            task.setStatus("erledigt");
        } else if (progress > 0) {
            task.setStatus("in Arbeit");
        } else {
            task.setStatus("offen");
        }

        taskRepository.save(task);
    }
}
