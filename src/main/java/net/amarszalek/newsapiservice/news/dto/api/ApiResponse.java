
package net.amarszalek.newsapiservice.news.dto.api;

import lombok.Data;

import java.util.List;

/**
 * Root object for response from News API
 */
@Data
public class ApiResponse {

    private String status;
    private Integer totalResults;
    private List<ArticleApiResponse> articles;

}
