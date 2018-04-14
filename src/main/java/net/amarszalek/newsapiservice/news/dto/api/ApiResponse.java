
package net.amarszalek.newsapiservice.news.dto.api;

import lombok.Data;

import java.util.List;

/**
 * Root object for response from News API
 */
@Data
public final class ApiResponse {

    private final String status;
    private final Integer totalResults;
    private final List<ArticleApiResponse> articles;

}
