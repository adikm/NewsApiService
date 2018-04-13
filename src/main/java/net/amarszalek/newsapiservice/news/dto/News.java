
package net.amarszalek.newsapiservice.news.dto;

import lombok.Data;

import java.util.List;

@Data
public class News {

    private String status;
    private Integer totalResults;
    private List<Article> articles;

}
