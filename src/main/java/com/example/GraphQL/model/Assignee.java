package com.example.GraphQL.model;

import jakarta.persistence.*;

@Entity
@Table(name = "assignee")
public class Assignee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
}

