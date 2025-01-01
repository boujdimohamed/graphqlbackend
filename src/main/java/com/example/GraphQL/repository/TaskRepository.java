package com.example.GraphQL.repository;

import com.example.GraphQL.model.Tag;
import com.example.GraphQL.model.Task;
import com.example.GraphQL.model.TaskFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository-Schnittstelle für Task-Entitäten.
 * Erbt grundlegende CRUD-Methoden von JpaRepository.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @PersistenceContext
    EntityManager entityManager = null;

    /**
     * Findet eine Aufgabe basierend auf der ID.
     */
    default Task findTaskById(Long id) {
        return entityManager.find(Task.class, id);
    }

    /**
     * Findet alle Aufgaben.
     */
    default List<Task> findAllTasks() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);
        criteriaQuery.select(taskRoot);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Findet Aufgaben basierend auf den Filterkriterien.
     */
    default List<Task> findTasksByFilter(TaskFilter filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = criteriaQuery.from(Task.class);

        List<Predicate> predicates = new ArrayList<>();

        // Filterbedingungen
        if (filter.getPriority() != null) {
            predicates.add(criteriaBuilder.equal(taskRoot.get("priority"), filter.getPriority()));
        }
        if (filter.getStatus() != null) {
            predicates.add(criteriaBuilder.equal(taskRoot.get("status"), filter.getStatus()));
        }
        if (filter.getStartDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(taskRoot.get("startDate"), filter.getStartDateFrom()));
        }
        if (filter.getStartDateTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(taskRoot.get("startDate"), filter.getStartDateTo()));
        }
        if (filter.getEndDateFrom() != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(taskRoot.get("endDate"), filter.getEndDateFrom()));
        }
        if (filter.getEndDateTo() != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(taskRoot.get("endDate"), filter.getEndDateTo()));
        }
        if (filter.getTag() != null) {
            Join<Task, Tag> tagJoin = taskRoot.join("tags", JoinType.INNER);
            predicates.add(criteriaBuilder.equal(tagJoin.get("name"), filter.getTag()));
        }

        // Filterbedingungen anwenden
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        // Sortierung hinzufügen, falls gewünscht
        if (filter.getSortField() != null && !filter.getSortField().isEmpty()) {
            if ("asc".equalsIgnoreCase(filter.getSortDirection())) {
                criteriaQuery.orderBy(criteriaBuilder.asc(taskRoot.get(filter.getSortField())));
            } else if ("desc".equalsIgnoreCase(filter.getSortDirection())) {
                criteriaQuery.orderBy(criteriaBuilder.desc(taskRoot.get(filter.getSortField())));
            }
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    /**
     * Speichert eine Aufgabe in der Datenbank.
     */
    default Task saveTask(Task task) {
        if (task.getId() == null) {
            entityManager.persist(task); // Neue Aufgabe speichern
            return task;
        } else {
            return entityManager.merge(task); // Existierende Aufgabe aktualisieren
        }
    }

    /**
     * Löscht eine Aufgabe aus der Datenbank.
     */
    default void deleteTask(Task task) {
        if (entityManager.contains(task)) {
            entityManager.remove(task);
        } else {
            entityManager.remove(entityManager.merge(task));
        }
    }
}
