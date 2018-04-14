
package net.amarszalek.newsapiservice.news.dto.api;

import lombok.Data;

@Data
public final class ArticleApiResponse {

    private final Source source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final String publishedAt;

    @Data
    public final static class Source {

        private final String id;
        private final String name;

    }
}
