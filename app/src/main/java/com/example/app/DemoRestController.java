package com.example.app;


import com.example.domain.Article;
import com.example.domain.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DemoRestController {

    @Autowired
    ArticleService articleService;
//region All articles
    @GetMapping("/articles")
    public ApiResponse getArticles(){
        List<Article> articles = articleService.getAllArticles();
        return new ApiResponse(
                "2002",
                "Tous les articles ont été récupérés",
                articles
        );
    }
//endregion

//region Articles par ID
    @GetMapping("/articles/{id}")
    public ApiResponse getArticleById(@PathVariable String id) {

        Optional<Article> article = articleService.getArticleById(id);

        if (article.isPresent()) {
            return new ApiResponse(
                    "2002",
                    "L'article a été récupéré avec succès",
                    article.get()
            );
        } else {
            return new ApiResponse(
                    "7001",
                    "L'article n'existe pas",
                    null
            );
        }
    }
//endregion

//region Supprimer un article via son ID
    @DeleteMapping("articles/{id}")
    public ResponseEntity<ApiResponse> deleteArticle(@PathVariable String id) {
        boolean isDeleted = articleService.deleteArticle(id);

        if (isDeleted) {
            ApiResponse response = new ApiResponse(
                    "2002",
                    "L'article a été supprimé avec succès",
                    true
            );
            return ResponseEntity.ok(response);
        } else {
            ApiResponse response = new ApiResponse(
                    "7001",
                    "L'article n'existe pas",
                    false
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
//endregion

//region Save/Création/Mise à jour
    // Création uniquement
    @PostMapping("articles")
    public ResponseEntity<ApiResponse> createArticle(@RequestBody Article article) {
        // Forcer l'ID à null pour garantir une création
        article.id = null;

        ArticleService.ArticleServiceResult result = articleService.saveArticle(article);

        if (result.titleExists) {
            ApiResponse response = new ApiResponse(
                    "7006",
                    "Le titre est déjà utilisé",
                    null
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        ApiResponse response = new ApiResponse(
                "2002",
                "Article créé avec succès",
                result.article
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // J'utilise PUT car avec post je pouvais pas modifier car le titre ou l'id était deja pris.

    @PutMapping("articles/{id}")
    public ResponseEntity<ApiResponse> updateArticle(@PathVariable String id, @RequestBody Article article) {
        // Forcer l'ID de l'URL
        article.id = id;

        ArticleService.ArticleServiceResult result = articleService.saveArticle(article);

        if (result.titleExists) {
            ApiResponse response = new ApiResponse(
                    "7006",
                    "Le titre est déjà utilisé",
                    null
            );
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        ApiResponse response = new ApiResponse(
                "2003",
                "Article modifié avec succès",
                result.article
        );
        return ResponseEntity.ok(response);
    }
//endregion
}

