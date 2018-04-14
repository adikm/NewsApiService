package net.amarszalek.newsapiservice;

import feign.Feign;
import feign.FeignException;
import feign.jackson.JacksonDecoder;
import io.javalin.Javalin;
import net.amarszalek.newsapiservice.news.NewsApiClient;
import net.amarszalek.newsapiservice.news.NewsController;
import net.amarszalek.newsapiservice.news.NewsService;
import org.eclipse.jetty.http.HttpStatus;

public class NewsApplication {

    public static void main(String[] args) {
        new NewsApplication().initialize();
    }

    private void initialize() {
        NewsApiClient newsApiClient = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(NewsApiClient.class, "https://newsapi.org/v2/");

        NewsService newsService = new NewsService(newsApiClient);
        NewsController controller = new NewsController(newsService);

        Javalin app = Javalin.start(7000);
        app.get("news/:lang/:category", controller::fetchNews);

        app.exception(FeignException.class, (e, ctx) -> {
            ctx.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            ctx.result("Communication problem with external service");
        });
    }
}