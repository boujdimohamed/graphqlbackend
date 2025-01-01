package com.example.GraphQL.config;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLScalarConfig {

    @Bean
    public GraphQLScalarType jsonScalar() {
        return GraphQLScalarType.newScalar()
                .name("JSON")
                .description("Custom scalar type for JSON")
                .coercing(new Coercing<Object, Object>() {
                    @Override
                    public Object serialize(Object dataFetcherResult) {
                        return dataFetcherResult; // Serialize output
                    }

                    @Override
                    public Object parseValue(Object input) {
                        return input; // Parse input value
                    }

                    @Override
                    public Object parseLiteral(Object input) {
                        return input; // Parse literal
                    }
                })
                .build();
    }
}

