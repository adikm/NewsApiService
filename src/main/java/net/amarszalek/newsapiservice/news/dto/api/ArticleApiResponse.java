
package net.amarszalek.newsapiservice.news.dto.api;

import lombok.Data;

@Data
public class ArticleApiResponse {

    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    @Data
    public static class Source {

        private String id;
        private String name;

    }
}
