package net.amarszalek.newsapiservice;

import io.javalin.Javalin;

public class NewsApiService {

    public static void main(String[] args) {
        Javalin application = Javalin.start(7000);
    }

}
