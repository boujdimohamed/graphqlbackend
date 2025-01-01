package com.example.GraphQL.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/test-db")
    public String testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "✅ Erfolgreich mit H2-Datenbank verbunden!";
        } catch (SQLException e) {
            return "❌ Fehler bei der Verbindung zur Datenbank: " + e.getMessage();
        }
    }
}
