package net.amarszalek.newsapiservice.news;

import net.amarszalek.newsapiservice.news.dto.Article;
import net.amarszalek.newsapiservice.news.dto.News;
import net.amarszalek.newsapiservice.news.dto.api.ApiResponse;
import net.amarszalek.newsapiservice.news.dto.api.ArticleApiResponse;

public class NewsService {

    private static final String API_KEY = "7f0c496e01e6437196c01d9d9380a1f7";

    private NewsApiClient newsApiClient;

    public NewsService(NewsApiClient newsApiClient) {
        this.newsApiClient = newsApiClient;
    }

    public News getTopNews(String lang, String category) {
        ApiResponse apiResponse = newsApiClient.fetchTop(lang, category, API_KEY);

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
