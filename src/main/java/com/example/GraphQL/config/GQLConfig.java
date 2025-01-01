package com.example.GraphQL.config;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GQLConfig {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType jsonScalar) {
        return wiringBuilder -> wiringBuilder.scalar(jsonScalar);
    }
}
