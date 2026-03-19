package com.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
public class ArticleService {

    @Autowired
    IDAOArticle daoArticle;
//region Pour avoir la liste de tout les articles
    public List<Article> getAllArticles(){

        return daoArticle.getAll();
    }
// endregion

//region Avoir un article selon son ID
    public Optional<Article> getArticleById(String id) {
        return daoArticle.findById(id);
    }
//endregion

//region Supprimer un article
    public boolean deleteArticle(String id) {
        return daoArticle.deleteById(id);
    }
//endregion

//region Modification et création d'un article
    public ArticleServiceResult saveArticle(Article article) {
        boolean isUpdate = article.id != null && !article.id.isEmpty();

        if (isUpdate) {
            // Vérifier si l'article existe
            Optional<Article> existingArticle = daoArticle.findById(article.id);
            if (existingArticle.isEmpty()) {
                // L'ID n'existe pas, traiter comme une création
                isUpdate = false;
            } else {
                // Vérifier si le titre est déjà utilisé par un autre article
                if (daoArticle.existsByTitleAndIdNot(article.title, article.id)) {
                    return new ArticleServiceResult(false, true, null);
                }
            }
        }

        if (!isUpdate) {
            // Création : vérifier si le titre existe déjà
            if (daoArticle.existsByTitle(article.title)) {
                return new ArticleServiceResult(false, true, null);
            }
        }

        // Sauvegarder l'article
        Article savedArticle = daoArticle.save(article);
        return new ArticleServiceResult(isUpdate, false, savedArticle);
    }

    //Pour distinguer les trois étapes de création, mise à jour et sauvegarde
    public static class ArticleServiceResult {
        public final boolean isUpdate;
        public final boolean titleExists;
        public final Article article;

        public ArticleServiceResult(boolean isUpdate, boolean titleExists, Article article) {
            this.isUpdate = isUpdate;
            this.titleExists = titleExists;
            this.article = article;
        }
    }
//endregion
}

