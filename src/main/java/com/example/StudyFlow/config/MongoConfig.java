package com.example.StudyFlow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.example.StudyFlow.repository.mongo")
public class MongoConfig {
}
