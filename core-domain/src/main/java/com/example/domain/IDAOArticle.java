package com.example.domain;

import java.util.List;
import java.util.Optional;

public interface IDAOArticle {
    //Avoir tous les articles
    public List<Article> getAll();
    //Avoir les articles par ID
    Optional<Article> findById(String id);

    //Supprimer un article selon son ID
    // Boolean pour le true/false de l'article supprimer ou non
    boolean deleteById(String id);

    //Save/ Mise a jour/Création d'un article
    boolean existsByTitle(String title);
    boolean existsByTitleAndIdNot(String title, String id);
    Article save(Article article);

}

