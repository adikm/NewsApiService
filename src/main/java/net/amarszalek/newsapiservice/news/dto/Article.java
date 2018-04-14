package net.amarszalek.newsapiservice.news.dto;

import lombok.Data;

@Data
public class Article {

    private String author;
    private String title;
    private String description;
    private String date;
    private String sourceName;
    private String articleUrl;
    private String imageUrl;

}
