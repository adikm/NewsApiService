package net.amarszalek.newsapiservice.news;

import io.javalin.Context;
import net.amarszalek.newsapiservice.news.dto.News;

public class NewsController {

    private NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    public void fetchNews(Context ctx) {
        String lang = ctx.param("lang");
        String category = ctx.param("category");

        News news = newsService.getTopNews(lang, category);

        ctx.json(news);
    }
}
