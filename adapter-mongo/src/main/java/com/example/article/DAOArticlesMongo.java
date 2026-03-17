package com.example.article;

import com.example.domain.IDAOArticle;
import com.example.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DAOArticlesMongo implements IDAOArticle {
    @Autowired
    ArticleMongoRepository articleMongoRepository;

//region Avoir tous les articles
    @Override
    public List<Article> getAll() {
        List<ArticleMongo> articlesMongo = articleMongoRepository.findAll();
        return articlesMongo.stream()
                .map(articleMongo -> {
                    Article article = new Article();
                    article.id = articleMongo.id;
                    article.title = articleMongo.title;
                    article.description = articleMongo.description;
                    return article;
                })
                .collect(Collectors.toList());
    }
//endregion

//region Trouver un article via son ID
    @Override
    public Optional<Article> findById(String id) {
        return articleMongoRepository.findById(id)
            .map(articleMongo -> {
                Article article = new Article();
                article.id = articleMongo.id;
                article.title = articleMongo.title;
                article.description = articleMongo.description;
                return article;
            });
    }
//endregion

//region Pour sauvegarder, modifier ou créer un article
    @Override
    public boolean existsByTitle(String title) {
        return articleMongoRepository.existsByTitle(title);
}

    @Override
    public boolean existsByTitleAndIdNot(String title, String id) {
        return articleMongoRepository.existsByTitleAndIdNot(title, id);
    }

    @Override
    public Article save(Article article) {
        ArticleMongo articleMongo = new ArticleMongo();
        articleMongo.id = article.id;
        articleMongo.title = article.title;
        articleMongo.description = article.description;

        ArticleMongo saved = articleMongoRepository.save(articleMongo);

        Article result = new Article();
        result.id = saved.id;
        result.title = saved.title;
        result.description = saved.description;

        return result;
    }
//endregion

//region Pour supprimer un article
    @Override
    public boolean deleteById(String id) {
        if (articleMongoRepository.existsById(id)) {
            articleMongoRepository.deleteById(id);
            return true;
        }
        return false;
    }

//endregion
}
