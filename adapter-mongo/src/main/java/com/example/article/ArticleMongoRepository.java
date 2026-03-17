package com.example.article;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMongoRepository extends MongoRepository<ArticleMongo, String> {
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, String id);
}
