package com.example.article;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
// Utile pour la connexion en MongoDB, pour SQL il n'y pas besoin d'un équivalent
@Configuration
@EnableMongoRepositories(basePackages = "com.example.article")
public class MongoConfig {
}