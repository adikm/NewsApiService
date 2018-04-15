package net.amarszalek.newsapiservice;

import feign.Feign;
import feign.gson.GsonDecoder;
import io.javalin.Javalin;
import net.amarszalek.newsapiservice.news.NewsApiClient;
import net.amarszalek.newsapiservice.news.NewsController;
import net.amarszalek.newsapiservice.news.NewsService;
import net.amarszalek.newsapiservice.news.dto.NewsApiException;

import java.nio.charset.StandardCharsets;

public class NewsApplication {

    private static final String NEWSAPI_URL = "https://newsapi.org/v2/";

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Specify your API key");
        }

        new NewsApplication().initialize(args[0]);
    }

    private void initialize(String apiKey) {
        NewsApiClient newsApiClient = Feign.builder()
                .decoder(new GsonDecoder())
                .target(NewsApiClient.class, NEWSAPI_URL);

        NewsService newsService = new NewsService(newsApiClient, apiKey);
        NewsController controller = new NewsController(newsService);

        Javalin app = Javalin.create()
                .defaultCharacterEncoding(StandardCharsets.UTF_8.name())
                .enableStaticFiles("/static")
                .port(7000).start();

        app.get("news/:lang/:category", controller::fetchNews);

        app.exception(NewsApiException.class, (e, ctx) -> {
            ctx.status(e.getStatus());
            ctx.result(e.getMessage());
        });
    }
}