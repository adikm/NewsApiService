package net.amarszalek.newsapiservice.news;

import net.amarszalek.newsapiservice.news.dto.Article;
import net.amarszalek.newsapiservice.news.dto.News;
import net.amarszalek.newsapiservice.news.dto.api.ApiResponse;
import net.amarszalek.newsapiservice.news.dto.api.ArticleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsService.class);

    private final NewsApiClient newsApiClient;
    private final String apiKey;

    public NewsService(NewsApiClient newsApiClient, String apiKey) {
        this.newsApiClient = newsApiClient;
        this.apiKey = apiKey;
    }

    public News getTopNews(String lang, String category) {
        ApiResponse apiResponse = newsApiClient.fetchTop(lang, category, apiKey);
        LOGGER.info("Data successfully obtained from NewsAPI. Articles found: {}", apiResponse.getTotalResults());

        return prepareNews(apiResponse, lang, category);
    }

    private News prepareNews(ApiResponse apiResponse, String lang, String category) {
        News news = new News();
        news.setCountry(lang);
        news.setCategory(category);

        for (ArticleApiResponse articleApiResponse : apiResponse.getArticles()) {
            Article article = new Article();
            article.setAuthor(articleApiResponse.getAuthor());
            article.setTitle(articleApiResponse.getTitle());
            article.setDescription(articleApiResponse.getDescription());
            article.setDate(articleApiResponse.getPublishedAt());
            article.setSourceName(articleApiResponse.getSource().getName());
            article.setArticleUrl(articleApiResponse.getUrl());
            article.setImageUrl(articleApiResponse.getUrlToImage());

            news.addArticle(article);
        }

        return news;
    }


}
