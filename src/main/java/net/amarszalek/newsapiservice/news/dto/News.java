package net.amarszalek.newsapiservice.news.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class News {

    private String country;
    private String category;
    List<Article> articles = new ArrayList<>();

    public void addArticle(Article article) {
        if (articles == null) {
            articles = new ArrayList<>();
        }
        articles.add(article);
    }
}
