package com.example.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSQLRepository extends JpaRepository<ArticleSQL, Long> {
    // Utile pour la création ou modifcation, selon si son titre existe ou pas
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, Long id);
}

