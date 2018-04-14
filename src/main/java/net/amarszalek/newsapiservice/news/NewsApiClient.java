package net.amarszalek.newsapiservice.news;

import feign.Param;
import feign.RequestLine;
import net.amarszalek.newsapiservice.news.dto.api.ApiResponse;

/**
 * Interface for obtaining data from external News API.
 */
public interface NewsApiClient {

    @RequestLine("GET /top-headlines?country={country}&category={category}&apiKey={apiKey}")
    ApiResponse fetchTop(@Param("country") String country, @Param("category") String category,
                         @Param("apiKey") String apiKey);
}
