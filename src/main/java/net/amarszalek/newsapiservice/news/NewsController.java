package net.amarszalek.newsapiservice.news;

import io.javalin.Context;
import net.amarszalek.newsapiservice.news.dto.News;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewsController.class);

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    public void fetchNews(Context ctx) {
        String lang = ctx.param("lang");
        String category = ctx.param("category");

        LOGGER.info("Incoming GET news request with lang={} and category={}", lang, category);

        News news = newsService.getTopNews(lang, category);

        ctx.json(news);
    }
}
