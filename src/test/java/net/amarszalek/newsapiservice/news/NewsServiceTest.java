package net.amarszalek.newsapiservice.news;

import net.amarszalek.newsapiservice.news.dto.Article;
import net.amarszalek.newsapiservice.news.dto.News;
import net.amarszalek.newsapiservice.news.dto.api.ApiResponse;
import net.amarszalek.newsapiservice.news.dto.api.ArticleApiResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class NewsServiceTest {

    private static final String SOURCE_ID = "10";
    private static final String SOURCE_NAME = "SourceName";
    private static final String AUTHOR = "Author";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String ARTICLE_URL = "http://article.xyz";
    private static final String IMAGE_URL = "http://urlToImage.jpg";
    private static final String PUBLISHED_AT = "DATA";
    private static final String PL = "pl";
    private static final String CATEGORY = "technology";
    private static final String API_KEY = "key";

    private NewsService newsService = new NewsService(mockNewsClientApi(), API_KEY);

    @DisplayName("Get top news with correct parameters will return News with Articles")
    @Test
    public void getTopNews_ApiCallOk_ShouldReturnArticles() {
        News news = newsService.getTopNews(PL, CATEGORY);

        assertEquals(PL, news.getCountry());
        assertEquals(CATEGORY, news.getCategory());

        List<Article> articles = news.getArticles();
        assertNotNull(articles);
        assertFalse(articles.isEmpty());

        for (Article article : articles) {
            assertEquals(SOURCE_NAME, article.getSourceName());
            assertEquals(AUTHOR, article.getAuthor());
            assertEquals(TITLE, article.getTitle());
            assertEquals(DESCRIPTION, article.getDescription());
            assertEquals(ARTICLE_URL, article.getArticleUrl());
            assertEquals(IMAGE_URL, article.getImageUrl());
            assertEquals(PUBLISHED_AT, article.getDate());
        }
    }

    @DisplayName("Get top news with incorrect parameters will return News without Articles")
    @Test
    public void getTopNews_ApiCallWithNotExistingParametersValues_ShouldReturnNoArticles() {
        String category = "category";
        String lang = "lang";

        News news = newsService.getTopNews(lang, category);

        assertEquals(lang, news.getCountry());
        assertEquals(category, news.getCategory());

        List<Article> articles = news.getArticles();
        assertNotNull(articles);
        assertTrue(articles.isEmpty());
    }
    
    private NewsApiClient mockNewsClientApi() {
        ArticleApiResponse.Source source = new ArticleApiResponse.Source(SOURCE_ID, SOURCE_NAME);
        ArticleApiResponse articleApiResponse = new ArticleApiResponse(source, AUTHOR, TITLE,
                DESCRIPTION, ARTICLE_URL,
                IMAGE_URL, PUBLISHED_AT);

        ApiResponse successfulApiResponse = new ApiResponse("ok", 1, Collections.singletonList(articleApiResponse));
        ApiResponse nothingFoundApiResponse = new ApiResponse("ok", 0, Collections.emptyList());

        NewsApiClient clientMock = Mockito.mock(NewsApiClient.class);
        when(clientMock.fetchTop(anyString(), anyString(), anyString())).thenReturn(nothingFoundApiResponse);
        when(clientMock.fetchTop(eq(PL), eq(CATEGORY), anyString())).thenReturn(successfulApiResponse);

        return clientMock;
    }

}